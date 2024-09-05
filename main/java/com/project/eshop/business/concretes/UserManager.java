package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.UserService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.ErrorDataResult;
import com.project.eshop.core.utilities.results.SuccessDataResult;
import com.project.eshop.dataAccess.UserRepository;
import com.project.eshop.entities.concretes.User;
import com.project.eshop.entities.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserManager(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<UserDto>> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(userDtos, "Users retrieved successfully");
    }

    @Override
    public DataResult<UserDto> getById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return new ErrorDataResult<>("User not found");
        }
        User user = userOptional.get();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return new SuccessDataResult<>(userDto, "User retrieved successfully");
    }

    @Override
    public DataResult<UserDto> add(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return new SuccessDataResult<>(savedUserDto, "User added successfully");
    }

    @Override
    public DataResult<UserDto> update(UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userDto.getId());
        if (userOptional.isEmpty()) {
            return new ErrorDataResult<>("User not found");
        }
        User user = userOptional.get();
        modelMapper.map(userDto, user);  // Update the user entity with new details
        User updatedUser = userRepository.save(user);
        UserDto updatedUserDto = modelMapper.map(updatedUser, UserDto.class);
        return new SuccessDataResult<>(updatedUserDto, "User updated successfully");
    }

    @Override
    public DataResult<String> delete(long id) {
        userRepository.deleteById(id);
        return new SuccessDataResult<>("User deleted successfully");
    }
}
