package br.com.leonardosanner.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "company")
public class CompanyEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaçamento entre os termos.")
    private String username;
    
    @Email(message = "O campo [email] não foi passado na formatação correta.")
    private String email;
    
    @Length(min = 10, max = 50, message = "O campo [senha] não deve ter entre (10) e (50) caracteres.")
    private String password;
    
    private String website;
    private String name;
    private String description;

    @CreationTimestamp
    LocalDateTime createdAt;
}
