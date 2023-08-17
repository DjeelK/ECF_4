package SpaceObservation.ECF.dto;

import SpaceObservation.ECF.entity.SpaceObservation;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class ObservationResponseDTO {
    private List<SpaceObservation> spaceObservations;
    private UserDTO userDTO;
    private CelestialObjectDTO celestialObjectDTO;
}
