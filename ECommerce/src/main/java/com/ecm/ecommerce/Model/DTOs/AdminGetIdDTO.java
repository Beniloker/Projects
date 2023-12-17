package com.ecm.ecommerce.Model.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AdminGetIdDTO {

    private String id;



}
