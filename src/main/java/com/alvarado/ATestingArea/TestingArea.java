package com.alvarado.ATestingArea;

import com.alvarado.models.Approvals;
import com.alvarado.models.Balance;
import com.alvarado.models.Staff;
import com.alvarado.repositories.ApprovalsRepo;
import com.alvarado.repositories.StaffRepo;
import com.alvarado.services.BalanceServices;
import com.alvarado.services.StaffServices;
import com.alvarado.utils.ConnectionUtil;

import java.sql.Connection;
import java.util.List;

public class TestingArea {

    private static ApprovalsRepo approvalsRepo = new ApprovalsRepo();
    private static StaffRepo staffRepo = new StaffRepo();
    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
    private static StaffServices staffServices = new StaffServices();
    private static BalanceServices balanceServices = new BalanceServices();

    public static void main(String[] args){

        System.out.println("Hello from Testing Area!");

//        System.out.println(staffServices.staffInfoAsJSON("ja"));
        Staff staff = staffRepo.getByUsername("ja");
//        balanceServices.updateAvailPend(staff);

        balanceServices.updatePendingAwarded(staff, 2);
    }

}
