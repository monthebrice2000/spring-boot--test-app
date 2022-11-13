package none.service.mapper;

import none.domain.UserCustom;
import none.service.dto.UserCustomDTO;
import org.mapstruct.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity {@link UserCustom} and its DTO {@link UserCustomDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserCustomMapper extends EntityMapper<UserCustomDTO, UserCustom> {}
