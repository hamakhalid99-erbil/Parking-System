package friendoo.parking_system.services;

import friendoo.parking_system.models.ReceiptResponse;
import friendoo.parking_system.models.TicketRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CalculationService {
    @Value("${calculation.url}")
    private String calculationUrl;

    public <T> Mono<T> sendInternalRequest(String url, Class<T> responseType) {
        WebClient webClient = WebClient.builder()
                .defaultHeaders(httpHeaders -> httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .build();

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(throwable -> {
                    System.out.println("throwable = " + throwable);
                    return Mono.error(throwable);
                });

    }

    public ReceiptResponse sendCalculateCalculationRequest(TicketRequest ticketRequest) {
        return sendInternalRequest(calculationUrl, ReceiptResponse.class).block();
    }
}
