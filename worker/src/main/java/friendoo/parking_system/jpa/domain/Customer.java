package friendoo.parking_system.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @Column(name = "id")
    private String uuid;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "pass", nullable = false)
    private String pass;
    @Column(name = "email", nullable = false, unique = true, length = 355)
    private String email;
    @Column(name = "balance", nullable = false)
    private Integer balance;

}



