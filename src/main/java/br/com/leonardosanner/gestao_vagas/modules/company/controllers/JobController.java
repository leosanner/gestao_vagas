package br.com.leonardosanner.gestao_vagas.modules.company.controllers;

import br.com.leonardosanner.gestao_vagas.modules.company.dto.CreateJobDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.leonardosanner.gestao_vagas.modules.company.entities.JobEntity;
import br.com.leonardosanner.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {
    
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
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
