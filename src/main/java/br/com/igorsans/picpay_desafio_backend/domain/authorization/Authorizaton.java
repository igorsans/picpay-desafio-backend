package br.com.igorsans.picpay_desafio_backend.domain.authorization;

public record Authorizaton(String message){
    public boolean isAuthorized() {
        return message.equals("true");
    }
}
