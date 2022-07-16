package com.ciencias.puntoventajavafxmvc.DAO;

import com.ciencias.puntoventajavafxmvc.DAO.conexion.ConexionBD;
import com.ciencias.puntoventajavafxmvc.DTO.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean saveUser(User user){
        String sql="INSERT INTO users(name,paternal_surname,maternal_surname,email,username,password,phone,gender,birthday) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            con = ConexionBD.connection();
            ps = con.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPaternalSurname());
            ps.setString(3,user.getMaternalSurname());
            ps.setString(4,user.getEmail());
            ps.setString(5,user.getUsername());
            ps.setString(6,user.getPassword());
            ps.setString(7,user.getPhone());
            ps.setString(8, String.valueOf(user.getGender()));
            ps.setString(9,user.getBirthday());
            ps.execute();
        } catch (SQLException e){
            System.out.println(e.toString());
            return false;
        } finally {
            try{
                ps.close();
                con.close();
            } catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
        return true;
    }

    public boolean validationEmail(String email){
        String sql = "Select name From users Where email=?";
        try{
            con = ConexionBD.connection();
            ps = con.prepareStatement(sql);
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println(ex.toString());
        } finally {
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException | NullPointerException ex){
                System.out.println(ex.toString());
            }
        }
        return false;
    }

    public String recuperationPass(String email){
        String sql = "Select password from users where email=?";
        String pass = "";
        try{
            con = ConexionBD.connection();
            ps = con.prepareStatement(sql);
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()){
                pass = rs.getString("password");
            }
        } catch (SQLException e){
            System.out.println(e.toString());
            pass = "";
        } finally {
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException | NullPointerException e){
                System.out.println(e.toString());
            }
        }
        return pass;
    }

    public int recuperationIdUser(String email){
        String sql = "Select id_user from users where email=?";
        int idUser = 0;
        try{
            con = ConexionBD.connection();
            ps = con.prepareStatement(sql);
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()){
                idUser = rs.getInt("id_user");
            }
        } catch (SQLException e){
            System.out.println(e.toString());
            idUser = 0;
        } finally {
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException | NullPointerException e){
                System.out.println(e.toString());
            }
        }
        return idUser;
    }

    public boolean forgotPassword(User user){
        String sql = "Select * From users Where email=? and username=? and birthday=?";
        try{
            con = ConexionBD.connection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getBirthday());
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println(ex.toString());
        } finally {
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException | NullPointerException ex){
                System.out.println(ex.toString());
            }
        }
        return false;
    }

    public boolean updatePassword(User user){
        String sql = "Update users set password=? where email=? and username=? and birthday=?";
        try{
            con = ConexionBD.connection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getBirthday());
            int res = ps.executeUpdate();
            return true;
        } catch (SQLException | NullPointerException ex) {
            System.out.println(ex.toString());
        } finally {
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException | NullPointerException ex){
                System.out.println(ex.toString());
            }
        }
        return false;
    }

}
