package br.com.igorsans.picpay_desafio_backend.domain.notification;

import br.com.igorsans.picpay_desafio_backend.domain.transaction.Transaction;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationProducer notificationProducer;

    public NotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    public void notify(Transaction transaction){
        notificationProducer.sendNotification(transaction);
    }
}
