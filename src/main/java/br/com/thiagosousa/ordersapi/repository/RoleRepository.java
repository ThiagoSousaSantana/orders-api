package br.com.thiagosousa.ordersapi.repository;

import br.com.thiagosousa.ordersapi.model.Role;
import br.com.thiagosousa.ordersapi.model.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
