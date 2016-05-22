package davis.gametracker.controller.gamesystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import davis.gametracker.controller.CrudController;
import davis.gametracker.domain.db.GameSystem;
import davis.gametracker.domain.json.GameSystemData;
import davis.gametracker.service.gamesystem.GameSystemConverter;
import davis.gametracker.service.gamesystem.GameSystemService;

/**
 * REST controller for accessing GameSystem-related resources
 * 
 * @author Adam Davis
 */
@RestController
@CrossOrigin // TODO remove
public class GameSystemController extends
		CrudController<Integer, GameSystem, GameSystemData, GameSystemService, GameSystemConverter>
{
	/**
	 * @param service
	 * @param converter
	 */
	@Autowired
	public GameSystemController(GameSystemService service, GameSystemConverter converter)
	{
		super(service, converter);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/gameSystems")
	public List<GameSystemData> list()
	{
		return super.list();
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/gameSystem/{id}")
	public GameSystemData get(@PathVariable("id") Integer primaryKey)
	{
		return super.get(primaryKey);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/gameSystem")
	public GameSystemData add(@RequestBody GameSystemData details)
	{
		return super.add(details);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/gameSystem/{id}")
	public GameSystemData update(@PathVariable("id") Integer primaryKey,
			@RequestBody GameSystemData details)
	{
		return super.update(primaryKey, details);
	}
}
