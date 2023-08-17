package CelestialObject.ECF.repository;

import CelestialObject.ECF.entity.CelestialObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CelestialObjectRepository extends CrudRepository<CelestialObject,Integer> {
}
