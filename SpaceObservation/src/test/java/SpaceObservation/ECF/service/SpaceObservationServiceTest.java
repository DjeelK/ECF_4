package SpaceObservation.ECF.service;

import SpaceObservation.ECF.entity.SpaceObservation;
import SpaceObservation.ECF.repository.SpaceObservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class SpaceObservationServiceTest {

    @Mock
    private SpaceObservationRepository spaceObservationRepository;

    private SpaceObservationService spaceObservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        spaceObservationService = new SpaceObservationService(spaceObservationRepository);
    }

    @Test
    public void testCreateObservation() {
        SpaceObservation expectedObservation = SpaceObservation.builder()
                .id(1)
                .userId(1)
                .celestialObjectId(1)
                .dateObservationCelestial("2023-08-18")
                .placeObservationCelestial("Observatoire")
                .instrumentUsed("Telescope")
                .viewingCondition("Ciel découvert")
                .descriptionCelestial("Ciel étoilé magnifique")
                .build();

        Mockito.when(spaceObservationRepository.save(Mockito.any(SpaceObservation.class)))
                .thenReturn(expectedObservation);

        SpaceObservation actualObservation = spaceObservationService.createObservation(
                1, 1, "2023-08-18", "Observatoire",
                "Telescope", "Ciel découvert", "Ciel étoilé magnifique");


        Assertions.assertEquals(expectedObservation.getUserId(), actualObservation.getUserId());
        Assertions.assertEquals(expectedObservation.getCelestialObjectId(), actualObservation.getCelestialObjectId());
        Assertions.assertEquals(expectedObservation.getDateObservationCelestial(), actualObservation.getDateObservationCelestial());
        Assertions.assertEquals(expectedObservation.getPlaceObservationCelestial(), actualObservation.getPlaceObservationCelestial());
        Assertions.assertEquals(expectedObservation.getInstrumentUsed(), actualObservation.getInstrumentUsed());
        Assertions.assertEquals(expectedObservation.getViewingCondition(), actualObservation.getViewingCondition());
        Assertions.assertEquals(expectedObservation.getDescriptionCelestial(), actualObservation.getDescriptionCelestial());
    }

    @Test
    public void testGetObservationsByUserId() {
        List<SpaceObservation> expectedObservations = new ArrayList<>();
        expectedObservations.add(SpaceObservation.builder().id(1).build());
        expectedObservations.add(SpaceObservation.builder().id(2).build());

        Mockito.when(spaceObservationRepository.findObservationsByUserId(1))
                .thenReturn(expectedObservations);

        List<SpaceObservation> actualObservations = spaceObservationService.getObservationsByUserId(1);

        Assertions.assertEquals(expectedObservations, actualObservations);
    }

    @Test
    public void testGetObservationsByCelestialId() {
        List<SpaceObservation> expectedObservations = new ArrayList<>();
        expectedObservations.add(SpaceObservation.builder().id(1).build());
        expectedObservations.add(SpaceObservation.builder().id(2).build());

        Mockito.when(spaceObservationRepository.findAllObservationsByCelestialObjectId(1))
                .thenReturn(expectedObservations);

        List<SpaceObservation> actualObservations = spaceObservationService.getObservationsByCelestialId(1);

        Assertions.assertEquals(expectedObservations, actualObservations);
    }

    @Test
    public void testGetObservationsByPlace() {
        List<SpaceObservation> expectedObservations = new ArrayList<>();
        expectedObservations.add(SpaceObservation.builder().id(1).build());
        expectedObservations.add(SpaceObservation.builder().id(2).build());

        Mockito.when(spaceObservationRepository.findByPlaceObservationCelestial("Observatoire"))
                .thenReturn(expectedObservations);

        List<SpaceObservation> actualObservations = spaceObservationService.getObservationsByPlace("Observatoire");

        Assertions.assertEquals(expectedObservations, actualObservations);
    }

}
