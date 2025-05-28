package br.com.projetoRivalixPreCadastro.projetoRivalix.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.projetoRivalixPreCadastro.projetoRivalix.model.Usuario;
import br.com.projetoRivalixPreCadastro.projetoRivalix.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuario();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<?> criarUsuario(@Valid @RequestBody Usuario usuario) {
        if (!usuario.isTermosUso()) {
            Map<String, String> erro = new HashMap<>();
            erro.put("termosUso", "Você precisa aceitar os termos de uso.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }

        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    /**
     * Trata erros de validação dos campos (ex: nome vazio, email inválido, etc)
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> tratarErrosValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();
            erros.put(campo, mensagem);
        });
        return erros;
    }

    /**
     * Trata erros lançados com ResponseStatusException, como duplicidade de e-mail, etc.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResponseStatusException.class)
    public Map<String, String> tratarConflito(ResponseStatusException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getReason());
        return erro;
    }
}
