package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.UserReponseDTO;
import com.pigeonskyrace.dto.request.UserRequestDTO;
import com.pigeonskyrace.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

   // User toUser(UserRequestDTO dto);

   // UserReponseDTO toUserResponseDto(User user);

}
