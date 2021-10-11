package com.alvarado.servlets;

import com.alvarado.models.Staff;
import com.alvarado.repositories.StaffRepo;
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

public class LoginServlet extends HttpServlet {
    private StaffRepo staffRepo = new StaffRepo();
    private Logger log = LogManager.getLogger(LoginServlet.class);
    private StaffServices staffServices = new StaffServices();
    private static ObjectMapper om = new ObjectMapper();

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Log when this servlet has been accessed
        System.out.println("\nLoginServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("LoginServlet Accessed");


        // Print the URI path just to see what it is
        String uriNoContext = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("uriNoContext:    " + uriNoContext);


        // Begin HttpSession here, (instantiate the session object)
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Created");


        // Set the Maximum Session Time in seconds
        session.setMaxInactiveInterval(1800);
        System.out.println("Max inactive interval: " + session.getMaxInactiveInterval()/60 + " minutes.");


        // Set an attribute that can be retrieved by the next servlet
        session.setAttribute("username", request.getParameter("username")); //request parameters come from html form
        session.setAttribute("password", request.getParameter("password"));
//        Staff s = staffRepo.getByUsername(request.getParameter("username"));
//        session.setAttribute("staffId", s.getStaffId());

        String username = session.getAttribute("username").toString();
        String password = session.getAttribute("password").toString();
        System.out.println("Username: " + username + " | " + "Password: " + password);


        //Now check if credentials match in order to send staff to correct homepage
        if (staffServices.login(username, password)) {
            System.out.println("\nLoginServlet | Staff signed in successfully");
            log.trace("Staff signed in successfully");

            String staffLevel = staffServices.staffType(username);
            System.out.println("LoginServlet | Printing Staff Level:    " + staffLevel);

            switch (staffLevel){
                case "employee" : {

                    System.out.println("LoginServlet | 'employee' switch case activated");
                    response.getWriter().write(staffServices.staffInfoAsJSON(username));
                    response.sendRedirect("static/employee.html");

                    break;

                }
                case "super" : {
                    System.out.println("LoginServlet | 'super' switch case activated");
                    response.getWriter().write(staffServices.staffInfoAsJSON(username));
                    response.sendRedirect("static/super.html");
                    break;
                }
                case "head" : {
                    response.sendRedirect("static/head.html");
                    break;
                }
                case "superHead" : {
                    response.sendRedirect("static/superHead.html");
                    break;
                }
                case "benco" : {
                    response.sendRedirect("static/benco.html");
                    break;
                }
            }

        } else{
            System.out.println("LoginController Class | Login Failed");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Login Credentials");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

}
