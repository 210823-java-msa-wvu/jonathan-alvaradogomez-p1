package com.alvarado.servlets;

import com.alvarado.models.Requests;
import com.alvarado.models.Staff;
import com.alvarado.repositories.ApprovalsRepo;
import com.alvarado.repositories.RequestsRepo;
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
import java.util.List;

public class BencoViewRequestsServlet extends HttpServlet {

    private RequestsRepo requestsRepo = new RequestsRepo();
    private ApprovalsRepo approvalsRepo = new ApprovalsRepo();
    private StaffServices staffServices = new StaffServices();
    private StaffRepo staffRepo = new StaffRepo();
    private Logger log = LogManager.getLogger(EmployeeServlet.class);
    private ObjectMapper om = new ObjectMapper();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Log when this servlet has been accessed
        System.out.println("\nBencoViewRequests received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("BencoViewRequests Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        //Get the staff member's username again
        String username = session.getAttribute("username").toString();
        Staff s = staffRepo.getByUsername(username);

        // Use the request repo to obtain all requests available.
        List<Requests> r = requestsRepo.getAll();




        System.out.println("om.writeValueAsString(r):    " + om.writeValueAsString(r));

        //give response to webpage (the list of request from this staff member). if null, deal with it in JS
        //or perhaps give a redirect here if NO requests exist for this use
        response.getWriter().write(om.writeValueAsString(r));
    }


}
