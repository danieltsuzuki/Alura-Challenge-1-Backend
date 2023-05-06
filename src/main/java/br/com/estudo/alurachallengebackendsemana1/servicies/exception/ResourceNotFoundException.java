package br.com.estudo.alurachallengebackendsemana1.servicies.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
