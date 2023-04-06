package com.ClienteApiRestSnider.Repositories;

import com.ClienteApiRestSnider.Entities.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Long> {
    //return a list of invoices by client id using a query
    List<InvoiceModel> findAllByClientId_Id(Long clientId);


}
