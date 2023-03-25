package com.ClienteApiRestSnider.Services;

import com.ClienteApiRestSnider.Entities.IModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<C> {
	public C create(C model) throws Throwable, Exception;

	public C update(C model, Long id) throws Throwable, Exception;

	public C delete(Long id) throws Throwable, Exception;

	public C findById(Long id) throws Throwable, Exception;

	public List<C> findAll();

}
