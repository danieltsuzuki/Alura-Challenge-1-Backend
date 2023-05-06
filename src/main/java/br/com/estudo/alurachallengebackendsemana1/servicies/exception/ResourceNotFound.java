package br.com.estudo.alurachallengebackendsemana1.servicies.exception;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String msg){
        super(msg);
    }
}
