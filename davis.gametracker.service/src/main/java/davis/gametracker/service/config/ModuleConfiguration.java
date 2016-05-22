package davis.gametracker.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import davis.gametracker.service.impl.GameSystemConverterImpl;

@Configuration
@ComponentScan(basePackageClasses = GameSystemConverterImpl.class)
@Import(davis.gametracker.repository.config.ModuleConfiguration.class)
public class ModuleConfiguration
{

}
