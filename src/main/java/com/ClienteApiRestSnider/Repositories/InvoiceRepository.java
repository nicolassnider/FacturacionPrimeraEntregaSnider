package com.ClienteApiRestSnider.Repositories;

import com.ClienteApiRestSnider.Entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InvoiceRepository extends JpaRepository<Invoice,Long>{
	
}
