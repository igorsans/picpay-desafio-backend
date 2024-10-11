package br.com.igorsans.picpay_desafio_backend.domain.transaction;

import br.com.igorsans.picpay_desafio_backend.domain.authorization.AuthorizerService;
import br.com.igorsans.picpay_desafio_backend.domain.notification.NotificationService;
import br.com.igorsans.picpay_desafio_backend.domain.transaction.InvalidTransactionException;
import br.com.igorsans.picpay_desafio_backend.domain.repository.TransactionRepository;
import br.com.igorsans.picpay_desafio_backend.domain.repository.WalletRepository;
import br.com.igorsans.picpay_desafio_backend.domain.transaction.Transaction;
import br.com.igorsans.picpay_desafio_backend.domain.wallet.Wallet;
import br.com.igorsans.picpay_desafio_backend.domain.wallet.WalletType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, AuthorizerService authorizerService, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction create(Transaction transaction) {

        validate(transaction);
        var newTransaction = transactionRepository.save(transaction);

        var walletPayer = walletRepository.findById(transaction.payer()).get();
        var walletPayee = walletRepository.findById(transaction.payer()).get();

        walletRepository.save(walletPayer.debit(transaction.value()));
        walletRepository.save(walletPayee.credit(transaction.value()));

        // Chamar serviço autorizador

        authorizerService.authorize(transaction);

        // notificação
        notificationService.notify(transaction);

        return newTransaction;
    };

    public void validate(Transaction transaction) {
        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payer())
                    .map(payer -> isTransactionValid(transaction, payer) ? transaction : null)
                        .orElseThrow(() -> new InvalidTransactionException("Invalid transaction - %s".formatted(transaction))))
                .orElseThrow(() -> new InvalidTransactionException("Invalid transaction - %s".formatted(transaction)));
    }

    public boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMUM.getValue() &&
                payer.balance().compareTo(transaction.value()) >= 0 &&
                !payer.id().equals(transaction.payee());
    }
    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
