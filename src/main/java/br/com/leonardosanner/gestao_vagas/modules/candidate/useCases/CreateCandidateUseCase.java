package br.com.leonardosanner.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leonardosanner.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.leonardosanner.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.leonardosanner.gestao_vagas.modules.exceptions.UserFoundException;

@Service
public class CreateCandidateUseCase {
    
    @Autowired
    private CandidateRepository candidateRepository;
    
    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(),candidate.getEmail()).ifPresent((user) -> {
            throw new UserFoundException();
        });

        return this.candidateRepository.save(candidate);
    }
}
