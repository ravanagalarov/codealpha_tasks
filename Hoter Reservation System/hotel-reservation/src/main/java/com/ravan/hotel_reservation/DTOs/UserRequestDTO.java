package com.ravan.hotel_reservation.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Name can't be blank")
    @Size(max = 12, message = "Name can be consist of max 12 chars")
    private String name;

    @Email
    @NotBlank(message = "E-mail must be filled")
    private String email;

    @Pattern(regexp = "^\\+994\\d{9}$", message = "Phone Number must be in this format: +994xx xxx xx xx")
    @NotBlank(message = "Phone Number must be filled")
    private String phone;

}
