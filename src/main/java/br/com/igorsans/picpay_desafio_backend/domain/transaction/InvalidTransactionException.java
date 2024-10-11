package br.com.igorsans.picpay_desafio_backend.domain.transaction;

public class InvalidTransactionException extends RuntimeException{
    public InvalidTransactionException(String message) {
        super(message);
    }
}
