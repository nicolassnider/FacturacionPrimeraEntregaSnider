package com.ClienteApiRestSnider.Repositories;

import com.ClienteApiRestSnider.Entities.InvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails,Long>{
	
}
