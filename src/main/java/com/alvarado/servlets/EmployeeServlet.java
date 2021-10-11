package com.alvarado.servlets;

import com.alvarado.models.Staff;
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

public class EmployeeServlet extends HttpServlet {

    private StaffRepo staffRepo = new StaffRepo();
    private Logger log = LogManager.getLogger(EmployeeServlet.class);
    private ObjectMapper om = new ObjectMapper();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        // Log when this servlet has been accessed
        System.out.println("\nEmployeeServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("EmployeeServlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());

        session.invalidate();
        System.out.println("Session was invalidated.");
        log.trace("Session was invalidated.");


        response.sendRedirect("http://localhost:8080/Project1/login.html");

//        response.getWriter().write("<h1>You Have logged out! </h1>");
//        response.getWriter().write("<a href=\"http://localhost:8080/Project1/login.html\">Return to Login Page</a>");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        // Log when this servlet has been accessed
        System.out.println("\nEmployeeServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("EmployeeServlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        //Get the staff member's username again
        String username = session.getAttribute("username").toString();
        Staff s = staffRepo.getByUsername(username);

        response.getWriter().write(om.writeValueAsString(s));


    }


}
