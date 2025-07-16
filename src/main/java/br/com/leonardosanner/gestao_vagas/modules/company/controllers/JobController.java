package br.com.leonardosanner.gestao_vagas.modules.company.controllers;

import br.com.leonardosanner.gestao_vagas.modules.company.dto.CreateJobDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.leonardosanner.gestao_vagas.modules.company.entities.JobEntity;
import br.com.leonardosanner.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {
    
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "Informações da vaga")
    @Operation(summary = "Cadastro de vagas",
            description = "Cadastrar vagas dentro da empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content={
                    @Content(
                            schema = @Schema(implementation = JobEntity.class)
                    )
            })
    })
    @SecurityRequirement(name="jwt_auth")
    public void create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        JobEntity jobEntity = JobEntity.builder()
                .benefits(createJobDTO.getBenefits())
                .description(createJobDTO.getDescription())
                .level(createJobDTO.getLevel())
                .company_id(UUID.fromString(companyId.toString()))
                .build();

        this.createJobUseCase.execute(jobEntity);
    }
}
