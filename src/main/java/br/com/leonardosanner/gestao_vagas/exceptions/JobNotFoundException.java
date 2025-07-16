package br.com.leonardosanner.gestao_vagas.exceptions;

import org.springframework.boot.autoconfigure.batch.BatchProperties;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(String msg) {
        super(msg);
    }
}
