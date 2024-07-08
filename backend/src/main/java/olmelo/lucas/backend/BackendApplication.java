package olmelo.lucas.backend;

import olmelo.lucas.backend.domain.entity.User;
import olmelo.lucas.backend.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	@Override
	public void run(String... args) throws Exception {
	}


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
