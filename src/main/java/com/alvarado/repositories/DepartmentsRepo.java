package com.alvarado.repositories;


import com.alvarado.models.Departments;
import com.alvarado.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// This class defined methods which we will use to interact with our department table in the database
public class DepartmentsRepo implements CrudRepository<Departments> {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    //CREATE
    @Override
    public Departments add(Departments departments) {
        try(Connection conn = cu.getConnection()){
            String sql = "insert into departments values (default, ?, default)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, departments.getStaffId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //READ
    @Override
    public Departments getById(Integer staff_id) {
        try(Connection conn = cu.getConnection()){
            String sql = "select * from departments where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staff_id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Departments d = new Departments();
                d.setDepartmentId(rs.getInt("department_id"));
                d.setStaffId((rs.getInt("staff_id")));
                d.setDepartment(rs.getString("department"));

                return d;
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Departments getByIDS(Integer id, Integer otherId) {
        return null;
    }

    @Override
    public List<Departments> getAll() {
        return null;
    }

    @Override
    public List<Departments> getAllById(Integer id) {
        return null;
    }

    @Override
    public List<Departments> getAllByIDS(Integer id, Integer otherId) {
        return null;
    }

    //UPDATE
    @Override
    public void update(Departments departments) {
        try(Connection conn = cu.getConnection()){
            String sql = "update departments set department = ? where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, departments.getDepartment());
            ps.setInt(2, departments.getStaffId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    //DELETE
    @Override
    public void delete(Departments departments) {
        try(Connection conn = cu.getConnection()){
            String sql = "delete from departments where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, departments.getStaffId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
