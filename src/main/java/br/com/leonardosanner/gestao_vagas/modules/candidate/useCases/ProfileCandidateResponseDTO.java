package br.com.leonardosanner.gestao_vagas.modules.candidate.useCases;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileCandidateResponseDTO {

    @Schema(name = "Desenvolverdor Java")
    private String description;

    @Schema(name = "leonardo")
    private String username;

    @Schema(name = "leonardo@gmail.com")
    private String email;
    private UUID id;

    @Schema(name = "Leonardo")
    private String name;
}
