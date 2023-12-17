package com.ecm.ecommerce.Model.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RegisterDTO implements Serializable {

    String firstName;
    String lastName;
    String email;
    String password;

}
