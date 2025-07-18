package br.com.leonardosanner.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Construtor que preenche todos atributos
public class ErrorMessageDTO { // DTO = data transfer objects

    private String message; // mensagem de erro, message da annotation de verificação
    private String field; // campo que ocorreu o erro
}