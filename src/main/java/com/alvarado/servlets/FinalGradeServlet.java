package com.alvarado.servlets;

import com.alvarado.models.Approvals;
import com.alvarado.models.Requests;
import com.alvarado.models.Staff;
import com.alvarado.repositories.ApprovalsRepo;
import com.alvarado.repositories.RequestsRepo;
import com.alvarado.repositories.StaffRepo;
import com.alvarado.services.BalanceServices;
import com.alvarado.services.StaffServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FinalGradeServlet extends HttpServlet {

    private BalanceServices balanceServices = new BalanceServices();
    private RequestsRepo requestsRepo = new RequestsRepo();
    private ApprovalsRepo approvalsRepo = new ApprovalsRepo();
    private StaffServices staffServices = new StaffServices();
    private StaffRepo staffRepo = new StaffRepo();
    private Logger log = LogManager.getLogger(EmployeeServlet.class);
    private ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        // Log when this servlet has been accessed
        System.out.println("\nFinalGradeServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("FinalGradeServlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        //Get the staff member's username again
        String username = session.getAttribute("username").toString();
        Staff s = staffRepo.getByUsername(username);

        //Use the username to obtain staff id, which can access current requests for that staff member
        List<Requests> r = requestsRepo.getAllById(s.getStaffId());



        System.out.println("om.writeValueAsString(r):    " + om.writeValueAsString(r));

        //give response to webpage (the list of request from this staff member). if null, deal with it in JS
        //or perhaps give a redirect here if NO requests exist for this use
        response.getWriter().write(om.writeValueAsString(r));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        // Log when this servlet has been accessed
        System.out.println("\nFinalGradeServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("FinalGradeServlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        //Get the staff member's username again
        String username = session.getAttribute("username").toString();
        Staff s = staffRepo.getByUsername(username);

        Integer requestID = Integer.parseInt(request.getParameter("request_id")) ;
        System.out.println("Printing requestID:    " + requestID);

        // Access the approval database and the request database to update the correct columns

        // Get the corresponding request object and the approval object.
        // NOTE: requests_id and approval_id in the database have the same value, the have a foreign key relationship
        Requests r = requestsRepo.getByIDS(s.getStaffId(), requestID);
        Approvals a = approvalsRepo.getByIDS(s.getStaffId(), requestID);

//        System.out.println("Printing out request.getParameter(\"letter\") :   " + request.getParameter("letter"));

        if (request.getParameter("letter") == null){ // if the "letter" parameter is null, then the grade was a percentage

            String newGrade = request.getParameter("percentage");

            System.out.println("Printing newGrade:    " + newGrade);
            r.setFinalGrade(newGrade);
            a.setFinalGrade(newGrade);

            //Print the request and the approval objects to see what information they contain
            System.out.println("Printing request object r:    " + r);
            System.out.println("Printing approval object a:    " + a);
            log.trace("Request table and Approval tables are about to be updated...");
            log.trace("Form Data Successfully received from user: " + session.getAttribute("username"));


            // redirect the user to a different page
            response.getWriter().write("<h1>Your Final Grade has been submitted for review. </h1>");
            response.getWriter().write("<a href=\"employee.html\">Return to Employee Page</a>");


            // Use the objects to send data back to the databases and update appropriate rows and columns
            requestsRepo.update(r);
            approvalsRepo.update(a);

            //include this method to update balance table values as well
            balanceServices.updatePendingAwarded(s,requestID);

        } else{ // this is the case when the final grade is a letter, not a percentage
            String newGrade = request.getParameter("letter");

            System.out.println("Printing newGrade:    " + newGrade);
            r.setFinalGrade(newGrade);
            a.setFinalGrade(newGrade);

            //Print the request and the approval objects to see what information they contain
            System.out.println("Printing request object r:    " + r);
            System.out.println("Printing approval object a:    " + a);
            log.trace("Request table and Approval tables are about to be updated...");
            log.trace("Form Data Successfully received from user: " + session.getAttribute("username"));


            // redirect the user to a different page
            response.getWriter().write("<h1>Your Final Grade has been submitted for review. </h1>");
            response.getWriter().write("<a href=\"employee.html\">Return to Employee Page</a>");


            // Use the objects to send data back to the databases and update appropriate rows and columns
            requestsRepo.update(r);
            approvalsRepo.update(a);

            //include this method to update balance table values as well
            balanceServices.updatePendingAwarded(s,requestID);



        }
    }
}
