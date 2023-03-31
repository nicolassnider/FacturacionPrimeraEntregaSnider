package com.ClienteApiRestSnider.Repositories;

import com.ClienteApiRestSnider.Entities.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
	Optional<ProductModel> findByCode(String code);

	Optional<ProductModel> findById(Long id);

	List<ProductModel> findAll();

}
