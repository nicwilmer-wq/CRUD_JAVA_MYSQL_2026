/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_java_mysql_2025;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Cproductos {
    
    public void AgregarProducto(JTextField nombreProducto, JTextArea descripcionProducto, JTextField precioProducto) {
     
        CConexionBD objetoConexion = new CConexionBD ();
        EntidadProducto objetoEntidadProducto = new EntidadProducto ();
        
        String consulta = "insert into productos (nombreProducto,descripcionProducto,precioProducto) values (?,?,?);";
        
        try {
            objetoEntidadProducto.setNombreProducto(nombreProducto.getText());
            objetoEntidadProducto.setDescripcionProducto(descripcionProducto.getText());
            objetoEntidadProducto.setPrecioProducto(Double.parseDouble(precioProducto.getText()));
            
            CallableStatement cs = objetoConexion.establecerConexion() .prepareCall(consulta);
            cs.setString(1, objetoEntidadProducto.getNombreProducto());
            cs.setString(2, objetoEntidadProducto.getDescripcionProducto());
            cs.setDouble(3, objetoEntidadProducto.getPrecioProducto());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se guardo correctamente");
            
        } catch (SQLException e)  {
           JOptionPane.showMessageDialog(null, "No se guardo correctamente, error: " + e.toString());
           
        }
        finally {
            objetoConexion.cerrarConexion();
        }
    }
    
    public void MostrarProducto(JTable tablaTotalProductos) {
     
        CConexionBD objetoConexion = new CConexionBD ();
        EntidadProducto objetoEntidadProducto = new EntidadProducto ();
        
        DefaultTableModel modelo = new DefaultTableModel();
                
        modelo.addColumn("id");
        modelo.addColumn("NombreProducto");
        modelo.addColumn("DescripcionProducto");
        modelo.addColumn("PrecioProducto");
        
        tablaTotalProductos.setModel(modelo);
        
      
        String consulta = "select productos.id, productos.nombreProducto, productos.descripcionProducto, productos.precioProducto from productos;";
        
        try {
            
            Statement st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                
                objetoEntidadProducto.setId(rs.getInt("id"));
                objetoEntidadProducto.setNombreProducto(rs.getString("nombreProducto"));
                objetoEntidadProducto.setDescripcionProducto(rs.getString("descripcionProducto"));
                objetoEntidadProducto.setPrecioProducto(rs.getDouble("precioProducto"));

                modelo.addRow(new Object [] {objetoEntidadProducto.getId(),objetoEntidadProducto.getNombreProducto(),objetoEntidadProducto.getDescripcionProducto(),objetoEntidadProducto.getPrecioProducto()});
                
            }
            tablaTotalProductos.setModel(modelo);
            
        } catch (SQLException e)  {
           JOptionPane.showMessageDialog(null, "Error al mostrar los datos, error: " + e.toString());
           
        }
        finally {
            objetoConexion.cerrarConexion();
        }
    }
    
    public void Seleccionar (JTable totalproductos, JTextField id, JTextField nombreProducto, JTextArea descripcionProducto, JTextField precioProducto) {
        
        int fila = totalproductos.getSelectedRow();
        
        try{
            if (fila>=0){
                id.setText(totalproductos.getValueAt(fila, 0).toString());
                nombreProducto.setText(totalproductos.getValueAt(fila, 1).toString());
                descripcionProducto.setText(totalproductos.getValueAt(fila, 2).toString());
                precioProducto.setText(totalproductos.getValueAt(fila, 3).toString());
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al seleccionar, error: "+ e.toString());
            
        }
    }
    public void ModificarProducto(JTextField id, JTextField nombreProducto, JTextArea descripcionProducto, JTextField precioProducto) {
     
        CConexionBD objetoConexion = new CConexionBD ();
        EntidadProducto objetoEntidadProducto = new EntidadProducto ();
        
        String consulta = "update productos set productos.nombreProducto = ?, productos.descripcionProducto = ?, productos.precioProducto = ? where productos.id = ?;";
        
        try {
            objetoEntidadProducto.setId(Integer.parseInt(id.getText()));
            objetoEntidadProducto.setNombreProducto(nombreProducto.getText());
            objetoEntidadProducto.setDescripcionProducto(descripcionProducto.getText());
            objetoEntidadProducto.setPrecioProducto(Double.parseDouble(precioProducto.getText()));
            
            CallableStatement cs = objetoConexion.establecerConexion() .prepareCall(consulta);
            cs.setString(1, objetoEntidadProducto.getNombreProducto());
            cs.setString(2, objetoEntidadProducto.getDescripcionProducto());
            cs.setDouble(3, objetoEntidadProducto.getPrecioProducto());
            cs.setInt(4, objetoEntidadProducto.getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se Modifico correctamente");
            
        } catch (SQLException e)  {
           JOptionPane.showMessageDialog(null, "No se Modifico correctamente, error: " + e.toString());
           
        }
        finally {
            objetoConexion.cerrarConexion();
        }
                   
     }
    
    public void LimpiarCamposProductos(JTextField id, JTextField nombreProducto, JTextArea descripcionProducto, JTextField precioProducto){
        
        id.setText("");
        nombreProducto.setText("");
        descripcionProducto.setText("");
        precioProducto.setText("");
    }
    public void EliminarProducto(JTextField id) {
     
        CConexionBD objetoConexion = new CConexionBD ();
        EntidadProducto objetoEntidadProducto = new EntidadProducto ();
        
        String consulta = "delete from productos where productos.id = ?";
        
        try {
            objetoEntidadProducto.setId(Integer.parseInt(id.getText()));
            
            
            CallableStatement cs = objetoConexion.establecerConexion() .prepareCall(consulta);
            
            cs.setInt(1, objetoEntidadProducto.getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se Elimino correctamente");
            
        } catch (SQLException e)  {
           JOptionPane.showMessageDialog(null, "No se Elimino correctamente, error: " + e.toString());
           
        }
        finally {
            objetoConexion.cerrarConexion();
        }
                   
     }
}
