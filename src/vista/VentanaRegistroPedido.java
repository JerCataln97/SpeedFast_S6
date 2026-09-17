package vista;

import controlador.ControladorPedidos;
import modelo.Pedido;
import javax.swing.*;

public class VentanaRegistroPedido extends JFrame {

    private JPanel panelRegistro;
    private JTextField txtId;
    private JTextField txtDireccion;
    private JButton btnGuardar;
    private JComboBox<String> cmbTipo;
    private JLabel lblVentRegistro;
    private JLabel lblId;
    private JLabel lblDireccion;
    private JLabel lblTipo;
    private JButton btnLimpiar;

    //Controlador
    private final ControladorPedidos controlador;

    //Constructor
    public VentanaRegistroPedido(ControladorPedidos controlador) {
        this.controlador = controlador;

        setContentPane(panelRegistro);

        configurarVentana();
        configurarComponentes();
        configurarEventos();
    }

    //Configuracion de la ventana
    private void configurarVentana() {

        setTitle("Speed Fast");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    //Configuracion de los componentes
    private void configurarComponentes() {

        cmbTipo.setModel(new DefaultComboBoxModel<>(
                new String[]{
                        "Comida",
                        "Encomienda",
                        "Express"
                }
        ));
    }

    //Configuracion de los eventos
    private void configurarEventos() {

        btnGuardar.addActionListener(e -> agregarPedido());

        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    //Metodo para agregar pedido
    private void agregarPedido() {

        try {
            //Obtiene la informacion
            String textoId = txtId.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String tipo = (String) cmbTipo.getSelectedItem();

            //Valida que el ID y Direccion no esten vacios
            if (textoId.isEmpty() || direccion.isEmpty()) {

                throw new IllegalArgumentException(
                        "Ingrese todos los datos."
                );
            }

            //Convierte ID
            int id = Integer.parseInt(textoId);

            //Valida que ID sea mayor de 0
            if (id <= 0) {
                throw new IllegalArgumentException(
                        "El ID debe ser mayor que 0."
                );
            }

            //Verifica que no existan 2 ID iguales
            for (Pedido pedido : controlador.obtenerPedidos()) {
                if (pedido.getId() == id) {
                    throw new IllegalArgumentException(
                            "Ya existe un pedido con el ID " + id + "."
                    );
                }
            }

            //Crea pedido
            Pedido nuevoPedido = new Pedido(id, direccion, tipo);

            //Guarda pedido en el controlador
            controlador.agregarPedido(nuevoPedido);

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido agregado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCampos();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe contener solamente números enteros.",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Datos no válidos",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    //Limpia los espacios
    private void limpiarCampos() {

        txtId.setText("");
        txtDireccion.setText("");
        cmbTipo.setSelectedIndex(0);
        txtId.requestFocus();
    }
}