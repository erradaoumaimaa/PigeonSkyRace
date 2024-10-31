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

    // Convertit un UserRequestDto en User
    @Mapping(target = "id", ignore = true) // ID généré automatiquement par MongoDB
    @Mapping(target = "colombiers", ignore = true) // Colonnes de colombiers non gérées ici
    User toUser(UserRequestDTO dto);

    // Convertit un User en UserResponseDto
    @Mapping(target = "colombiers", source = "colombiers.id")
    UserReponseDTO toUserResponseDto(User user);

}
