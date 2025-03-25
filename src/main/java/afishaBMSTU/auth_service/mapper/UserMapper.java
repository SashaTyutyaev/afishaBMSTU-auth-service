package afishaBMSTU.auth_service.mapper;

import afishaBMSTU.auth_service.dto.SignupRequestDto;
import afishaBMSTU.auth_service.dto.UserCreationRequestDto;
import afishaBMSTU.auth_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(SignupRequestDto signupRequestDto);

    UserCreationRequestDto toUserCreationRequest(SignupRequestDto signupRequestDto);
}
