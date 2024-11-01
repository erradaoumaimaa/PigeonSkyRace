package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.UserReponseDTO;
import com.pigeonskyrace.dto.request.UserRequestDTO;
import com.pigeonskyrace.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserRequestDTO userRequestDto);

    @Mapping(target = "colombiers", ignore = true)
    UserReponseDTO toUserResponseDto(User user);
}
