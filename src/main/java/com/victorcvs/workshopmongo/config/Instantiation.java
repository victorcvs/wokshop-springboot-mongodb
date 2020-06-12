package com.victorcvs.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victorcvs.workshopmongo.domain.Post;
import com.victorcvs.workshopmongo.domain.User;
import com.victorcvs.workshopmongo.dto.AuthorDTO;
import com.victorcvs.workshopmongo.dto.CommentDTO;
import com.victorcvs.workshopmongo.repository.PostRepository;
import com.victorcvs.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userReposiroty;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		postRepository.deleteAll();
		userReposiroty.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userReposiroty.saveAll(Arrays.asList(maria, alex, bob));

		AuthorDTO mariaDto = new AuthorDTO(maria);
		AuthorDTO alexDto = new AuthorDTO(alex);
		AuthorDTO bobDto = new AuthorDTO(bob);

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",
				mariaDto);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje", mariaDto);

		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), alexDto);
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), bobDto);
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), alexDto);

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);

		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userReposiroty.save(maria);

	}

}
