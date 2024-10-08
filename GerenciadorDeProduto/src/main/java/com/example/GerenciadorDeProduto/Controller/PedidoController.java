package com.example.GerenciadorDeProduto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.GerenciadorDeProduto.Model.Pedido;
import com.example.GerenciadorDeProduto.Repository.PedidoRepository;
import com.example.GerenciadorDeProduto.Repository.ProdutoRepository;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        model.addAttribute("produtos", produtoRepository.findAll());
        return "pedidos";
    }

    @PostMapping
    public String criarPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
        return "redirect:/pedidos";
    }
}
