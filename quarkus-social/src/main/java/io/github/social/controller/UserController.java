package io.github.social.controller;


import io.github.social.dto.UserRequestDto;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserController {

    @POST
    public Response createUser(UserRequestDto userRequestDto){
        return null;
    }
}
