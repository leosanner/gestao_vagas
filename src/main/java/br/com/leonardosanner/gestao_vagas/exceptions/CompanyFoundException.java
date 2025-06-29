package br.com.leonardosanner.gestao_vagas.exceptions;

public class CompanyFoundException extends RuntimeException{
    public CompanyFoundException() {
        super("A empresa já existe.");
    }
}
