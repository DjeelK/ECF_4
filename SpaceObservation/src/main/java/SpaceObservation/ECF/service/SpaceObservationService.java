package SpaceObservation.ECF.service;

import SpaceObservation.ECF.entity.SpaceObservation;
import SpaceObservation.ECF.repository.SpaceObservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpaceObservationService {
    private final SpaceObservationRepository spaceObservationRepository;

    public SpaceObservationService(SpaceObservationRepository spaceObservationRepository) {
        this.spaceObservationRepository = spaceObservationRepository;
    }

    //Méthode création observation
    public SpaceObservation createObservation(int userId, int celestialObjectId, String dateObservationCelestial, String placeObservationCelestial, String instrumentUsed, String viewingCondition, String descriptionCelestial) {
        SpaceObservation observation = SpaceObservation.builder()
                .userId(userId)
                .celestialObjectId(celestialObjectId)
                .dateObservationCelestial(dateObservationCelestial)
                .placeObservationCelestial(placeObservationCelestial)
                .instrumentUsed(instrumentUsed)
                .viewingCondition(viewingCondition)
                .descriptionCelestial(descriptionCelestial).build();
        spaceObservationRepository.save(observation);
        return observation;
    }

    //ByUserId
    public List<SpaceObservation> getObservationsByUserId(int userId) {
        return spaceObservationRepository.findObservationsByUserId(userId);
    }

    //ByCelestialId
    public List<SpaceObservation> getObservationsByCelestialId(int celestialObjectId) {
        return spaceObservationRepository.findAllObservationsByCelestialObjectId(celestialObjectId);
    }
    //ByPlace (à la place de date)
    public List<SpaceObservation> getObservationsByPlace(String placeObservationCelestial) {
        return spaceObservationRepository.findByPlaceObservationCelestial(placeObservationCelestial);
    }

}
