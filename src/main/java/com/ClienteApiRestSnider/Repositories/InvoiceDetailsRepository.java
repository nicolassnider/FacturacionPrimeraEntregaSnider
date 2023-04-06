package com.ClienteApiRestSnider.Repositories;

import com.ClienteApiRestSnider.Entities.InvoiceDetailsModel;
import com.ClienteApiRestSnider.Entities.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetailsModel, Long> {
    List<InvoiceDetailsModel> findAllByInvoiceId(Long invoiceId);
}
