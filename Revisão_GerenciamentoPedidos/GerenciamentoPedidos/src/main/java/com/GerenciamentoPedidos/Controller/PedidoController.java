package com.GerenciamentoPedidos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciamentoPedidos.Service.PedidoService;
import com.GerenciamentoPedidos.entities.Pedidos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Pedidos", description = "API REST DE GERENCIAMENTO SE PEDIDOS")
@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
	private final PedidoService pedidoServices;
	 
    @Autowired
    public PedidoController (PedidoService pedidoServices) {
        this.pedidoServices = pedidoServices;
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Localiza curso por ID")
    public ResponseEntity <Pedidos> buscaPedidosIdControlId(@PathVariable Long id){
        Pedidos pedidos = pedidoServices.buscarPedidosId(id);
        if(pedidos!= null) {
            return ResponseEntity.ok(pedidos);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    @Operation(summary = "Apresenta todos os pedidos")
    public ResponseEntity<List<Pedidos>> buscaTodosPedidosControl() {
        List<Pedidos> pedidos = pedidoServices.buscarTodosPedidos();
 
        return ResponseEntity.ok(pedidos);
    }
    
    @PostMapping
    @Operation(summary = "Cadastra um pedido")
    public ResponseEntity<Pedidos> salvaPedidoControl(@RequestBody @Valid Pedidos pedidos){
        Pedidos salvaPedidos = pedidoServices.salvaPedidos(pedidos);
 
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaPedidos);
 
    }
    @PutMapping ("/{id}")
    @Operation(summary = "altera as informações do id pedidos")
    public ResponseEntity<Pedidos> alterarPedidos(@PathVariable Long id, @RequestBody @Valid Pedidos pedidos) {
        Pedidos alterarPedidos = pedidoServices.alterarPedidos(id,pedidos);
        if (alterarPedidos  != null) {
            return ResponseEntity.ok(alterarPedidos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Apagar o id selecionado")
    public ResponseEntity<String> apagaPedidosControl(@PathVariable Long id) {
        boolean apagar = pedidoServices.apagarPedidos(id);
        if(apagar) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
	

}
