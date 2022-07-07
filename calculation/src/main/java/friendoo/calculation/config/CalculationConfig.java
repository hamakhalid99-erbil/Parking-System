package friendoo.calculation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculationConfig {
    @Bean
    @ConfigurationProperties(prefix = "calculation")
    public CalculationProperties calculationConfig() {
        return new CalculationProperties();
    }
}
