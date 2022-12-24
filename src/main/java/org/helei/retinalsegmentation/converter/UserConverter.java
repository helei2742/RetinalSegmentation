package org.helei.retinalsegmentation.converter;

import org.helei.retinalsegmentation.dto.UserDTO;
import org.helei.retinalsegmentation.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mappings({
//            @Mapping(target="strId",
//                    expression = "java(org.helei.retinalsegmentation.converter.ConvertException.userId2StrId(entity.getId()))"),
            @Mapping(target="id",source = "id"),
            @Mapping(target="username",source = "username"),
            @Mapping(target="icon",source = "icon")}
    )
    UserDTO entity2dto(User entity);

    @Mappings({
//            @Mapping(target="id",
//                    expression = "java(org.helei.retinalsegmentation.converter.ConvertException.strId2UserId(dto.getStrId()))"),
            @Mapping(target="id",source = "id"),
            @Mapping(target="username",source = "username"),
            @Mapping(target="icon",source = "icon")}
    )
    User dto2entity(UserDTO dto);

    List<UserDTO> entitylist2dto(List<User>  authorizationRole);

    List<User> dtolist2entity(List<UserDTO>  roleDTOS);
}
