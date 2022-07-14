package friendoo.parkingsystem.services;

import friendoo.parkingsystem.models.ReceiptResponse;
import friendoo.parkingsystem.models.TicketRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
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
                    log.error("error sending request to {}, message {}", url, throwable.getMessage());
                    return Mono.error(throwable);
                });

    }

    public ReceiptResponse sendCalculateCalculationRequest(TicketRequest ticketRequest) {
        return sendInternalRequest(calculationUrl, ReceiptResponse.class).block();
    }
}
