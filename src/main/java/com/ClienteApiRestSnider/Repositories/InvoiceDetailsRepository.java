package com.ClienteApiRestSnider.Repositories;

import com.ClienteApiRestSnider.Entities.InvoiceDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetailsModel, Long> {
	List<InvoiceDetailsModel> findAllByInvoiceId(Long invoiceId);
	List<InvoiceDetailsModel> findAll();

}
