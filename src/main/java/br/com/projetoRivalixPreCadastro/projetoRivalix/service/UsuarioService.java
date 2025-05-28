package br.com.projetoRivalixPreCadastro.projetoRivalix.service;



import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.projetoRivalixPreCadastro.projetoRivalix.model.Usuario;
import br.com.projetoRivalixPreCadastro.projetoRivalix.repository.IUsuario;

// UsuarioService foi criado seguindo boas práticas de POO transferindo métodos que tinham no UsuarioController para essa classe

@Service
public class UsuarioService {
	
	private IUsuario repository;
	
	public UsuarioService(IUsuario repository) {
		this.repository = repository;
	}
	
	public List<Usuario> listarUsuario() {
		List<Usuario> lista = repository.findAll();
		return lista; 
	}
	
	public Usuario criarUsuario(Usuario usuario) {
        Optional<Usuario> existente = repository.findByEmail(usuario.getEmail());

        if (existente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }
        return repository.save(usuario);
    }
}
