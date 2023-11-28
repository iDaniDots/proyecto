package escuela;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Interfaz2 {

    private JFrame frame;
    private JTextField textId;
    private JTextField textNombreCliente;
    private JTextField textEmailCliente;
    private JTextField textFechaCompra;
    private JTextField textNombreEvento;
    private JTextField textFechaEvento;
    private JTextField textLugarEvento;
    private JTextField textCantidad;
    private JTextField textPrecioUnitario;
    private JTextField textTotal;

    Conexion cn = new Conexion();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interfaz2 window = new Interfaz2();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Interfaz2() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(10, 11, 46, 14);
        frame.getContentPane().add(lblId);

        textId = new JTextField();
        textId.setBounds(133, 8, 86, 20);
        frame.getContentPane().add(textId);
        textId.setColumns(10);

        JLabel lblNombreCliente = new JLabel("Nombre Cliente");
        lblNombreCliente.setBounds(10, 42, 113, 14);
        frame.getContentPane().add(lblNombreCliente);

        textNombreCliente = new JTextField();
        textNombreCliente.setBounds(133, 39, 86, 20);
        frame.getContentPane().add(textNombreCliente);
        textNombreCliente.setColumns(10);

        JLabel lblEmailCliente = new JLabel("Email Cliente");
        lblEmailCliente.setBounds(10, 67, 113, 14);
        frame.getContentPane().add(lblEmailCliente);

        textEmailCliente = new JTextField();
        textEmailCliente.setBounds(133, 64, 86, 20);
        frame.getContentPane().add(textEmailCliente);
        textEmailCliente.setColumns(10);

        JLabel lblFechaCompra = new JLabel("Fecha Compra");
        lblFechaCompra.setBounds(10, 92, 113, 14);
        frame.getContentPane().add(lblFechaCompra);

        textFechaCompra = new JTextField();
        textFechaCompra.setBounds(133, 89, 86, 20);
        frame.getContentPane().add(textFechaCompra);
        textFechaCompra.setColumns(10);

        JLabel lblNombreEvento = new JLabel("Nombre Evento");
        lblNombreEvento.setBounds(10, 117, 113, 14);
        frame.getContentPane().add(lblNombreEvento);

        textNombreEvento = new JTextField();
        textNombreEvento.setBounds(133, 114, 86, 20);
        frame.getContentPane().add(textNombreEvento);
        textNombreEvento.setColumns(10);

        JLabel lblFechaEvento = new JLabel("Fecha Evento");
        lblFechaEvento.setBounds(10, 142, 113, 14);
        frame.getContentPane().add(lblFechaEvento);

        textFechaEvento = new JTextField();
        textFechaEvento.setBounds(133, 139, 86, 20);
        frame.getContentPane().add(textFechaEvento);
        textFechaEvento.setColumns(10);

        JLabel lblLugarEvento = new JLabel("Lugar Evento");
        lblLugarEvento.setBounds(10, 167, 113, 14);
        frame.getContentPane().add(lblLugarEvento);

        textLugarEvento = new JTextField();
        textLugarEvento.setBounds(133, 164, 86, 20);
        frame.getContentPane().add(textLugarEvento);
        textLugarEvento.setColumns(10);

        JLabel lblCantidad = new JLabel("Cantidad");
        lblCantidad.setBounds(10, 192, 113, 14);
        frame.getContentPane().add(lblCantidad);

        textCantidad = new JTextField();
        textCantidad.setBounds(133, 189, 86, 20);
        frame.getContentPane().add(textCantidad);
        textCantidad.setColumns(10);

        JLabel lblPrecioUnitario = new JLabel("Precio Unitario");
        lblPrecioUnitario.setBounds(10, 217, 113, 14);
        frame.getContentPane().add(lblPrecioUnitario);

        textPrecioUnitario = new JTextField();
        textPrecioUnitario.setBounds(133, 214, 86, 20);
        frame.getContentPane().add(textPrecioUnitario);
        textPrecioUnitario.setColumns(10);

        JLabel lblTotal = new JLabel("Total");
        lblTotal.setBounds(10, 242, 113, 14);
        frame.getContentPane().add(lblTotal);

        textTotal = new JTextField();
        textTotal.setBounds(133, 239, 86, 20);
        frame.getContentPane().add(textTotal);
        textTotal.setColumns(10);

        // Botón de Insertar
        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cn.ID = textId.getText();
                    cn.NombreCliente = textNombreCliente.getText();
                    cn.EmailCliente = textEmailCliente.getText();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    cn.FechaCompra = new java.sql.Date(dateFormat.parse(textFechaCompra.getText()).getTime());
                    cn.NombreEvento = textNombreEvento.getText();
                    cn.FechaEvento = new java.sql.Date(dateFormat.parse(textFechaEvento.getText()).getTime());

                    cn.LugarEvento = textLugarEvento.getText();
                    cn.Cantidad = textCantidad.getText();
                    cn.PrecioUnitario = textPrecioUnitario.getText();

                    // Calcular el Total antes de insertar
                    double cantidad = Double.parseDouble(cn.Cantidad);
                    double precioUnitario = Double.parseDouble(cn.PrecioUnitario);
                    double total = cantidad * precioUnitario;
                    cn.Total = Double.toString(total);

                    // Insertar el registro en la base de datos
                    cn.insertar();
                    limpiarCajas();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al insertar el registro");
                }
            }
        });
        btnInsertar.setBounds(245, 21, 148, 23);
        frame.getContentPane().add(btnInsertar);

        // Botón de Seleccionar
        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cn.ID = textId.getText();
                    cn.selecreg();
                    textNombreCliente.setText(cn.NombreCliente);
                    textEmailCliente.setText(cn.EmailCliente);
                    textFechaCompra.setText(cn.FechaCompra.toString());
                    textNombreEvento.setText(cn.NombreEvento);
                    textFechaEvento.setText(cn.FechaEvento.toString());
                    textLugarEvento.setText(cn.LugarEvento);
                    textCantidad.setText(cn.Cantidad);
                    textPrecioUnitario.setText(cn.PrecioUnitario);
                    textTotal.setText(cn.Total);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al seleccionar el registro");
                }
            }
        });
        btnSeleccionar.setBounds(245, 66, 148, 23);
        frame.getContentPane().add(btnSeleccionar);

        // Botón de Actualizar
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cn.ID = textId.getText();
                    cn.NombreCliente = textNombreCliente.getText();
                    cn.EmailCliente = textEmailCliente.getText();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    cn.FechaCompra = new java.sql.Date(dateFormat.parse(textFechaCompra.getText()).getTime());
                    cn.NombreEvento = textNombreEvento.getText();
                    cn.FechaEvento = new java.sql.Date(dateFormat.parse(textFechaEvento.getText()).getTime());

                    cn.LugarEvento = textLugarEvento.getText();
                    cn.Cantidad = textCantidad.getText();
                    cn.PrecioUnitario = textPrecioUnitario.getText();

                    // Calcular el Total antes de actualizar
                    double cantidad = Double.parseDouble(cn.Cantidad);
                    double precioUnitario = Double.parseDouble(cn.PrecioUnitario);
                    double total = cantidad * precioUnitario;
                    cn.Total = Double.toString(total);

                    // Actualizar el registro en la base de datos
                    cn.actreg();
                    limpiarCajas();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar el registro");
                }
            }
        });
        btnActualizar.setBounds(245, 101, 148, 23);
        frame.getContentPane().add(btnActualizar);

        // Botón de Eliminar
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cn.ID = textId.getText();
                    cn.elimreg();
                    limpiarCajas();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar el registro");
                }
            }
        });
        btnEliminar.setBounds(245, 144, 148, 23);
        frame.getContentPane().add(btnEliminar);

        // Botón de Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnSalir.setBounds(10, 261, 89, 23);
        frame.getContentPane().add(btnSalir);
    }

    public void limpiarCajas() {
        textId.setText("");
        textNombreCliente.setText("");
        textEmailCliente.setText("");
        textFechaCompra.setText("");
        textNombreEvento.setText("");
        textFechaEvento.setText("");
        textLugarEvento.setText("");
        textCantidad.setText("");
        textPrecioUnitario.setText("");
        textTotal.setText("");
    }
}