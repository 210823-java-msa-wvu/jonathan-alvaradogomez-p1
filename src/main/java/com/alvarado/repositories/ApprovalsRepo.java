package com.alvarado.repositories;


import com.alvarado.models.Approvals;
import com.alvarado.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//This class will define methods that we can use to interact with our database
public class ApprovalsRepo implements CrudRepository<Approvals>{

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    //CREATE
    @Override
    public Approvals add(Approvals approvals) { //add an approval to the table
        try(Connection conn = cu.getConnection()){
            String sql = "insert into approvals values " +
                    "(default, ? , default, default, default, default, default, default, " +
                    "default, default, default, default, default)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, approvals.getStaffId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();

        }
        return null;
    }

    //READ
    @Override
    public Approvals getById(Integer staff_id) {
//        try(Connection conn = cu.getConnection()){
//            String sql = "select * from approvals where staff_id = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, staff_id);
//
//            ResultSet rs = ps.executeQuery();
//
//            if(rs.next()){
//                Approvals a = new Approvals();
//                a.setApprovalId(rs.getInt("approval_id"));
//                a.setStaffId(rs.getInt("staff_id"));
//                a.setFinalGrade(rs.getString("final_grade"));
//                a.setSuperDecision(rs.getBoolean("super_decision"));
//                a.setSuperReasoning(rs.getString("super_reasoning"));
//                a.setHeadDecision(rs.getBoolean("head_decision"));
//                a.setHeadReasoning(rs.getString("head_reasoning"));
//                a.setBencoDecision(rs.getBoolean("benco_decision"));
//                a.setBencoReasoning(rs.getString("benco_reasoning"));
//
//                return a;
//
//            }
//
//        }catch(SQLException e){
//            e.printStackTrace();
//
//        }
        return null;
    }

    @Override
    public Approvals getByIDS(Integer staff_id, Integer approval_id) {
        //approval_id is a foreign key of request_id
        //get an approval by staff id and approval_id

        try(Connection conn = cu.getConnection()){
            String sql = "select * from approvals where staff_id = ? and approval_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staff_id);
            ps.setInt(2, approval_id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Approvals a = new Approvals();
                a.setApprovalId(rs.getInt("approval_id"));
                a.setStaffId(rs.getInt("staff_id"));
                a.setStaffMoreInfo(rs.getString("staff_more_info"));
                a.setFinalGrade(rs.getString("final_grade"));

                a.setSuperMoreInfo(rs.getBoolean("super_more_info"));
                a.setSuperDecision(rs.getBoolean("super_decision"));
                a.setSuperReasoning(rs.getString("super_reasoning"));

                a.setHeadMoreInfo(rs.getBoolean("head_more_info"));
                a.setHeadDecision(rs.getBoolean("head_decision"));
                a.setHeadReasoning(rs.getString("head_reasoning"));

                a.setBencoMoreInfo(rs.getBoolean("benco_more_info"));
                a.setBencoDecision(rs.getBoolean("benco_decision"));
                a.setBencoReasoning(rs.getString("benco_reasoning"));

                return a;

            }

        }catch(SQLException e){
            e.printStackTrace();

        }
        return null;
    }



