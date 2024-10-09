package com.example.GerenciadorDeProduto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.GerenciadorDeProduto.Model.Usuario;
import com.example.GerenciadorDeProduto.Repository.UsuarioRepository;

import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

   
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("usuario", new Usuario()); 
        return "usuarios"; 
    }

    
    @PostMapping
    public String criarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios"; 
    }

    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get()); 
        } else {
            model.addAttribute("usuario", new Usuario()); 
        }
        model.addAttribute("usuarios", usuarioRepository.findAll()); 
        return "usuarios";
    }

   
    @PostMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setTelefone(usuarioAtualizado.getTelefone());
            usuario.setTipoUsuario(usuarioAtualizado.getTipoUsuario());
            usuarioRepository.save(usuario); 
        }
        return "redirect:/usuarios";
    }

 
    @PostMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id); 
        return "redirect:/usuarios"; 
    }
}
