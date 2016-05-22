package davis.gametracker.controller.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import davis.gametracker.controller.CrudController;
import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.json.GameData;
import davis.gametracker.service.game.GameConverter;
import davis.gametracker.service.game.GameService;

/**
 * REST controller for accessing Game-related resources
 * 
 * @author Adam Davis
 */
@RestController
@CrossOrigin // TODO remove when done testing
public class GameController
		extends CrudController<Integer, Game, GameData, GameService, GameConverter>
{
	/**
	 * @param service
	 * @param converter
	 */
	@Autowired
	public GameController(GameService service, GameConverter converter)
	{
		super(service, converter);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/games")
	public List<GameData> list()
	{
		return super.list();
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/game/{id}")
	public GameData get(@PathVariable("id") Integer primaryKey)
	{
		return super.get(primaryKey);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/game")
	public GameData add(@RequestBody GameData details)
	{
		return super.add(details);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/game/{id}")
	public GameData update(@PathVariable("id") Integer primaryKey, @RequestBody GameData details)
	{
		return super.update(primaryKey, details);
	}
}
