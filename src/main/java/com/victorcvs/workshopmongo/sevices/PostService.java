package com.victorcvs.workshopmongo.sevices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorcvs.workshopmongo.domain.Post;
import com.victorcvs.workshopmongo.repository.PostRepository;
import com.victorcvs.workshopmongo.sevices.exception.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}

	public List<Post> findByTitle(String text) {
		// return repository.findByTitleContainingIgnoreCase(text);
		
		return repository.searchTitle(text);
	}
}
