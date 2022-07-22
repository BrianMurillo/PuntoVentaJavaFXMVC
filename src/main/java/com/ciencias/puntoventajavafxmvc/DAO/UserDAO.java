package com.ciencias.puntoventajavafxmvc.DAO;

import com.ciencias.puntoventajavafxmvc.DAO.conexion.ConnectionBD;
import com.ciencias.puntoventajavafxmvc.DTO.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean saveUser(User user){
        String sql="INSERT INTO users(name,paternal_surname,maternal_surname,email,username,password,phone,gender,birthday,id_rol) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            con = ConnectionBD.connection();
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
            ps.setInt(10, user.getId_rol());
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

    public boolean updateUser(User user){
        String sql="Update users set name=?, paternal_surname=?, maternal_surname=?, email=?, username=?,phone=?,birthday=?,id_rol=? where id_user=?";
        try {
            con = ConnectionBD.connection();
            ps = con.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPaternalSurname());
            ps.setString(3,user.getMaternalSurname());
            ps.setString(4,user.getEmail());
            ps.setString(5,user.getUsername());
            ps.setString(6,user.getPhone());
            ps.setString(7,user.getBirthday());
            switch (user.getRol()){
                case "ADMIN":
                    user.setId_rol(1);
                    ps.setInt(8, user.getId_rol());
                    break;
                case "ROOT":
                    user.setId_rol(2);
                    ps.setInt(8, user.getId_rol());
                    break;
                default:
                    break;
            }
            ps.setInt(9, user.getId_user());
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

    public boolean deleteUser(int id_user){
        String sql = "Delete From users Where id_user=?";
        try{
            con = ConnectionBD.connection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ps.execute();
        } catch (SQLException | NullPointerException ex) {
            System.out.println(ex.toString());
            return false;
        } finally {
            try{
                ps.close();
                con.close();
            } catch (SQLException | NullPointerException ex){
                System.out.println(ex.toString());
            }
        }
        return true;
    }

    public boolean validationEmailUser(String email){
        String sql = "Select name From users Where email=?";
        try{
            con = ConnectionBD.connection();
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

    public String recuperationPassUser(String email){
        String sql = "Select password from users where email=?";
        String pass = "";
        try{
            con = ConnectionBD.connection();
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
            con = ConnectionBD.connection();
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

    public boolean forgotPasswordUser(User user){
        String sql = "Select * From users Where email=? and username=? and birthday=?";
        try{
            con = ConnectionBD.connection();
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

    public boolean updatePasswordUser(User user){
        String sql = "Update users set password=? where email=? and username=? and birthday=?";
        try{
            con = ConnectionBD.connection();
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
                ps.close();
                con.close();
            } catch (SQLException | NullPointerException ex){
                System.out.println(ex.toString());
            }
        }
        return false;
    }

    public List<User> findAllUser(){
        String sql = "Select id_user,name,paternal_surname,maternal_surname,email,username,phone,birthday,id_rol from users";
        ArrayList<User> listUsers = new ArrayList<>();
        try{
            con = ConnectionBD.connection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId_user(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setPaternalSurname(rs.getString("paternal_surname"));
                user.setMaternalSurname(rs.getString("maternal_surname"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPhone(rs.getString("phone"));
                user.setBirthday(rs.getString("birthday"));
                switch (rs.getInt("id_rol")){
                    case 1:
                        user.setRol(Rol.ADMIN.name());
                        user.setId_rol(1);
                        break;
                    case 2:
                        user.setRol(Rol.ROOT.name());
                        user.setId_rol(2);
                        break;
                    default:
                        break;
                }
                listUsers.add(user);
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
        return listUsers;
    }

}