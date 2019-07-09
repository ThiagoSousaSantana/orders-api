package br.com.thiagosousa.ordersapi.repository;

import br.com.thiagosousa.ordersapi.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
