package vista;

import controlador.ControladorPedidos;
import modelo.Pedido;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private JPanel panelLista;
    private JLabel lblVentListar;

    private DefaultTableModel modeloTabla;

    //Controlador
    private final ControladorPedidos controlador;

    //Constructor
    public VentanaListaPedidos(ControladorPedidos controlador) {
        this.controlador = controlador;

        setContentPane(panelLista);

        configurarVentana();
        configurarComponentes();
    }

    //Configuracion de la ventana
    private void configurarVentana() {

        setTitle("Speed Fast");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    //Configuracion de los componentes
    private void configurarComponentes() {

        String[] columnas = {
                "ID",
                "Dirección",
                "Tipo",
                "Repartidor"
        };

        //Crea el modelo de la tabla no editable
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPedidos.setModel(modeloTabla);

        actualizarTabla();
    }

    //Metodo que llena la tabla con los pedidos existentes
    public void actualizarTabla() {

        modeloTabla.setRowCount(0);

        for (Pedido pedido : controlador.obtenerPedidos()) {

            String nombreRepartidor;

            if (pedido.getRepartidor() == null) {
                nombreRepartidor = "Sin asignar";
            } else {
                nombreRepartidor =
                        pedido.getRepartidor().getNombre();
            }

            Object[] fila = {
                    pedido.getId(),
                    pedido.getDireccionEntrega(),
                    pedido.getTipo(),
                    nombreRepartidor
            };

            modeloTabla.addRow(fila);
        }
    }
}