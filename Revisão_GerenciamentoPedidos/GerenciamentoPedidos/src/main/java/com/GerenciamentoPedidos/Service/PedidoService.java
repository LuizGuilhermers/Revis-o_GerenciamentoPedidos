package com.GerenciamentoPedidos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciamentoPedidos.Repository.PedidoRepository;
import com.GerenciamentoPedidos.entities.Pedidos;

@Service
public class PedidoService {
	private final PedidoRepository pedidoRepository;
	 
    @Autowired
    public PedidoService (PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    public List<Pedidos> buscarTodosPedidos(){
        return pedidoRepository.findAll();
    }
    public Pedidos buscarPedidosId(Long id) {
        Optional <Pedidos> Pedidos = pedidoRepository.findById(id);
        return Pedidos.orElse(null);
    }
    public Pedidos salvaPedidos (Pedidos pedidos) {
        return pedidoRepository.save(pedidos);
    }
    public Pedidos alterarPedidos(Long id, Pedidos alterarPedidos) {
        Optional <Pedidos> existePedidos = pedidoRepository.findById(id);
        if (existePedidos.isPresent()) {
            alterarPedidos.setId(id);
            return pedidoRepository.save(alterarPedidos);
        }
        return null;
 
    }
    public boolean apagarPedidos(Long id) {
        Optional <Pedidos> existePedidos = pedidoRepository.findById(id);
        if (existePedidos.isPresent()) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
