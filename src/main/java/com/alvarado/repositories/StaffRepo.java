package com.alvarado.repositories;

import com.alvarado.models.Staff;
import com.alvarado.utils.ConnectionUtil;
import com.sun.org.apache.bcel.internal.util.ClassStack;

import java.beans.PropertyEditorSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// This class defines methods that we can use to interact with our database
public class StaffRepo implements CrudRepository<Staff>{

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    //CREATE
    @Override
    public Staff add(Staff staff) {
        try(Connection conn = cu.getConnection()){
            String sql = "insert into staff values (default, ?, ?, ?, ?," +
                    "default, default, default, default)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setString(3, staff.getUsername());
            ps.setString(4, staff.getPassword());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //READ
    @Override
    public Staff getById(Integer staff_id) {
        try(Connection conn = cu.getConnection()){
            String sql = "select * from staff where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staff_id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Staff s = new Staff();
                s.setStaffId(rs.getInt("staff_id"));
                s.setFirstName(rs.getString("first_name"));
                s.setLastName(rs.getString("last_name"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setEmployee(rs.getBoolean("employee"));
                s.setSuper(rs.getBoolean("super"));
                s.setHead(rs.getBoolean("head"));
                s.setBenco(rs.getBoolean("benco"));

                return s;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Staff getByUsername(String username){
        try(Connection conn = cu.getConnection()){
            String sql = "select * from staff where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Staff s = new Staff();
                s.setStaffId(rs.getInt("staff_id"));
                s.setFirstName(rs.getString("first_name"));
                s.setLastName(rs.getString("last_name"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setEmployee(rs.getBoolean("employee"));
                s.setSuper(rs.getBoolean("super"));
                s.setHead(rs.getBoolean("head"));
                s.setBenco(rs.getBoolean("benco"));

                return s;


            }


        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Staff getByIDS(Integer id, Integer otherId) {
        return null;
    }

    @Override
    public List<Staff> getAll() {
        return null;
    }

    @Override
    public List<Staff> getAllById(Integer id) {
        return null;
    }

    @Override
    public List<Staff> getAllByIDS(Integer id, Integer otherId) {
        return null;
    }

    //UPDATE
    @Override
    public void update(Staff staff) {
        try(Connection conn = cu.getConnection()){
            String sql = "update staff set first_name = ?, last_name = ?, username = ?, password = ?, " +
                    "employee = ?, super = ?, head = ?, benco = ? where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setString(3, staff.getUsername());
            ps.setString(4, staff.getPassword());
            ps.setBoolean(5, staff.getEmployee());
            ps.setBoolean(6, staff.getSuper());
            ps.setBoolean(7, staff.getHead());
            ps.setBoolean(8, staff.getBenco());
            ps.setInt(9, staff.getStaffId());

            ps.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    //DELETE
    @Override
    public void delete(Staff staff) {
        try(Connection conn = cu.getConnection()){
            String sql = "delete from staff where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staff.getStaffId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
