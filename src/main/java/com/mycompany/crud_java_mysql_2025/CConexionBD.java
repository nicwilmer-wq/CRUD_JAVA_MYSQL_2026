/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_java_mysql_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CConexionBD {
    Connection conectar = null;
    
    String usuario="root";
    String contrasena="123456";
    String bd="bd_productos";
    String ip="localhost";
    String puerto="3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerConexion() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasena);
           JOptionPane.showMessageDialog (null, "La conexión fue exitosa");
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null, "La conexión NO fue exitosa, error: "+e.toString());
        }
        return conectar;
    }  
    
    public void cerrarConexion() {
        
        try {
            if (conectar!= null && !conectar.isClosed()) {
               //JOptionPane.showMessageDialog (null, "La conexión fue cerrada"); 
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null, "No se logro cerrar la conexión, error: "+e.toString()); 
        }
    }
}
