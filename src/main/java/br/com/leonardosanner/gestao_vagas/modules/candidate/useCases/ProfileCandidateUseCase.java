package br.com.leonardosanner.gestao_vagas.modules.candidate.useCases;

import br.com.leonardosanner.gestao_vagas.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        ProfileCandidateResponseDTO candidateDTO = ProfileCandidateResponseDTO
                .builder()
                .id(candidate.getId())
                .email(candidate.getEmail())
                .description(candidate.getDescription())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .build();

        return candidateDTO;
    }

}
