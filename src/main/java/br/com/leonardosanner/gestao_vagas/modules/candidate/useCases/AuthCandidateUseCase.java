package br.com.leonardosanner.gestao_vagas.modules.candidate.useCases;

import br.com.leonardosanner.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.leonardosanner.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.leonardosanner.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException{
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(()-> {
                    throw new UsernameNotFoundException("Username/Password incorrect.");
                } );

        var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("javagas").
                withSubject(candidate.getId().toString()).
                withClaim("roles", Arrays.asList("CANDIDATE")).
                withExpiresAt(Instant.now().plus(Duration.ofHours(2))).
                sign(algorithm);


        return AuthCandidateResponseDTO.builder().access_token(token).build();
    }
}
