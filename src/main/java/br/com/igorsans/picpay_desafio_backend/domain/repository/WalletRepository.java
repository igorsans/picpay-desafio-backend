package br.com.igorsans.picpay_desafio_backend.domain.repository;

import br.com.igorsans.picpay_desafio_backend.domain.wallet.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
