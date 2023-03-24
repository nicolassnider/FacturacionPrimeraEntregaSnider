package com.ClienteApiRestSnider.Repositories;


import com.ClienteApiRestSnider.Entities.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientModel,Long> {
    Optional<ClientModel> findByDocNumber(String docNumber);
}
