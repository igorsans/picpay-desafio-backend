package br.com.igorsans.picpay_desafio_backend.domain.authorization;

import br.com.igorsans.picpay_desafio_backend.domain.transaction.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthorizerService {
    private RestClient restClient;

    public AuthorizerService(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://util.devi.tools/api/v2/authorize")
                .build();
    }

    public void authorize(Transaction transaction) {
        var response = restClient.get()
                .retrieve()
                .toEntity(Authorizaton.class);
            if(response.getStatusCode().isError() || !response.getBody().isAuthorized()) {
                throw new UnauthorizedTransactionException("Unauthorized transaction!");
        }
    }
}
