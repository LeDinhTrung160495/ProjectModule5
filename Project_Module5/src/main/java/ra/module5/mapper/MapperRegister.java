package ra.module5.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ra.module5.dto.request.RegisterRequest;
import ra.module5.dto.response.RegisterResponse;
import ra.module5.model.ERoles;
import ra.module5.model.Roles;
import ra.module5.model.Users;
import ra.module5.service.RolesService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class MapperRegister implements MapperGeneric<Users, RegisterRequest, RegisterResponse> {
    @Autowired
    private RolesService rolesService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users mapperRequestToEntity(RegisterRequest registerRequest) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Xử lý quyền Users khi đăng ký
        Set<String> strRoles = registerRequest.getListRoles();
        Set<Roles> listRoles = new HashSet<>();
        //Chú ý: khi mà đăng ký không truyền role nào thì mặc định là ROLE_USER
        if (strRoles == null) {
            //User quyền mặc định là ROLE_USER
            Roles userRole = rolesService.findByName(ERoles.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            listRoles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Roles adminRole = rolesService.findByName(ERoles.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        listRoles.add(adminRole);
                        break;
                    case "moderator":
                        Roles modRole = rolesService.findByName(ERoles.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        listRoles.add(modRole);
                        break;
                    case "user":
                        Roles userRole = rolesService.findByName(ERoles.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        listRoles.add(userRole);
                        break;
                }
            });
        }
        return Users.builder().userName(registerRequest.getUserName())
                //Mã hóa mật khẩu khi người dùng đăng ký
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .address(registerRequest.getAddress())
                .created(sdf.format(new Date()))
                .userStatus(true)
                .listRoles(listRoles)
                .fullName(registerRequest.getFullName())
                .build();
    }

    @Override
    public RegisterResponse mapperEntityToResponse(Users users) {
        return RegisterResponse.builder().id(users.getUserId())
                .userName(users.getUserName())
                .password(users.getPassword())
                .email(users.getEmail())
                .phone(users.getPhone())
                .address(users.getAddress()).build();
    }
}
