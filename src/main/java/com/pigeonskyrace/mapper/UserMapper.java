package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.UserReponseDTO;
import com.pigeonskyrace.dto.request.UserRequestDTO;
import com.pigeonskyrace.model.User;
import com.pigeonskyrace.model.Colombier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User toUser(UserRequestDTO dto);

    @Mapping(target = "colombiers", source = "colombiers")
    UserReponseDTO toUserResponseDto(User user);

    default List<ObjectId> mapColombiersToIds(List<Colombier> colombiers) {
        return colombiers != null ? colombiers.stream()
                .map(Colombier::getId)
                .collect(Collectors.toList()) : null;
    }
}
