package com.alvarado.servlets;

import com.alvarado.models.Approvals;
import com.alvarado.models.Requests;
import com.alvarado.models.Staff;
import com.alvarado.repositories.ApprovalsRepo;
import com.alvarado.repositories.RequestsRepo;
import com.alvarado.repositories.StaffRepo;
import com.alvarado.services.BalanceServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class RequestServlet extends HttpServlet {

    private BalanceServices balanceServices = new BalanceServices();
    private RequestsRepo requestsRepo = new RequestsRepo();
    private ApprovalsRepo approvalsRepo = new ApprovalsRepo();
    private StaffRepo staffRepo = new StaffRepo();
    private Logger log = LogManager.getLogger(RequestServlet.class);
    private ObjectMapper om = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Log when this servlet has been accessed
        System.out.println("\nRequestServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("RequestServlet Accessed");


        //Print the URI path just to see what it is
        String uriNoContext = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("uriNoContext:    " + uriNoContext);


        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");


        //Double check that credentials from this session are the same
        String username = session.getAttribute("username").toString();
        String password = session.getAttribute("password").toString();
        System.out.println("Username: " + username + " | " + "Password: " + password);


        // Create a new request object, a new staff object, and a new approval object.
        Staff s = staffRepo.getByUsername(username);
        Requests r = new Requests();
        Approvals a = new Approvals();
        a.setStaffId(s.getStaffId()); //set approval staffId to current staff's Id

        // Cost comes as a string from JS but needs to be a big decimal
        BigDecimal cost = new BigDecimal(request.getParameter("cost"));

        // Fill set the request object attributes with the form data
        r.setStaffId(s.getStaffId());
        r.setTodayDate(request.getParameter("today_date"));   // what data type will this come in?
        r.setEventDate(request.getParameter("event_date"));   // what data type will this come in?
        r.setTime(request.getParameter("time"));    // what data type will this come in?
        r.setLocation(request.getParameter("city") + ", " + request.getParameter("state"));
        r.setDescription(request.getParameter("description"));
        r.setCost(cost);
        r.setGradingFormat(request.getParameter("grading_format"));
        r.setEventType(request.getParameter("event_type"));   // what data type will this come in?
        r.setJustification(request.getParameter("justification"));

        // I want to print each thing and see how it looks like in the console. How do I get the data type?
        System.out.println("\nstaff id: " + r.getStaffId());
        System.out.println("today_date: " + r.getTodayDate());
        System.out.println("event_date: " + r.getEventDate());
        System.out.println("time: " + r.getTime());
        System.out.println("location: " + r.getLocation());
        System.out.println("description: " + r.getDescription());
        System.out.println("cost: " + r.getCost());
        System.out.println("grading_format: " + r.getGradingFormat());
        System.out.println("event_type: " + r.getEventType());
        System.out.println("justification : " + r.getJustification());

        log.trace("Form Data Successfully received from user: " + session.getAttribute("username"));




        // Now send form data to the requests table in the database.
        // Also create a new approval row
        requestsRepo.add(r);
        approvalsRepo.add(a);

        // Update the balance table to reflect the new request info, which will update the pending amount
        balanceServices.updateAvailPend(s);

        response.getWriter().write("<h1>Your Reimbursement Request has been submitted for review. </h1>");
        response.getWriter().write("<a href=\"employee.html\">Return to Employee Page</a>");



    }

}