    @Override
    public List<Approvals> getAll() {
        List<Approvals> approvals = new ArrayList<>();

        try(Connection conn = cu.getConnection()){
            String sql = "select * from approvals";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Approvals a = new Approvals();
                a.setApprovalId(rs.getInt("approval_id"));
                a.setStaffId(rs.getInt("staff_id"));
                a.setStaffMoreInfo(rs.getString("staff_more_info"));
                a.setFinalGrade(rs.getString("final_grade"));

                a.setSuperMoreInfo(rs.getBoolean("super_more_info"));
                a.setSuperDecision(rs.getBoolean("super_decision"));
                a.setSuperReasoning(rs.getString("super_reasoning"));

                a.setHeadMoreInfo(rs.getBoolean("head_more_info"));
                a.setHeadDecision(rs.getBoolean("head_decision"));
                a.setHeadReasoning(rs.getString("head_reasoning"));

                a.setBencoMoreInfo(rs.getBoolean("benco_more_info"));
                a.setBencoDecision(rs.getBoolean("benco_decision"));
                a.setBencoReasoning(rs.getString("benco_reasoning"));

                approvals.add(a);

            }

            return approvals;

        }catch(SQLException e){
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Approvals> getAllById(Integer staff_id) {
        List<Approvals> approvals = new ArrayList<>();

        try(Connection conn = cu.getConnection()){
            String sql = "select * from approvals where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staff_id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Approvals a = new Approvals();
                a.setApprovalId(rs.getInt("approval_id"));
                a.setStaffId(rs.getInt("staff_id"));
                a.setStaffMoreInfo(rs.getString("staff_more_info"));
                a.setFinalGrade(rs.getString("final_grade"));

                a.setSuperMoreInfo(rs.getBoolean("super_more_info"));
                a.setSuperDecision(rs.getBoolean("super_decision"));
                a.setSuperReasoning(rs.getString("super_reasoning"));

                a.setHeadMoreInfo(rs.getBoolean("head_more_info"));
                a.setHeadDecision(rs.getBoolean("head_decision"));
                a.setHeadReasoning(rs.getString("head_reasoning"));

                a.setBencoMoreInfo(rs.getBoolean("benco_more_info"));
                a.setBencoDecision(rs.getBoolean("benco_decision"));
                a.setBencoReasoning(rs.getString("benco_reasoning"));

                approvals.add(a);

            }

            return approvals;

        }catch(SQLException e){
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Approvals> getAllByIDS(Integer staff_id, Integer approval_id) {
        List<Approvals> approvals = new ArrayList<>();

        try(Connection conn = cu.getConnection()){
            String sql = "select * from approvals where staff_id = ? and approval_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staff_id);
            ps.setInt(2, approval_id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Approvals a = new Approvals();
                a.setApprovalId(rs.getInt("approval_id"));
                a.setStaffId(rs.getInt("staff_id"));
                a.setStaffMoreInfo(rs.getString("staff_more_info"));
                a.setFinalGrade(rs.getString("final_grade"));

                a.setSuperMoreInfo(rs.getBoolean("super_more_info"));
                a.setSuperDecision(rs.getBoolean("super_decision"));
                a.setSuperReasoning(rs.getString("super_reasoning"));

                a.setHeadMoreInfo(rs.getBoolean("head_more_info"));
                a.setHeadDecision(rs.getBoolean("head_decision"));
                a.setHeadReasoning(rs.getString("head_reasoning"));

                a.setBencoMoreInfo(rs.getBoolean("benco_more_info"));
                a.setBencoDecision(rs.getBoolean("benco_decision"));
                a.setBencoReasoning(rs.getString("benco_reasoning"));

                approvals.add(a);

            }

            return approvals;

        }catch(SQLException e){
            e.printStackTrace();

        }

        return null;
    }



    //UPDATE
    @Override
    public void update(Approvals approvals) { // update an approval
        try(Connection conn = cu.getConnection()){
            String sql = "update approvals set staff_more_info = ?, final_grade = ?," +
                    "super_more_info =?, super_decision = ?, super_reasoning = ?," +
                    "head_more_info =?, head_decision = ?, head_reasoning = ?," +
                    "benco_more_info =?, benco_decision = ?, benco_reasoning = ? " +
                    "where staff_id = ? and approval_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, approvals.getStaffMoreInfo());
            ps.setString(2,approvals.getFinalGrade());

            ps.setBoolean(3, approvals.getSuperMoreInfo());
            ps.setBoolean(4, approvals.getSuperDecision());
            ps.setString(5, approvals.getSuperReasoning());

            ps.setBoolean(6, approvals.getHeadMoreInfo());
            ps.setBoolean(7, approvals.getHeadDecision());
            ps.setString(8, approvals.getHeadReasoning());

            ps.setBoolean(9, approvals.getBencoMoreInfo());
            ps.setBoolean(10, approvals.getBencoDecision());
            ps.setString(11, approvals.getBencoReasoning());

            ps.setInt(12, approvals.getStaffId());
            ps.setInt(13, approvals.getApprovalId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    //DELETE
    @Override
    public void delete(Approvals approvals) {
        try(Connection conn = cu.getConnection()){
            String sql = "delete from approvals where staff_id = ? and approval_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, approvals.getStaffId());
            ps.setInt(2, approvals.getApprovalId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
