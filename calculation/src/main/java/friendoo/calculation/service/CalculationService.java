package friendoo.calculation.service;

import com.iamceph.resulter.core.DataResultable;
import friendoo.calculation.config.CalculationProperties;
import friendoo.calculation.models.TicketRequest;
import friendoo.calculation.repo.ReceiptRepository;
import friendoo.calculation.repo.domain.Receipt;
import friendoo.calculation.utils.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CalculationService {
    private final ReceiptRepository receiptRepository;
    private final CalculationProperties calculationProperties;


    public DataResultable<Receipt> calculateTicket(final TicketRequest ticketRequest) {

        var id = ticketRequest.getUuid();
        if (id.isEmpty()) {
            return DataResultable.fail("No ticket Id provided");
        }
        Optional<Receipt> byId = receiptRepository.findById(id);

        if (byId.isPresent()) {
            return DataResultable.fromOptional(byId);
        }
        var createdTime = ticketRequest.getCreatedTime();
        var between = ChronoUnit.MINUTES.between(createdTime, LocalDateTime.now());
        var timeUnits = TimeConverter.convert(between);

        String description;
        long totalAmount;
        switch (timeUnits) {
            case MINUTE -> {
                description = "Price fair for minute";
                totalAmount = between * calculationProperties.getPerMinute();
            }
            case HOUR -> {
                description = "Price fair for Hour";
                totalAmount = Duration.ofMinutes(between).toHours() * calculationProperties.getPerHour();
            }
            default -> {
                description = "Price fair for day";
                totalAmount = between / 3600 * calculationProperties.getPerDay();
            }

        }
        Receipt receipt = Receipt.builder()
                .createdTime(LocalDateTime.now())
                .uuid(id)
                .description(description)
                .totalAmount(totalAmount)
                .build();
        receiptRepository.save(receipt);
        return DataResultable.ok(receipt);


    }

}
