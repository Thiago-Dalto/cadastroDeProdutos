package com.example.GerenciadorDeProduto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GerenciadorDeProduto.Model.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
