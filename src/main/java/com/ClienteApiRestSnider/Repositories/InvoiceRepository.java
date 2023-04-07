package com.ClienteApiRestSnider.Repositories;

import com.ClienteApiRestSnider.Entities.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Long> {
	List<InvoiceModel> findAllByClientId_Id(Long clientId);

}
