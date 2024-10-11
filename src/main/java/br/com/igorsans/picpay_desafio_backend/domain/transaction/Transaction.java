package br.com.igorsans.picpay_desafio_backend.domain.transaction;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalTime;


@Table("TRANSACTIONS")
public record Transaction(@Id Long id, Long payer, Long payee, BigDecimal value, @CreatedDate LocalTime createdAt) {
    public Transaction {
        value = value.setScale(2);
    }
}
