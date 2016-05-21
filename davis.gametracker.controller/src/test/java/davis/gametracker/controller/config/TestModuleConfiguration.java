package davis.gametracker.controller.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Testing version of this module's configuration. Since this module is not
 * annotated as a {@link SpringBootApplication}, need to enable
 * auto-configuration manually.
 * 
 * @author Adam Davis
 */
@EnableAutoConfiguration
public class TestModuleConfiguration extends ModuleConfiguration
{

}
