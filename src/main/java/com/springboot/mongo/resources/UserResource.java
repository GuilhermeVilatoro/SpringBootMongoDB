package com.springboot.mongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.mongo.DTO.UserDTO;
import com.springboot.mongo.domains.Post;
import com.springboot.mongo.domains.User;
import com.springboot.mongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> usuarios = userService.findAll().stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		UserDTO usuario = new UserDTO(userService.findById(id));
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User usuario = userService.findById(id);
		return ResponseEntity.ok(usuario.getPosts());
	}

	@PostMapping
	public ResponseEntity<Void> post(@RequestBody UserDTO usuario) {
		User usuarioCorrente = userService.insert(userService.fromDTO(usuario));

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioCorrente.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> put(@RequestBody UserDTO usuario, @PathVariable String id) {
		User usuarioCorrente = userService.fromDTO(usuario);
		usuarioCorrente.setId(id);		
		userService.update(usuarioCorrente);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}