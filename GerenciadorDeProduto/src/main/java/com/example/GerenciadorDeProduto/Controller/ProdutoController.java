package com.example.GerenciadorDeProduto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.GerenciadorDeProduto.Model.Produto;
import com.example.GerenciadorDeProduto.Repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "produtos";
    }

    @PostMapping
    public String criarProduto(Produto produtos) {
        produtoRepository.save(produtos);
        return "redirect:/produtos";
    }
}
