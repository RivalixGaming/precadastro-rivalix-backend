package br.com.projetoRivalixPreCadastro.projetoRivalix.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Essa annotation gera automaticamente gera os getters, setters que antes tinha, reduzindo a quantidade de código

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotBlank (message = "O nome é obrigatório") // Define que o campo não pode conter string vazia " " nem ser nulo ""
	@Size (min = 3, message = "O nome deve ter no mínimo 3 caracteres")
	@Column(name = "nome", length = 200, nullable = false)
	private String nome;
	
	@Email(message = "Insira um e-mail válido") // Verifica se a string segue o formato correto de e-mail
	@NotBlank (message = "O e-mail é obrigatório")
	@Column(name = "email", length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(name = "termos_uso", nullable = false)
	private boolean termosUso;

	@Column(name = "interest", length = 50, nullable = false)
	private String interest;

}