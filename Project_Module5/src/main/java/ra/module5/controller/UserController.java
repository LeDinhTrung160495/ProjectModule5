package ra.module5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.module5.dto.request.LoginRequest;
import ra.module5.dto.request.RegisterRequest;
import ra.module5.dto.response.LoginResponse;
import ra.module5.dto.response.MessageResponse;
import ra.module5.dto.response.RegisterResponse;
import ra.module5.service.UsersService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/auth")
public class UserController {
    @Autowired
    private UsersService usersService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        boolean isExistUserName = usersService.existsByUserName(registerRequest.getUserName());
        if(isExistUserName){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: UserName is exist"));
        }
        boolean isExistEmail = usersService.existsByEmail(registerRequest.getEmail());
        if(isExistEmail){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is exist"));
        }
        RegisterResponse registerResponse = usersService.saveOrUpdate(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = usersService.login(loginRequest);
        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }
}
