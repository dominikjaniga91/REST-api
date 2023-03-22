package com.epam.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dominik_Janiga
 */
@RestController
@RequestMapping("/user")
@Api(value = "User controller")
class UserController {

    private final UserService userService;

    UserController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(response = UserResponseDto.class, produces = "application/json", value="Creates a instance of a User")
    @ApiResponse(code = 201, message = "User created", response = UserResponseDto.class)
    @PostMapping
    UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto){
        return this.userService.createUser(userRequestDto);
    }


    @ApiOperation(response = UserResponseDto.class, produces = "application/json", value="Update a instance of a User")
    @ApiResponse(code = 200, message = "User updated", response = UserResponseDto.class)
    @PutMapping
    UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto) {
        return this.userService.updateUser(userRequestDto);
    }


    @ApiOperation(value="Update a instance of a User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User deleted"),
            @ApiResponse(code = 404, message = "Not found - The user was not found")
    })
    @DeleteMapping("/{id}")
    void deleteUser(@ApiParam(value = "The user id", required = true)
                    @PathVariable(name = "id") Long id){
        this.userService.deleteUser(id);
    }

    @ApiOperation(value="Get a instance of a User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found a user"),
            @ApiResponse(code = 404, message = "Not found - The user was not found")
    })
    @GetMapping("/{id}")
    UserResponseDto getUser(@ApiParam(value = "The user id", required = true)
                            @PathVariable(name = "id") Long id){
        return this.userService.getUser(id);
    }

    @ApiOperation(value="Get list of users")
    @ApiResponse(code = 200, message = "Got list of users")
    @GetMapping
    List<UserResponseDto> getAllUser(){
        return this.userService.getAllUser();
    }
}
