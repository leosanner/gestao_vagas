package br.com.leonardosanner.gestao_vagas.modules.exceptions;

public class CompanyFoundException extends RuntimeException{
    public CompanyFoundException() {
        super("A empresa já existe.");
    }
}
