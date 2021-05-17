package platform.configuration;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import platform.entity.CodeSnippet;
import platform.repository.CodeSnippetRepository;

@Configuration
public class CodeSnippetConfiguration {

	@Bean
	public CommandLineRunner commandLineRunner(CodeSnippetRepository repository) {

		return args -> repository.saveAll(List.of(
					new CodeSnippet("code 1", LocalDateTime.now(), 0, 0),
					new CodeSnippet("code 2", LocalDateTime.now(), 0, 5),
					new CodeSnippet("code 3", LocalDateTime.now(), 500, 0),
					new CodeSnippet("code 4", LocalDateTime.now(), 500, 5),
					new CodeSnippet("code 5", LocalDateTime.now(), 0, 0)));

	}

}
