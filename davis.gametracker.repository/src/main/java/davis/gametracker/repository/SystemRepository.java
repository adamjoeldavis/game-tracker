package davis.gametracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import davis.gametracker.domain.db.System;

@Repository
public interface SystemRepository extends CrudRepository<System, Integer>
{

}
