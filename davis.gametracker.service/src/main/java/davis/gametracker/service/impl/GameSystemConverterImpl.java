package davis.gametracker.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import davis.gametracker.domain.db.GameSystem;
import davis.gametracker.domain.json.GameSystemData;
import davis.gametracker.service.gamesystem.GameSystemConverter;

/**
 * Converter implementation for {@link GameSystem} entities
 * 
 * @author Adam Davis
 */
@Service
public class GameSystemConverterImpl implements GameSystemConverter
{
    @Override
    public void populate(GameSystem record, GameSystemData data)
    {
        Objects.requireNonNull(record);
        Objects.requireNonNull(data);

        record.setName(data.getName());
    }

    @Override
    public GameSystem toRecord(GameSystemData data)
    {
        Objects.requireNonNull(data);

        GameSystem newSystem = new GameSystem(data.getId());

        populate(newSystem, data);

        return newSystem;
    }

    @Override
    public GameSystemData toView(GameSystem record)
    {
        Objects.requireNonNull(record);

        return new GameSystemData(record.getId())
                .setName(record.getName());
    }

}
