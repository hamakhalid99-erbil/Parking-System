package friendoo.calculation;

import com.iamceph.resulter.core.DataResultable;
import friendoo.calculation.config.CalculationProperties;
import friendoo.calculation.models.TicketRequest;
import friendoo.calculation.repo.ReceiptRepository;
import friendoo.calculation.repo.domain.Receipt;
import friendoo.calculation.service.CalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculationApplicationTests {

    private CalculationProperties properties;
    @Mock
    private CalculationService calculationService;
    @Mock
    private ReceiptRepository receiptRepository;

    @BeforeEach
    void setUp() {
        receiptRepository = mock(ReceiptRepository.class);
        properties = mock(CalculationProperties.class);
        calculationService = new CalculationService(receiptRepository, properties);
    }

    /**
     * scenario
     */
    @Test
    void shouldCalculatePerMinute() {
        TicketRequest ticketRequest = new TicketRequest(
                "ticket1",
                10,
                LocalDateTime.now().minus(6, ChronoUnit.MINUTES),
                "paid");

        when(properties.getPerMinute()).thenReturn(4);

        DataResultable<Receipt> receiptResult = calculationService.calculateTicket(ticketRequest);
        assertThat(receiptResult.isOk()).isTrue();
        assertThat(receiptResult.data().getTotalAmount()).isEqualTo(24);
    }

    @Test
    void shouldCalculatePerHour() {
        TicketRequest ticketRequest = new TicketRequest(
                "Ticket2",
                25,
                LocalDateTime.now().minus(62, ChronoUnit.MINUTES),
                "paid");

        when(properties.getPerHour()).thenReturn(25);
        DataResultable<Receipt> receiptResult = calculationService.calculateTicket(ticketRequest);
        assertThat(receiptResult.isOk()).isTrue();
        assertThat(receiptResult.data().getTotalAmount()).isEqualTo(25);

    }

    @Test
    void shouldCalculatePerDay() {
        TicketRequest ticketRequest = new TicketRequest(
                "Ticket3",
                200,
                LocalDateTime.now().minus(3800, ChronoUnit.MINUTES),
                "paid"
        );


        when(properties.getPerDay()).thenReturn(200);
        DataResultable<Receipt> receiptResult = calculationService.calculateTicket(ticketRequest);
        assertThat(receiptResult.isOk()).isTrue();
        assertThat(receiptResult.data().getTotalAmount()).isEqualTo(200);

    }

    @Test
    void shouldIdPresent() {
        TicketRequest ticketRequest = new TicketRequest(
                "1",
                1300,
                LocalDateTime.now(), "paid");
        Receipt receipt = new Receipt("1",
                200L,
                "hour price",
                LocalDateTime.now());
        when(receiptRepository.findById(anyString())).thenReturn(Optional.of(receipt));
        DataResultable<Receipt> receiptResult = calculationService.calculateTicket(ticketRequest);
        assertThat(receiptResult.isOk()).isTrue();
        assertThat(receiptResult.data().getTotalAmount()).isEqualTo(200L);


    }
}
