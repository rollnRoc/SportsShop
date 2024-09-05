package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.dto.UserDto;

import java.util.List;

public interface UserService {
    DataResult<List<UserDto>> getAll();
    DataResult<UserDto> getById(long id);
    DataResult<UserDto> add(UserDto userDto);
    DataResult<UserDto> update(UserDto userDto);
    DataResult<String> delete(long id);
}
