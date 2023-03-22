package com.FacturacionPrimeraEntregaSnider.Services;

import com.FacturacionPrimeraEntregaSnider.Entities.Client;

import com.FacturacionPrimeraEntregaSnider.Exceptions.ClientAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import com.FacturacionPrimeraEntregaSnider.Repositories.ClientRepository;
@Slf4j
@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

	public Client create(Client newClient) throws ClientAlreadyExistsException {
		Optional<Client> clientOp = this.clientRepository.findByDocNumber(newClient.getDocNumber());

		if (clientOp.isPresent()){
			log.info("El Cliente que intenta agregar ya existe en la base de datos : " + newClient);
			throw new ClientAlreadyExistsException("El Cliente que intenta agregar ya existe en la base de datos");
		}
		return this.clientRepository.save(newClient);
	}

	public Client updateClient(Client client) {
		return clientRepository.save(client);
	}

	public void deleteClient(Client client) {
		clientRepository.delete(client);
	}

	public Client findById(Long id) {
		return clientRepository.findById(id).orElse(null);
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
}
