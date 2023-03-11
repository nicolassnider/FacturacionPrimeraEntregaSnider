package com.FacturacionPrimeraEntregaSnider.Repositories;

import com.FacturacionPrimeraEntregaSnider.Entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InvoiceRepository extends JpaRepository<Invoice,Long>{
	
}
