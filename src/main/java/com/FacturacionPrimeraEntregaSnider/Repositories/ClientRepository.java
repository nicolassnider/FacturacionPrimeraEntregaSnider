package com.FacturacionPrimeraEntregaSnider.Repositories;


import com.FacturacionPrimeraEntregaSnider.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
