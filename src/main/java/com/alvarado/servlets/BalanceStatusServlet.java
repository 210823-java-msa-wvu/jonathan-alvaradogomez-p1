package com.alvarado.servlets;

import com.alvarado.models.Approvals;
import com.alvarado.models.Balance;
import com.alvarado.models.Staff;
import com.alvarado.repositories.ApprovalsRepo;
import com.alvarado.repositories.BalanceRepo;
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

public class BalanceStatusServlet extends HttpServlet {

    private ApprovalsRepo approvalsRepo = new ApprovalsRepo();
    private BalanceRepo balanceRepo = new BalanceRepo();
    private StaffRepo staffRepo = new StaffRepo();
    private Logger log = LogManager.getLogger(EmployeeServlet.class);
    private ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Log when this servlet has been accessed
        System.out.println("\nBalanceStatusServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("BalanceStatusServlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        //Get the staff member's username again
        String username = session.getAttribute("username").toString();
        Staff s = staffRepo.getByUsername(username);

        //This servlet will either access the balance table or the approval table, depending on the request header
        String headerInfo = request.getHeader("type");
        System.out.println("Printing headerInfo:    " + headerInfo);

        if ( headerInfo.equals("balance") ){ //if the request header is "balance", then access the balance table
            System.out.println("Getting balance data ... ");

            Balance b = balanceRepo.getById(s.getStaffId());

            System.out.println("Printing balance:    " + b);

            response.getWriter().write(om.writeValueAsString(b));


        } else if (headerInfo.equals("approvals")) { // the request header is "approvals", so access the approval table
            System.out.println("Getting approvals data ... ");

            List<Approvals> a = approvalsRepo.getAllById(s.getStaffId());

            for (int i = 0; i < a.size(); i++){
                System.out.println("Approval list index at i = " + i + ":    " + a.get(i));
            }

            response.getWriter().write(om.writeValueAsString(a));



        }



    }

}
