package br.edu.ifrn.biblioteca.controladores;

import br.edu.ifrn.biblioteca.modelo.Usuario;
import br.edu.ifrn.biblioteca.persistencia.repositorio.UsuarioRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioControle {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioRepo.findAll());
        return "usuario/lista-usuarios"; // Aponta para o arquivo lista-usuarios.html
    }

    @GetMapping("/novo")
    public String formulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/formulario-usuario"; // Aponta para o arquivo formulario-usuario.html
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
        if (usuarioRepo.findByCpf(usuario.getCpf()).isPresent()) {
            result.rejectValue("cpf", "erro.duplicado", "Já existe um usuário com este CPF.");
        }
        if (usuarioRepo.findByEmail(usuario.getEmail()).isPresent()) {
            result.rejectValue("email", "erro.duplicado", "Já existe um usuário com este e-mail.");
        }

        if (result.hasErrors()) {
            return "usuario/formulario-usuario";
        }

        usuarioRepo.save(usuario);
        return "redirect:/usuarios";
    }
}