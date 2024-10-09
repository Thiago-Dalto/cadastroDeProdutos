package com.example.GerenciadorDeProduto.Repository;

import com.example.GerenciadorDeProduto.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
