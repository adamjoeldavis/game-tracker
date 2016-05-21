package davis.gametracker.domain.db.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;

import davis.gametracker.domain.db.EntityBase;

@Configuration
@EntityScan(basePackageClasses = EntityBase.class)
public class ModuleConfiguration
{

}
