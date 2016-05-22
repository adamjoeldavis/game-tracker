package davis.gametracker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import davis.gametracker.domain.db.GameSystem;
import davis.gametracker.domain.json.GameSystemData;
import davis.gametracker.repository.GameSystemRepository;
import davis.gametracker.service.GameSystemConverter;
import davis.gametracker.service.GameSystemService;

@Service
public class GameSystemServiceImpl implements GameSystemService
{
	private GameSystemConverter		converter;
	private GameSystemRepository	repository;

	@Autowired
	public GameSystemServiceImpl(GameSystemConverter converter, GameSystemRepository repository)
	{
		this.converter = converter;
		this.repository = repository;
	}

	@Override
	public GameSystem create(GameSystemData data)
	{
		return repository.saveAndFlush(converter.initialize(data));
	}

	@Override
	public GameSystem update(Integer key, GameSystemData data)
	{
		GameSystem system = load(key);

		converter.populate(system, data);

		return repository.saveAndFlush(system);
	}

	@Override
	public GameSystem load(Integer key)
	{
		return repository.findOne(key);
	}

	@Override
	public GameSystem load(String id)
	{
		return repository.findById(id);
	}

	@Override
	public List<GameSystem> list()
	{
		return repository.findAll();
	}

}
