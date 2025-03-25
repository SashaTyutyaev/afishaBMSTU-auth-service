package afishaBMSTU.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {

    @NotNull
    @NotEmpty
    @NotBlank
    private String login;

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @NotNull
    @NotEmpty
    @NotBlank
    private String surname;

    private String groupName;

    @Size(min = 8, max = 500)
    private String password;
}
