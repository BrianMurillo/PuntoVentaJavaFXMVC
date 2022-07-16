package com.ciencias.puntoventajavafxmvc.DAO;

import com.ciencias.puntoventajavafxmvc.DAO.conexion.ConexionBD;
import com.ciencias.puntoventajavafxmvc.DTO.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public User getUserLogin(){
        String sql = "SELECT TOP 1 * FROM login_users ORDER BY date_login DESC";
        User user = new User();
        try{
            con = ConexionBD.connection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setActive(rs.getInt("active"));
                user.setId_user(rs.getInt("id_user"));
            }
        } catch (SQLException | NullPointerException e){
            System.out.println(e.toString());
        } finally {
          try{
              rs.close();
              ps.close();
              con.close();
          }catch (SQLException ex){
              System.out.println(ex.toString());
          }
        }
        return user;
    }

    public void saveUserLogin(User user){
        String sql="INSERT INTO login_users(email,password,active,id_user) VALUES (?,?,?,?)";
        try {
            con = ConexionBD.connection();
            ps = con.prepareStatement(sql);
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getPassword());
            ps.setInt(3,user.getActive());
            ps.setInt(4,user.getId_user());
            ps.execute();
        } catch (SQLException e){
            System.out.println(e.toString());
        } finally {
            try{
                ps.close();
                con.close();
            } catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
    }
}
