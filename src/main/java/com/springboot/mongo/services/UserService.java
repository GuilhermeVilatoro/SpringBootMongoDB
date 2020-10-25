package com.springboot.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mongo.DTO.UserDTO;
import com.springboot.mongo.domains.User;
import com.springboot.mongo.repositories.IUserRepository;
import com.springboot.mongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private IUserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> usuario = userRepository.findById(id);
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User usuario) {
		return userRepository.save(usuario);
	}

	public User update(User usuario) {
		User usuarioCorrente = findById(usuario.getId());

		usuarioCorrente.setName(usuario.getName());
		usuarioCorrente.setEmail(usuario.getEmail());

		return userRepository.save(usuarioCorrente);
	}

	public void delete(String id) {
		userRepository.deleteById(id);
	}

	public User fromDTO(UserDTO usuarioDTO) {
		return new User(usuarioDTO.getId(), usuarioDTO.getName(), usuarioDTO.getEmail());
	}
}