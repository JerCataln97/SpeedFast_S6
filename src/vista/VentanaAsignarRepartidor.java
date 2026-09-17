package vista;

import controlador.ControladorPedidos;
import modelo.Pedido;
import modelo.Repartidor;
import javax.swing.*;

public class VentanaAsignarRepartidor extends JFrame {

    private JPanel panelAsignar;
    private JLabel lblPedido;
    private JLabel lblRepartidor;
    private JComboBox<Pedido> cmbPedido;
    private JComboBox<Repartidor> cmbRepartidor;
    private JButton btnAsignar;
    private JLabel lblVentAsignar;

    //Controlador
    private final ControladorPedidos controlador;

    //Constructor
    public VentanaAsignarRepartidor(ControladorPedidos controlador) {
        this.controlador = controlador;

        setContentPane(panelAsignar);

        configurarVentana();
        configurarComponentes();
        configurarEventos();
    }

    //Configuracion de la ventana
    private void configurarVentana() {
        setTitle("Speed Fast");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    //Configuracion de los componentes
    private void configurarComponentes() {

        //Cargar los pedidos
        cmbPedido.removeAllItems();
        for (Pedido pedido : controlador.obtenerPedidos()) {
            cmbPedido.addItem(pedido);
        }

        //Crea repartidores
        cmbRepartidor.removeAllItems();
        cmbRepartidor.addItem(new Repartidor("Alberto Castro"));
        cmbRepartidor.addItem(new Repartidor("Miguel Contreras"));
        cmbRepartidor.addItem(new Repartidor("Matías Navarro"));
    }

    //Configuracion de eventos
    private void configurarEventos() {
        btnAsignar.addActionListener(e -> asignarRepartidor());
    }

    //Asignar repartidor al pedido
    private void asignarRepartidor() {

        Pedido pedSeleccionado =
                (Pedido) cmbPedido.getSelectedItem();

        Repartidor repSeleccionado =
                (Repartidor) cmbRepartidor.getSelectedItem();

        if (pedSeleccionado == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un pedido.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (repSeleccionado == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un repartidor.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean asig = controlador.asignarRepartidor(
                pedSeleccionado.getId(),
                repSeleccionado
        );

        if (asig) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pedido #" + pedSeleccionado.getId()
                            + " asignado correctamente a "
                            + repSeleccionado.getNombre()
                            + ".",
                    "Asignacion exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();
        }
    }
}