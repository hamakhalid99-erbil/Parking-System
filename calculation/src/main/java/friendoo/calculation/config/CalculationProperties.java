package friendoo.calculation.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "calculation")
@Configuration
public class CalculationProperties {
    private Integer perMinute;
    private Integer perDay;
    private Integer perHour;
}
