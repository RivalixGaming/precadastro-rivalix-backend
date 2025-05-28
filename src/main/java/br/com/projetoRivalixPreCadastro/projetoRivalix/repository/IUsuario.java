package br.com.projetoRivalixPreCadastro.projetoRivalix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoRivalixPreCadastro.projetoRivalix.model.Usuario;

public interface IUsuario extends JpaRepository<Usuario, Integer>{
	 Optional<Usuario> findByEmail(String email);
}