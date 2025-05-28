package br.com.projetoRivalixPreCadastro.projetoRivalix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.projetoRivalixPreCadastro.projetoRivalix.model")

public class ProjetoRivalixPreCadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoRivalixPreCadastroApplication.class, args);
	}

}
