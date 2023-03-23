package com.ClienteApiRestSnider.Repositories;

import com.ClienteApiRestSnider.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product,Long>{
	
}
