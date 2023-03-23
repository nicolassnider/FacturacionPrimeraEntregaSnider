package com.ClienteApiRestSnider.Services;

import com.ClienteApiRestSnider.Exceptions.ClientAlreadyExistsException;
import com.ClienteApiRestSnider.Entities.Client;
import com.ClienteApiRestSnider.Repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

	public Client create(Client newClient) throws ClientAlreadyExistsException {
		Optional<Client> clientOp = this.clientRepository.findByDocnumber(newClient.getDocnumber());

		if (clientOp.isPresent()){
			log.info("El cliente que intenta agregar ya existe en la base de datos : " + newClient);
			throw new ClientAlreadyExistsException("El cliente que intenta agregar ya existe en la base de datos");
		}else {
			return this.clientRepository.save(newClient);
		}
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
