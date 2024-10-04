package br.com.igorsans.picpay_desafio_backend.domain.wallet;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record Wallet(@Id Long id, String fullName, Long cpf, String email, String password, int type, BigDecimal balance) {
    public Wallet debit(BigDecimal value) {
        return new Wallet(id, fullName,cpf, email, password, type, balance.subtract(value));
    }
}
