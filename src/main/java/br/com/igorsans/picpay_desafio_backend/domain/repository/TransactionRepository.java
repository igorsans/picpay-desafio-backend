package br.com.igorsans.picpay_desafio_backend.domain.repository;

import br.com.igorsans.picpay_desafio_backend.domain.transaction.Transaction;
import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {

}
