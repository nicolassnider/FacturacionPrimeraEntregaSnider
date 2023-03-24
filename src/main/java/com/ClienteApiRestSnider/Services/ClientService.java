package com.ClienteApiRestSnider.Services;

import com.ClienteApiRestSnider.Exceptions.ClientAlreadyExistsException;
import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Exceptions.ClientNotFoundException;
import com.ClienteApiRestSnider.Repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

	public ClientModel create(ClientModel newClient) throws ClientAlreadyExistsException {
		Optional<ClientModel> clientOp = this.clientRepository.findByDocNumber(newClient.getDocNumber());

		if (clientOp.isPresent()){
			log.info("El cliente que intenta agregar ya existe en la base de datos : " + newClient);
			throw new ClientAlreadyExistsException("El cliente que intenta agregar ya existe en la base de datos");
		}else {
			return this.clientRepository.save(newClient);
		}
	}

	public ClientModel update(ClientModel newModel, Long id) throws Exception {
		log.info("ID INGRESANDO : " + id);
		if (id <= 0){
			throw new Exception("El id brindado no es valido");
		}

		Optional<ClientModel> clientOp = this.clientRepository.findById(id);

		if (clientOp.isEmpty()){
			log.info("El cliente que intenta modificar no existe en la base de datos : " + newModel);
			throw new ClientNotFoundException("El cliente que intenta modificar no existe en la base de datos");
		}else {
			log.info("el cliente fue encontrado");
			ClientModel clientBd = clientOp.get();

			clientBd.setName(newModel.getName());
			clientBd.setLastName(newModel.getLastName());
			clientBd.setDocNumber(newModel.getDocNumber());

			log.info("cliente actualizado : " + clientBd);

			return this.clientRepository.save(clientBd);
		}
	}

	public ClientModel deleteClient(Long clientId) throws Exception{
		log.info("ID INGRESANDO : " +clientId);
		if (clientId <= 0){
			throw new Exception("El id brindado no es valido");
		}

		Optional<ClientModel> clientOp = this.clientRepository.findById(clientId);


		if (clientOp.isEmpty()){
			log.info("El cliente que intenta eliminar no existe en la base de datos : " );
			throw new ClientNotFoundException("El cliente que intenta modificar no existe en la base de datos");
		}else {
			log.info("el cliente fue encontrado");
			ClientModel clientBd = clientOp.get();
			clientBd.setActive(false);

			log.info("cliente desactivado : " + clientBd);

			return clientBd;
		}


	}

	public ClientModel findById(Long id) throws ClientNotFoundException {
		Optional<ClientModel> clientOp = this.clientRepository.findById(id);
		if (clientOp.isEmpty()){
			log.info("El cliente que intenta buscar no existe en la base de datos : " );
			throw new ClientNotFoundException("El cliente que intenta modificar no existe en la base de datos");
		}else {
			log.info("el cliente fue encontrado");

			return clientOp.get();
		}
	}

	public ClientModel findByDocNumber(String docNumber) throws ClientNotFoundException {
		Optional<ClientModel> clientOp = this.clientRepository.findByDocNumber(docNumber);
		if (clientOp.isEmpty()){
			log.info("El cliente que intenta buscar no existe en la base de datos : " );
			throw new ClientNotFoundException("El cliente que intenta modificar no existe en la base de datos");
		}else {
			log.info("el cliente fue encontrado");

			return clientOp.get();
		}
	}

	public List<ClientModel> findAll() {
		return clientRepository.findAll();
	}

	public int getAnios(Long id) throws ClientNotFoundException {
		Optional<ClientModel> clientOp = this.clientRepository.findById(id);
		if (clientOp.isEmpty()){
			log.info("El cliente que intenta buscar no existe en la base de datos : " );
			throw new ClientNotFoundException("El cliente que intenta modificar no existe en la base de datos");
		}
		int anioActual = LocalDate.now().getYear();
		int anioNacimiento = clientOp.get().getBirth().getYear();
		return anioActual - anioNacimiento;
	}
	
}
