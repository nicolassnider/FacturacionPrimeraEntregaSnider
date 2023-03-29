package com.ClienteApiRestSnider.Repositories;

import com.ClienteApiRestSnider.Entities.ClientModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
	Optional<ClientModel> findByDocNumber(String docNumber);

	Optional<ClientModel> findById(Long id);

	List<ClientModel> findAll();

}
