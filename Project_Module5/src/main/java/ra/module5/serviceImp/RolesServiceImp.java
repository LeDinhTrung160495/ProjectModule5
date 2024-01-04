package ra.module5.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.module5.model.ERoles;
import ra.module5.model.Roles;
import ra.module5.repository.RolesRepository;
import ra.module5.service.RolesService;

import java.util.Optional;
@Service
public class RolesServiceImp implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;
    @Override
    public Optional<Roles> findByName(ERoles name) {
        return rolesRepository.findByRoleName(name);
    }
}
