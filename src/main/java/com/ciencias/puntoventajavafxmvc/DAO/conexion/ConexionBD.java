package com.ciencias.puntoventajavafxmvc.DAO.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String username="server-54-admin";
    private static final String pass="musovespo54$";
    private static Connection con;

    public static Connection connection(){
        try{
            con = DriverManager.getConnection("jdbc:sqlserver://sql-server54.database.windows.net:1433;database=sql-server;user="+username+"@sql-server54;password="+pass+";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        } catch(SQLException ex){
            System.out.println(ex.toString());
        }
        return con;
    }

}
