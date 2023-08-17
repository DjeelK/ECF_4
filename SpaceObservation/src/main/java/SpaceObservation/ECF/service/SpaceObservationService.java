package SpaceObservation.ECF.service;

import SpaceObservation.ECF.dto.CelestialObjectDTO;
import SpaceObservation.ECF.dto.ObservationResponseDTO;
import SpaceObservation.ECF.dto.UserDTO;
import SpaceObservation.ECF.entity.SpaceObservation;
import SpaceObservation.ECF.repository.SpaceObservationRepository;
import SpaceObservation.ECF.tool.RestClient;
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
    public ObservationResponseDTO getObservationByUserId(int userId) {
        RestClient<UserDTO, String> restClient = new RestClient<>();
        ObservationResponseDTO observationDTO = ObservationResponseDTO.builder().spaceObservations(spaceObservationRepository.findObservationsByUserId(userId))
                .userDTO(restClient.get("user/" + userId, UserDTO.class))
                .build();
        return observationDTO;
    }

    //ByCelestialId
    public ObservationResponseDTO getObservationByCelestialId(int celestialObjectId) {
        RestClient<CelestialObjectDTO, String> restClient = new RestClient<>();
        ObservationResponseDTO observationDTO = ObservationResponseDTO.builder().spaceObservations(spaceObservationRepository.findAllObservationsByObjectId(celestialObjectId))
                .celestialObjectDTO(restClient.get("celestialObject/" + celestialObjectId, CelestialObjectDTO.class))
                .build();
        return observationDTO;
    }

    //ByPlace (à la place de date)
    public List<SpaceObservation> getObservationsByPlace(String placeObservationCelestial) {
        List<SpaceObservation> spaceObservations = spaceObservationRepository.findObservationsByPlace(placeObservationCelestial);
        if (!spaceObservations.isEmpty()) {
            return spaceObservations;
        }
        throw new RuntimeException("Observations not found for the specified place");
    }
}
