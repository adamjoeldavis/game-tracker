package davis.gametracker.repository;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import davis.gametracker.repository.config.TestModuleConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestModuleConfiguration.class)
@Transactional
public abstract class RepositoryTestBase
{

}
