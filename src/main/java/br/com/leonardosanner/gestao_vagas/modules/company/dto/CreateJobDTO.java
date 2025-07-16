package br.com.leonardosanner.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {

    @Schema(example = "Vaga para fullstack junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(example = "VA, VR, Bolsa Auxílio", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;

    @Schema(example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
