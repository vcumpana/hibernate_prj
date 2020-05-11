package com.hibernate.dao;

import com.hibernate.entities.Address;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
    //List<Address> findAddressByCityContains(String string);

    Optional<Address> findByCityStartingWith(String city);
}
