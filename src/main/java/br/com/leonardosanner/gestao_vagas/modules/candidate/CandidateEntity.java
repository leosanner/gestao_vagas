package br.com.leonardosanner.gestao_vagas.modules.candidate;
import java.util.UUID;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data // -> cria getters e setters; importante para não deixar poluído
public class CandidateEntity {
    
    private UUID id;
    private String name;

    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    private String username;

    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
    private String password;
    
    @Email(message = "O campo [email] deve conter um email válido")
    private String email;
    private String description;
    private String curriculum;
}
