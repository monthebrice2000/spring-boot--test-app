package none.service.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import none.domain.UserCustom;
import none.service.dto.UserCustomDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCustomMapperImpl implements UserCustomMapper {

    @Override
    public UserCustom toEntity(UserCustomDTO dto) {
        if (dto == null) {
            return null;
        } else {
            UserCustom user = new UserCustom();
            user.setId(dto.getId());
            //user.setLogin(userDTO.getLogin());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            //user.set(userDTO.getImageUrl());
            user.setIsActived(dto.getIsActived());
            user.joinDate(dto.getJoinDate());
            user.lastLoginDate(dto.getLastLoginDate());
            user.lastLoginDateDisplay(dto.getLastLoginDateDisplay());
            user.setRole(dto.getRole());
            //String[] authorities = this.authoritiesFromStrings(dto.getAuthorities());
            user.setAuthorities(dto.getAuthorities());
            return user;
        }
    }

    @Override
    public UserCustomDTO toDto(UserCustom entity) {
        return new UserCustomDTO(entity);
    }

    @Override
    public List<UserCustom> toEntity(List<UserCustomDTO> dtoList) {
        return null;
    }

    @Override
    public List<UserCustomDTO> toDto(List<UserCustom> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(UserCustom entity, UserCustomDTO dto) {}
}
