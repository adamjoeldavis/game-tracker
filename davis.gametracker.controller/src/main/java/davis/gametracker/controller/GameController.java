package davis.gametracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for accessing Game-related resources
 * 
 * @author Adam Davis
 */
@RestController
public class GameController
{
	private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(method = RequestMethod.GET, value = "/games")
	public String listGames()
	{
		log.info("In method: {}", "listGames()");

		return "listGames";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/game/{id}")
	public String getGame(@PathVariable("id") long id)
	{
		log.info("In method: {} For Key: {}", "getGame", id);

		return "getGame => " + id;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/game")
	public String addGame(Object details)
	{
		log.info("In method: {} with details {}", "addGame", details);

		return "addGame";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/game/{id}")
	public String updateGame(@PathVariable("id") long id, Object details)
	{
		log.info("In method: {} for id {} with details {}", "updateGame", id, details);

		return "updateGame => " + id;
	}
}
