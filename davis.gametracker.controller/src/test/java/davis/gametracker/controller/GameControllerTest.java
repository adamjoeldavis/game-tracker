package davis.gametracker.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import davis.gametracker.controller.config.TestModuleConfiguration;

/**
 * Integration Tests for the {@link GameController} class
 * 
 * @author Adam Davis
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestModuleConfiguration.class)
@WebIntegrationTest(randomPort = true)
public class GameControllerTest
{
	@Value("${local.server.port}")
	private String			serverPort;

	private RestTemplate	template	= new TestRestTemplate();

	@Test
	public void testListGames()
	{
		String result = template.getForObject(constructUrl("games"), String.class);

		assertEquals("listGames", result);
	}

	@Test
	public void testGetGame()
	{
		final long gameId = 12L;

		String result = template.getForObject(constructUrl("game/" + gameId), String.class);

		assertEquals("getGame => " + gameId, result);
	}

	@Test
	public void testAddGame()
	{
		template.put(constructUrl("game"), null);
	}

	@Test
	public void testUpdateGame()
	{
		final long gameId = 11L;

		String response = template.postForObject(constructUrl("game/" + gameId), null, String.class);

		assertEquals("updateGame => " + gameId, response);
	}

	/**
	 * Utility method to construct a URL appended by the given resource.
	 * 
	 * This method will automatically add a "/" preceding the given resource
	 * 
	 * @param resource
	 * @return full URL
	 */
	private String constructUrl(String resource)
	{
		return "http://localhost:" + serverPort + "/" + resource;
	}
}
