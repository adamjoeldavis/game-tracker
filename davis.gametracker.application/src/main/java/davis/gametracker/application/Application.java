package davis.gametracker.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 * Main application entrypoint.
 * 
 * Imports all individual module configurations. Since these configurations all
 * have a standard name (ModuleConfiguration), we must ignore them for component
 * scanning. Otherwise we end up with a bunch of beans with the same
 * "ModuleConfiguration" name, and the application fails to run.
 * 
 * @author Adam Davis
 */
@SpringBootApplication
@ComponentScan(excludeFilters = { @Filter(type = FilterType.REGEX, pattern = { ".*/config/ModuleConfiguration" }) })
@Import({ davis.gametracker.controller.config.ModuleConfiguration.class })
public class Application
{
	public static void main(String... args) throws Exception
	{
		SpringApplication.run(Application.class, args);
	}
}
