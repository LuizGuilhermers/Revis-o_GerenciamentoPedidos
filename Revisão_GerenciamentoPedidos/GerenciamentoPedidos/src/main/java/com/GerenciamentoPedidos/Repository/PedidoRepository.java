package com.GerenciamentoPedidos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.GerenciamentoPedidos.entities.Pedidos;

public interface PedidoRepository extends JpaRepository <Pedidos, Long> {

}
