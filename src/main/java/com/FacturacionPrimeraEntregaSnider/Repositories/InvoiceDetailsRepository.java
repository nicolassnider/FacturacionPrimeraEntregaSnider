package com.FacturacionPrimeraEntregaSnider.Repositories;

import com.FacturacionPrimeraEntregaSnider.Entities.InvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails,Long>{
	
}
