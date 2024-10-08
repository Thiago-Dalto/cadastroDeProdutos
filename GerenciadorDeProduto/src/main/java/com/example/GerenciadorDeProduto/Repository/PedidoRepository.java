package com.example.GerenciadorDeProduto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.GerenciadorDeProduto.Model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
