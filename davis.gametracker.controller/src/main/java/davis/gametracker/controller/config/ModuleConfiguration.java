package davis.gametracker.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import davis.gametracker.controller.GameController;

/**
 * Module configuration
 * 
 * @author Adam Davis
 */
@Configuration
@ComponentScan(basePackageClasses = { GameController.class })
@Import(davis.gametracker.service.config.ModuleConfiguration.class)
public class ModuleConfiguration
{

}
