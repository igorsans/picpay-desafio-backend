package br.com.igorsans.picpay_desafio_backend.domain.authorization;

public class UnauthorizedTransactionException extends RuntimeException {
    public UnauthorizedTransactionException(String message) {
        super(message);
    }
}
