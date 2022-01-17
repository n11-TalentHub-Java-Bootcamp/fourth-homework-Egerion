package com.example.egedemirbas.User.Converter;
import com.example.egedemirbas.User.Dto.UserDto;
import com.example.egedemirbas.User.Entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(source = "id", target ="id")
    User convertUserDtoToUser(UserDto userdto);
}
