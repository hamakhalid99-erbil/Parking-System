package friendoo.parkingsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Role customerRole;
    private final Role ticketRole;
    private final Role floorRole;
    private final Role slotRole;
    private final Role reportRole;


    @Autowired
    public SecurityConfig(@Qualifier("customerRole") Role customerRole,
                          @Qualifier("ticketRole") Role ticketRole,
                          @Qualifier("floorRole") Role floorRole,
                          @Qualifier("slotRole") Role slotRole,
                          @Qualifier("reportRole") Role reportRole) {
        this.customerRole = customerRole;
        this.ticketRole = ticketRole;
        this.floorRole = floorRole;
        this.slotRole = slotRole;
        this.reportRole = reportRole;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser(customerRole.getUsername())
                .password(encoder.encode(customerRole.getPassword()))
                .roles(customerRole.getRole())
                .and()
                .withUser(ticketRole.getUsername())
                .password(encoder.encode(ticketRole.getPassword()))
                .roles(ticketRole.getRole())
                .and()
                .withUser(floorRole.getUsername())
                .password(encoder.encode(floorRole.getPassword()))
                .roles(floorRole.getRole())
                .and()
                .withUser(slotRole.getUsername())
                .password(encoder.encode(slotRole.getPassword()))
                .roles(slotRole.getRole())
                .and()
                .withUser(reportRole.getUsername())
                .password(encoder.encode(reportRole.getPassword()))
                .roles(reportRole.getRole());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(customerRole.getUrl()).hasRole(customerRole.getRole())
                .antMatchers(ticketRole.getUrl()).hasRole(ticketRole.getRole())
                .antMatchers(floorRole.getUrl()).hasRole(floorRole.getRole())
                .antMatchers(slotRole.getUrl()).hasRole(slotRole.getRole())
                .antMatchers(reportRole.getUrl()).hasRole(reportRole.getRole())
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();

    }
}
