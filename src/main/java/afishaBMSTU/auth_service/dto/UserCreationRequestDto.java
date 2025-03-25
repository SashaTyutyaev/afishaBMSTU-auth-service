package afishaBMSTU.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationRequestDto {
    private UUID externalId;
    private String name;
    private String surname;
    private String groupName;
}
