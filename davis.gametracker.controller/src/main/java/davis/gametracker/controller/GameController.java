package davis.gametracker.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import davis.gametracker.domain.json.GameData;
import davis.gametracker.service.GameConverter;
import davis.gametracker.service.GameService;

/**
 * REST controller for accessing Game-related resources
 * 
 * @author Adam Davis
 */
@RestController
@CrossOrigin // TODO remove when done testing
public class GameController
{
	private final Logger	log	= LoggerFactory.getLogger(getClass());

	private GameService		service;
	private GameConverter	converter;

	@Autowired
	public GameController(GameService service, GameConverter converter)
	{
		assert (service != null);
		assert (converter != null);

		this.service = service;
		this.converter = converter;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/games")
	public List<GameData> listGames()
	{
		log.info("In method: {}", "listGames()");

		return service.list().stream()
				.map(converter::convert)
				.sorted(Comparator.comparing(GameData::getName))
				.collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/game/{id}")
	public GameData getGame(@PathVariable("id") int id)
	{
		log.info("In method: {} For Key: {}", "getGame", id);

		return converter.convert(service.load(id));
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/game")
	public GameData addGame(@RequestBody GameData details)
	{
		log.info("In method: {} with details {}", "addGame", details);

		return converter.convert(service.create(details));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/game/{id}")
	public GameData updateGame(@PathVariable("id") int id, @RequestBody GameData details)
	{
		log.info("In method: {} for id {} with details {}", "updateGame", id, details);

		return converter.convert(service.update(id, details));
	}
}
