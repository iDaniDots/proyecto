// Clase Conexion.java
package escuela;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/proyecto";
    private static final String usr = "root";
    private static final String pass = "";

    public String ID;
    public String NombreCliente;
    public String EmailCliente;
    public java.sql.Date FechaCompra;
    public String NombreEvento;
    public java.sql.Date FechaEvento;
    public String LugarEvento;
    public String Cantidad;
    public String PrecioUnitario;
    public String Total;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();
        }
    }

    public Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, usr, pass);
            System.out.println("Conexion correcta");
        } catch (SQLException e) {
            System.out.println("Error en conexion");
            e.printStackTrace();
        }
        return con;
    }

    public PreparedStatement insertar() {
        Conexion conexion = new Conexion();
        Connection cn = null;
        PreparedStatement pstm = null;

        try {
            cn = conexion.conectar();
            String insertar = "insert into boletos value (?,?,?,?,?,?,?,?,?,?)";
            pstm = cn.prepareStatement(insertar);
            pstm.setString(1, ID);
            pstm.setString(2, NombreCliente);
            pstm.setString(3, EmailCliente);
            pstm.setDate(4, FechaCompra);
            pstm.setString(5, NombreEvento);
            pstm.setDate(6, FechaEvento);
            pstm.setString(7, LugarEvento);
            pstm.setString(8, Cantidad);
            pstm.setString(9, PrecioUnitario);
            pstm.setString(10, Total);

            pstm.executeUpdate();

            System.out.println("Registro insertado");
            JOptionPane.showMessageDialog(null, "Registro Agregado");

        } catch (Exception e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
        }
        return pstm;
    }

    public PreparedStatement selecreg() {
        Conexion con = new Conexion();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = con.conectar();
            String seleccionar = "Select * from boletos where id=?";
            pstm = cn.prepareStatement(seleccionar);
            pstm.setString(1, ID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ID = rs.getString(1);
                NombreCliente = rs.getString(2);
                EmailCliente = rs.getString(3);
                FechaCompra = rs.getDate(4);
                NombreEvento = rs.getString(5);
                FechaEvento = rs.getDate(6);
                LugarEvento = rs.getString(7);
                Cantidad = rs.getString(8);
                PrecioUnitario = rs.getString(9);
                Total = rs.getString(10);
            }
            System.out.println("Consulta Exitosa");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Error al consultar");
        }
        return pstm;
    }

    public PreparedStatement actreg() {
        Conexion conexion = new Conexion();
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = conexion.conectar();
            String upd = "update boletos set NombreCliente=?, EmailCliente=?, FechaCompra=?, NombreEvento=?, FechaEvento=?, LugarEvento=?, Cantidad=?, PrecioUnitario=?, Total=? where id=?";
            pstm = cn.prepareStatement(upd);
            pstm.setString(1, NombreCliente);
            pstm.setString(2, EmailCliente);
            pstm.setDate(3, FechaCompra);
            pstm.setString(4, NombreEvento);
            pstm.setDate(5, FechaEvento);
            pstm.setString(6, LugarEvento);
            pstm.setString(7, Cantidad);
            pstm.setString(8, PrecioUnitario);
            pstm.setString(9, Total);
            pstm.setString(10, ID);

            pstm.executeUpdate();
            System.out.println("Registro actualizado");
            JOptionPane.showMessageDialog(null, "Registro Actualizado");
        } catch (Exception e) {
            System.out.println("Error de actualizacion");
            e.printStackTrace();
        }
        return pstm;
    }


    public PreparedStatement elimreg() {
        Conexion conexion = new Conexion();
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = conexion.conectar();
            String query = "Delete from boletos where id=?";
            ps = cn.prepareStatement(query);
            ps.setString(1, ID);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
            System.out.println("Registro Borrado");

        } catch (Exception e) {
            System.out.println("Error al borrar registro");
            e.printStackTrace();
        }
        return ps;
    }
}