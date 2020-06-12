package com.victorcvs.workshopmongo.sevices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorcvs.workshopmongo.domain.User;
import com.victorcvs.workshopmongo.dto.UserDTO;
import com.victorcvs.workshopmongo.repository.UserRepository;
import com.victorcvs.workshopmongo.sevices.exception.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();

	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}

	public User insert(User obj) {
		return repository.insert(obj);
	}

	public User fromDto(UserDTO obj) {
		return new User(obj.getId(), obj.getName(), obj.getEmail());
	}
}
