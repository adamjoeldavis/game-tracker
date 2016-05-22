package davis.gametracker.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import davis.gametracker.repository.config.TestModuleConfiguration;

/**
 * Base integration test class for repositories. Handles annotation
 * configurations.
 * 
 * @author Adam Davis
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestModuleConfiguration.class)
@Transactional
public abstract class RepositoryTestBase
{

}
