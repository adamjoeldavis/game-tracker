package davis.gametracker.service.impl;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.json.GameData;
import davis.gametracker.domain.json.GameSystemData;
import davis.gametracker.service.game.GameConverter;
import davis.gametracker.service.gamesystem.GameSystemConverter;
import davis.gametracker.service.gamesystem.GameSystemService;

/**
 * Converter service implementation for {@link Game} entities
 * 
 * @author Adam Davis
 */
@Service
public class GameConverterImpl implements GameConverter
{
    private GameSystemConverter systemConverter;
    private GameSystemService   systemService;

    /**
     * @param systemConverter
     * @param systemService
     */
    @Autowired
    public GameConverterImpl(GameSystemConverter systemConverter, GameSystemService systemService)
    {
        Objects.requireNonNull(systemConverter);
        Objects.requireNonNull(systemService);

        this.systemConverter = systemConverter;
        this.systemService = systemService;
    }

    @Override
    public void populate(Game record, GameData data)
    {
        Objects.requireNonNull(data);
        Objects.requireNonNull(record);

        for (GameSystemData system : data.getOwnedOn())
        {
            record.ownedOn(systemService.load(system.getId()));
        }
    }

    @Override
    public Game toRecord(GameData data)
    {
        Objects.requireNonNull(data);

        Game newGame = new Game(data.getName());

        populate(newGame, data);

        return newGame;
    }

    @Override
    public GameData toView(Game record)
    {
        Objects.requireNonNull(record);

        return new GameData(record.getName())
                .setOwnedOn(record.getOwnedOn().stream()
                        .map(systemConverter::toView)
                        .collect(Collectors.toList()));
    }

}
