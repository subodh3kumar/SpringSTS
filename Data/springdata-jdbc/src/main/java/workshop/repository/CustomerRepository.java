package workshop.repository;

import org.springframework.data.repository.CrudRepository;

import workshop.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
