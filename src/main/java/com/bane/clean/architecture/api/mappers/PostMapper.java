package com.bane.clean.architecture.api.mappers;

import com.bane.clean.architecture.api.dto.PostTO;
import com.bane.clean.architecture.api.entities.PostEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@Mapper

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PostMapper {

    @Mapping(target = "creationTime", ignore = true)
    @Mapping(source = "uuid", target = "id")
    PostEntity dtoToEntity(PostTO postTO);

    @Mapping(source = "id", target = "uuid")
    PostTO entityToTransferObject(PostEntity postEntity);

}
