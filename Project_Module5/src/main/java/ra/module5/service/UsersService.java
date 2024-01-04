package ra.module5.service;

import ra.module5.dto.request.LoginRequest;
import ra.module5.dto.request.RegisterRequest;
import ra.module5.dto.response.LoginResponse;
import ra.module5.dto.response.RegisterResponse;
import ra.module5.model.Users;

public interface UsersService {
    Users findByUserNameAndUserStatus(String userName, boolean status);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    RegisterResponse saveOrUpdate(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
