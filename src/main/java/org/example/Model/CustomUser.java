package org.example.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.UserRole;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomUser {
    private long id;
    private String userName;
    private String email;
    private UserRole userRole;

}
