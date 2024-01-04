package ra.module5.service;

import ra.module5.model.ERoles;
import ra.module5.model.Roles;

import java.util.Optional;

public interface RolesService {
    Optional<Roles> findByName(ERoles name);
}
