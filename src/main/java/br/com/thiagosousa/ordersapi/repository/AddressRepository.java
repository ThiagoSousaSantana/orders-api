package br.com.thiagosousa.ordersapi.repository;

import br.com.thiagosousa.ordersapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
