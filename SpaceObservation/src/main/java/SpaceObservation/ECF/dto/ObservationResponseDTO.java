package SpaceObservation.ECF.dto;

import SpaceObservation.ECF.entity.SpaceObservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Builder

public class ObservationResponseDTO {
    private List<SpaceObservation> spaceObservations;
    private UserDTO userDTO;
    private CelestialObjectDTO celestialObjectDTO;
}
