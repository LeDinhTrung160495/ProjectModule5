package ra.module5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.module5.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users findByUserNameAndUserStatus(String userName, boolean status);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
