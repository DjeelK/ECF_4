package SpaceObservation.ECF.repository;

import SpaceObservation.ECF.entity.SpaceObservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceObservationRepository extends CrudRepository<SpaceObservation, Integer> {
    List<SpaceObservation> findObservationsByUserId(int userId);
    List<SpaceObservation> findObservationsByPlace(String placeObservationCelestial);
    List<SpaceObservation> findAllObservationsByObjectId(int celestialObjectId);
}
