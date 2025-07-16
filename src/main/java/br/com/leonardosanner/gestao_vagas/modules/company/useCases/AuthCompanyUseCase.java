package br.com.leonardosanner.gestao_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import br.com.leonardosanner.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.leonardosanner.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.leonardosanner.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.leonardosanner.gestao_vagas.modules.company.repositories.CompanyRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {
 
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException{
        CompanyEntity company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Username / Password incorrect");
            }
        );

        Boolean passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException("Missmatch between passwords");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        
        var token =  JWT
                .create()
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .withSubject(company.getId().toString())
                .sign(algorithm);

        AuthCompanyResponseDTO responseDTO = AuthCompanyResponseDTO.builder()
                .expires_in(expiresIn.toEpochMilli())
                .access_token(token)
                .build();

        return responseDTO;
    }
}
