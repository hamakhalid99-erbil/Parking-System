package friendoo.calculation.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculationProperties {
    private Integer perMinute;
    private Integer perDay;
    private Integer perHour;
}
