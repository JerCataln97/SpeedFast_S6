package controlador;

import modelo.Pedido;
import modelo.Repartidor;
import java.util.ArrayList;
import java.util.List;

public class ControladorPedidos {

    //Crea la lista pedidos
    private final List<Pedido> pedidos;

    //Constructor
    public ControladorPedidos() {
        pedidos = new ArrayList<>();
    }

    //Metodo para pedido a la lista
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    //Metodo para obtener los pedidos
    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }

    //Metodo para buscar pedido por el Id
    public Pedido buscarPorId(int id) {

        for (Pedido pedido : pedidos) {

            if (pedido.getId() == id) {
                return pedido;
            }
        }

        return null;
    }

    //Metodo para asignar repartidor a un pedido
    public boolean asignarRepartidor(int idPedido, Repartidor repartidor) {

        Pedido pedido = buscarPorId(idPedido);

        if (pedido == null) {
            return false;
        }

        pedido.setRepartidor(repartidor);

        return true;
    }
}

