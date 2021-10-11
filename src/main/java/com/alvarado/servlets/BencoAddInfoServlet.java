package com.alvarado.servlets;

import com.alvarado.models.Approvals;
import com.alvarado.models.Staff;
import com.alvarado.repositories.ApprovalsRepo;
import com.alvarado.repositories.StaffRepo;
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

public class BencoAddInfoServlet extends HttpServlet {

    private StaffRepo staffRepo = new StaffRepo();
    private ApprovalsRepo approvalsRepo = new ApprovalsRepo();
    private Logger log = LogManager.getLogger(EmployeeServlet.class);
    private ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Log when this servlet has been accessed
        System.out.println("\nBencoAddInfoServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("BencoAddInfoServlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        //Get the staff member's username again
        String username = session.getAttribute("username").toString();
        Staff s = staffRepo.getByUsername(username);

        //Use the username to obtain staff id, which can access current approvals for that staff member
        List<Approvals> a = approvalsRepo.getAll();

        for (int i = 0; i < a.size(); i++){
            System.out.println("Approval list index at i = " + i + ":    " + a.get(i));
        }

        response.getWriter().write(om.writeValueAsString(a));



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Log when this servlet has been accessed
        System.out.println("\nBencoAddInfoServlet Servlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("BencoAddInfoServlet Servlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        //Get the staff member's username again
        String username = session.getAttribute("username").toString();
        Staff s = staffRepo.getByUsername(username);




        Integer requestID = Integer.parseInt(request.getParameter("request_id")) ;
        Integer staffID = Integer.parseInt(request.getParameter("employee_id")) ;

        System.out.println("Printing requestID:    " + requestID);
        System.out.println("Printing staffID:      " + staffID);


        // Access the approval table in the database in order to update the information
        Approvals a = approvalsRepo.getByIDS(staffID, requestID);

        // update the approval object 'a' with the new information
        a.setBencoMoreInfo(true);
        System.out.println("benco_more_info was set to true");

        log.trace("Approval table is about to be updated...");
//        log.trace("Form Data Successfully received from user: " + session.getAttribute("username"));

        // Use the objects to send data back to the databases and update appropriate rows and columns
        approvalsRepo.update(a);
        System.out.println("Approval table update via approvalsRepo.");

        //now redirect them to the same page
        response.sendRedirect("bencoAddInfo.html");


    }


}
