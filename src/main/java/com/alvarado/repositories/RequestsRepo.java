package com.alvarado.repositories;

import com.alvarado.models.Requests;
import com.alvarado.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//This class defines methods which will allow me to interact with the requests table in my database
public class RequestsRepo implements CrudRepository<Requests> {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    //CREATE
    @Override
    public Requests add(Requests requests) {
        try(Connection conn = cu.getConnection()){
            String sql = "insert into requests values (default,?,?,?,?,?,?,?,?,default,?,?,default)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, requests.getStaffId() );
            ps.setString(2, requests.getTodayDate());
            ps.setString(3, requests.getEventDate());
            ps.setString(4, requests.getTime());
            ps.setString(5, requests.getLocation());
            ps.setString(6, requests.getDescription());
            ps.setBigDecimal(7, requests.getCost());
            ps.setString(8, requests.getGradingFormat());
            ps.setString(9, requests.getEventType());
            ps.setString(10, requests.getJustification());
//            ps.setBoolean(11, requests.getBencoChangedCost());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //READ
    @Override
    public Requests getById(Integer staff_id) {
        try(Connection conn = cu.getConnection()){
            String sql = "select * from requests where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staff_id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Requests r = new Requests();
                r.setRequestId(rs.getInt("request_id"));
                r.setStaffId(rs.getInt("staff_id"));
                r.setTodayDate(rs.getString("today_date"));
                r.setEventDate(rs.getString("event_date"));
                r.setTime(rs.getString("time"));
                r.setLocation(rs.getString("location"));
                r.setDescription(rs.getString("description"));
                r.setCost(rs.getBigDecimal("cost"));
                r.setGradingFormat(rs.getString("grading_format"));
                r.setFinalGrade(rs.getString("final_grade"));
                r.setEventType(rs.getString("event_type"));
                r.setJustification(rs.getString("justification"));
                r.setBencoChangedCost(rs.getBoolean("benco_changed_cost"));

                return r;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Requests getByIDS(Integer staff_id, Integer request_id) {

        try(Connection conn = cu.getConnection()){
            String sql = "select * from requests where staff_id = ? and request_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staff_id);
            ps.setInt(2, request_id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Requests r = new Requests();
                r.setRequestId(rs.getInt("request_id"));
                r.setStaffId(rs.getInt("staff_id"));
                r.setTodayDate(rs.getString("today_date"));
                r.setEventDate(rs.getString("event_date"));
                r.setTime(rs.getString("time"));
                r.setLocation(rs.getString("location"));
                r.setDescription(rs.getString("description"));
                r.setCost(rs.getBigDecimal("cost"));
                r.setGradingFormat(rs.getString("grading_format"));
                r.setFinalGrade(rs.getString("final_grade"));
                r.setEventType(rs.getString("event_type"));
                r.setJustification(rs.getString("justification"));
                r.setBencoChangedCost(rs.getBoolean("benco_changed_cost"));

                return r;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Requests> getAll() {
        List<Requests> requests = new ArrayList<>();

        try(Connection conn = cu.getConnection()){
            String sql = "select * from requests";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Requests r = new Requests();
                r.setRequestId(rs.getInt("request_id"));
                r.setStaffId(rs.getInt("staff_id"));
                r.setTodayDate(rs.getString("today_date"));
                r.setEventDate(rs.getString("event_date"));
                r.setTime(rs.getString("time"));
                r.setLocation(rs.getString("location"));
                r.setDescription(rs.getString("description"));
                r.setCost(rs.getBigDecimal("cost"));
                r.setGradingFormat(rs.getString("grading_format"));
                r.setFinalGrade(rs.getString("final_grade"));
                r.setEventType(rs.getString("event_type"));
                r.setJustification(rs.getString("justification"));
                r.setBencoChangedCost(rs.getBoolean("benco_changed_cost"));

                requests.add(r);

            }
            return requests;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Requests> getAllById(Integer staffId) {
        List<Requests> requests = new ArrayList<>();

        try(Connection conn = cu.getConnection()){
            String sql = "select * from requests where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staffId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Requests r = new Requests();
                r.setRequestId(rs.getInt("request_id"));
                r.setStaffId(rs.getInt("staff_id"));
                r.setTodayDate(rs.getString("today_date"));
                r.setEventDate(rs.getString("event_date"));
                r.setTime(rs.getString("time"));
                r.setLocation(rs.getString("location"));
                r.setDescription(rs.getString("description"));
                r.setCost(rs.getBigDecimal("cost"));
                r.setGradingFormat(rs.getString("grading_format"));
                r.setFinalGrade(rs.getString("final_grade"));
                r.setEventType(rs.getString("event_type"));
                r.setJustification(rs.getString("justification"));
                r.setBencoChangedCost(rs.getBoolean("benco_changed_cost"));

                requests.add(r);

            }
            return requests;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Requests> getAllByIDS(Integer id, Integer otherId) {
        return null;
    }

    //UPDATE
    @Override
    public void update(Requests requests) { //update a request
        try(Connection conn = cu.getConnection()){
            String sql = "update requests set final_grade = ?, benco_changed_cost = ?, cost = ? where staff_id = ? and request_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, requests.getFinalGrade());
            ps.setBoolean(2,requests.getBencoChangedCost());
            ps.setBigDecimal(3, requests.getCost());
            ps.setInt(4, requests.getStaffId());
            ps.setInt(5, requests.getRequestId());

            ps.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    //DELETE
    @Override
    public void delete(Requests requests) {
        try(Connection conn = cu.getConnection()){
            String sql = "delete from requests where staff_id = ? and request_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, requests.getStaffId());
            ps.setInt(2, requests.getRequestId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
