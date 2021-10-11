package com.alvarado.servlets;

import com.alvarado.models.Approvals;
import com.alvarado.models.Staff;
import com.alvarado.repositories.ApprovalsRepo;
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
import java.util.List;

public class HeadAppDenServlet extends HttpServlet {

    private BalanceServices balanceServices = new BalanceServices();
    private StaffRepo staffRepo = new StaffRepo();
    private ApprovalsRepo approvalsRepo = new ApprovalsRepo();
    private Logger log = LogManager.getLogger(EmployeeServlet.class);
    private ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Log when this servlet has been accessed
        System.out.println("\nHeadAppDenServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("HeadAppDenServlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        //Get the staff member's username again
        String username = session.getAttribute("username").toString();
        Staff s = staffRepo.getByUsername(username);

        //obtain approvals
        List<Approvals> a = approvalsRepo.getAll();

        for (int i = 0; i < a.size(); i++){
            System.out.println("Approval list index at i = " + i + ":    " + a.get(i));
        }

        response.getWriter().write(om.writeValueAsString(a));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Log when this servlet has been accessed
        System.out.println("\nHeadAppDenServlet Servlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("HeadAppDenServlet Servlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        // obtain the parameters
        Integer requestId = Integer.valueOf(request.getParameter("request_id"));
        Integer staffId = Integer.valueOf(request.getParameter("employee_id"));
        Boolean decision = Boolean.valueOf(request.getParameter("answer"));
        String reason = request.getParameter("reason");

        System.out.println("Printing request ID:  " + requestId);
        System.out.println("Printing staff ID " + staffId);
        System.out.println("Printing decision:  " + decision);
        System.out.println("Printing reason:  " + reason);

        // set the right attributes for the object
        // also need the two IDs !!!
        Approvals a = approvalsRepo.getByIDS(staffId, requestId);

        a.setHeadDecision(decision);
        a.setHeadReasoning(reason);

        log.trace("Approval table is about to be updated...");

        //now update the data in the database.
        approvalsRepo.update(a);

        // update the balance table in case all approval conditions have been met
        Staff staff = staffRepo.getById(staffId);
        balanceServices.updatePendingAwarded(staff,requestId);

        //page redirect to same page
        response.sendRedirect("headAppDen.html");



    }



}
