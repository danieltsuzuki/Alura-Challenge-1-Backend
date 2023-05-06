package br.com.estudo.alurachallengebackendsemana1.servicies.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg){
        super(msg);
    }
}
