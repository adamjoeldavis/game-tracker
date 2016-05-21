package davis.gametracker.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import davis.gametracker.controller.GameController;

/**
 * Module configuration
 * 
 * @author Adam Davis
 */
@Configuration
@ComponentScan(basePackageClasses = { GameController.class })
public class ModuleConfiguration
{

}
