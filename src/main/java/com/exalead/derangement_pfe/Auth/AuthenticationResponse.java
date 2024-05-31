package com.exalead.derangement_pfe.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
  //  @JsonProperty("refresh_token")
    //private String refreshToken;
    @JsonProperty("Token_Type")
    private String tokenType;

}
