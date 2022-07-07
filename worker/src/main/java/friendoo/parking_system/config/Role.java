package friendoo.parking_system.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private String role;
    private String username;
    private String password;
    private String url;
}
