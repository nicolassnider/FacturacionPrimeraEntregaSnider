package com.ClienteApiRestSnider.Repositories;


import com.ClienteApiRestSnider.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByDocnumber(String docNumber);
}
