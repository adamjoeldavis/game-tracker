package davis.gametracker.presentation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import davis.gametracker.presentation.home.HomeController;

@Configuration
@ComponentScan(basePackageClasses = HomeController.class)
public class ModuleConfiguration
{

}
