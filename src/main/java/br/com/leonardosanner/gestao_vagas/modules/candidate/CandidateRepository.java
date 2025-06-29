package br.com.leonardosanner.gestao_vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID>{
    // funciona como um intermediário entre banco de dados e api, facilitando a usabilidade
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
    Optional<CandidateEntity> findByUsername(String username);
}