package ra.module5.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.module5.model.Users;
import ra.module5.repository.UsersRepository;
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUserNameAndUserStatus(username,true);
        if(users==null){
            throw  new UsernameNotFoundException("User not found or blocked");
        }
        return CustomUserDetail.mapUserToUserDetail(users);
    }
}
