package com.alvarado.repositories;


import com.alvarado.models.Balance;
import com.alvarado.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// this class defines methods which we will use to interact with our balance table in the database
public class BalanceRepo implements CrudRepository<Balance> {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    //CREATE
    @Override
    public Balance add(Balance balance) {//add a new balance for a staff member to the table
        try(Connection conn = cu.getConnection()){
            String sql = "insert into balance values (default, ?, default, default, default)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, balance.getStaffId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //READ
    @Override
    public Balance getById(Integer staff_id) {// get a staff's balance by their ID
        try(Connection conn = cu.getConnection()){
            String sql = "select * from balance where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staff_id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Balance b = new Balance();
                b.setBalanceId(rs.getInt("balance_id"));
                b.setStaffId(rs.getInt("staff_id"));
                b.setAvailable(rs.getBigDecimal("available"));
                b.setPending(rs.getBigDecimal("pending"));
                b.setAwarded(rs.getBigDecimal("awarded"));

                return b;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Balance getByIDS(Integer id, Integer otherId) {
        return null;
    }

    @Override
    public List<Balance> getAll() {
        List<Balance> balance = new ArrayList<>();

        try (Connection conn = cu.getConnection()){
            String sql = "select * from balance";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Balance b = new Balance();

                b.setBalanceId(rs.getInt("balance_id"));
                b.setStaffId(rs.getInt("staff_id"));
                b.setAvailable(rs.getBigDecimal("available"));
                b.setPending(rs.getBigDecimal("pending"));
                b.setAwarded(rs.getBigDecimal("awarded"));

                balance.add(b);
            }

            return balance;


        }catch(SQLException e){
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Balance> getAllById(Integer id) {
        return null;
    }

    @Override
    public List<Balance> getAllByIDS(Integer id, Integer otherId) {
        return null;
    }

    //UPDATE
    @Override
    public void update(Balance balance) {
        try(Connection conn = cu.getConnection()){
            String sql = "update balance set available = ?, pending = ?, awarded = ? where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, balance.getAvailable());
            ps.setBigDecimal(2, balance.getPending());
            ps.setBigDecimal(3, balance.getAwarded());
            ps.setInt(4, balance.getStaffId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    //DELETE
    @Override
    public void delete(Balance balance) {
        try(Connection conn = cu.getConnection()){
            String sql = "delete from balance where staff_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, balance.getStaffId());


        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
