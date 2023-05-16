package br.com.estudo.alurachallengebackendsemana1.servicies.exception;

public class AtLeastOneFieldNeedToBeFillException extends RuntimeException {
    public AtLeastOneFieldNeedToBeFillException(String msg) {
        super(msg);
    }
}
