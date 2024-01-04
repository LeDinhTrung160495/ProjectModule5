package ra.module5.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ra.module5.dto.request.LoginRequest;
import ra.module5.dto.request.RegisterRequest;
import ra.module5.dto.response.LoginResponse;
import ra.module5.dto.response.RegisterResponse;
import ra.module5.jwt.JwtTokenProvider;
import ra.module5.mapper.MapperRegister;
import ra.module5.model.Users;
import ra.module5.repository.UsersRepository;
import ra.module5.security.CustomUserDetail;
import ra.module5.service.UsersService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MapperRegister mapperRegister;
    //Để xác thực
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public Users findByUserNameAndUserStatus(String userName, boolean status) {
        return usersRepository.findByUserNameAndUserStatus(userName,true);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return usersRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Override
    public RegisterResponse saveOrUpdate(RegisterRequest registerRequest) {
        return mapperRegister.mapperEntityToResponse(usersRepository.save(mapperRegister.mapperRequestToEntity(registerRequest)));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetail customUserDetail =(CustomUserDetail) authentication.getPrincipal();
        //Sinh ra JWT trả về client
        String jwt = jwtTokenProvider.generateToken(customUserDetail);
        //Lấy các quyền của user
        List<String> listRoles = customUserDetail.getAuthorities().stream()
                .map(item->item.getAuthority()).collect(Collectors.toList());
        return new LoginResponse(jwt,"Bearer",customUserDetail.getUsername(), customUserDetail.getEmail(), customUserDetail.getPhone(), listRoles);
    }
}
