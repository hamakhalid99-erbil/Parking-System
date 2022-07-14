package friendoo.parkingsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {

    @Bean(name = "customerRole")
    @ConfigurationProperties(prefix = "role.customer")
    public Role customerRole() {
        return new Role();
    }

    @Bean(name = "ticketRole")
    @ConfigurationProperties(prefix = "role.ticket")
    public Role ticketRole() {
        return new Role();
    }

    @Bean(name = "floorRole")
    @ConfigurationProperties(prefix = "role.floor")
    public Role floorRole() {
        return new Role();
    }

    @Bean(name = "slotRole")
    @ConfigurationProperties(prefix = "role.slot")
    public Role SlotRole() {
        return new Role();
    }

    @Bean(name = "reportRole")
    @ConfigurationProperties(prefix = "role.report")
    public Role ReportRole() {
        return new Role();
    }
}
