package br.com.leonardosanner.gestao_vagas.modules.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO { // DTO = data transfer objects

    private String message; // mensagem de erro, message da annotation de verificação
    private String field; // campo que ocorreu o erro
}