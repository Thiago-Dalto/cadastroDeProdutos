package com.example.GerenciadorDeProduto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.GerenciadorDeProduto.Model.Pedido;
import com.example.GerenciadorDeProduto.Model.Produto;
import com.example.GerenciadorDeProduto.Repository.PedidoRepository;
import com.example.GerenciadorDeProduto.Repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public String listarPedidos(Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        List<Pedido> pedidos = pedidoRepository.findAll();

        model.addAttribute("produtos", produtos);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("pedido", new Pedido()); 
        return "pedidos";
    }

    @PostMapping
    public String criarPedido(Pedido pedido) {
        Produto produto = produtoRepository.findById(pedido.getProduto().getId())
            .orElseThrow(() -> new IllegalArgumentException("Produto inválido"));

        // Calcula o valor do pedido
        float valor = produto.getPreco() * pedido.getQuantidade();
        pedido.setValor(valor);
        pedido.setData(LocalDate.now());

        pedidoRepository.save(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/editar/{id}")
    public String editarPedido(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido inválido: " + id));

        model.addAttribute("pedido", pedido);
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("pedidos", pedidoRepository.findAll());

        return "pedidos";
    }

    @PostMapping("/editar/{id}")
    public String atualizarPedido(@PathVariable Long id, Pedido pedidoAtualizado) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido inválido: " + id));

        pedido.setProduto(pedidoAtualizado.getProduto());
        pedido.setQuantidade(pedidoAtualizado.getQuantidade());
        pedido.setStatus(pedidoAtualizado.getStatus());

        // Recalcular valor
        float valor = pedido.getProduto().getPreco() * pedido.getQuantidade();
        pedido.setValor(valor);

        pedidoRepository.save(pedido);
        return "redirect:/pedidos";
    }

    @PostMapping("/excluir/{id}")
    public String excluirPedido(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido inválido: " + id));

        pedidoRepository.delete(pedido);
        return "redirect:/pedidos";
    }
}
