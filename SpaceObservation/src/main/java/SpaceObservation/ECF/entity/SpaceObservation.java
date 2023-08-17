package SpaceObservation.ECF.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="space_observation")
public class SpaceObservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String dateObservationCelestial;
    private String observedCelestial;
    private String observationPlaceCelestial;
    private String instrumentUsed;
    private String viewingCondition;
    private String descriptionCelestial;
}
