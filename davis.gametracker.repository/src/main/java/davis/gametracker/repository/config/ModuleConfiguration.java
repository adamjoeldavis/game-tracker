package davis.gametracker.repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import davis.gametracker.repository.GameRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = GameRepository.class)
@Import({ davis.gametracker.domain.db.config.ModuleConfiguration.class })
public class ModuleConfiguration
{

}
