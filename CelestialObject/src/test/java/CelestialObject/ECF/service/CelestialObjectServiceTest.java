package CelestialObject.ECF.service;

import CelestialObject.ECF.dto.CelestialObjectDTO;
import CelestialObject.ECF.entity.CelestialObject;
import CelestialObject.ECF.exception.EntityNotFoundException;
import CelestialObject.ECF.repository.CelestialObjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CelestialObjectServiceTest {

    @Mock
    private CelestialObjectRepository celestialObjectRepository;

    private CelestialObjectService celestialObjectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        celestialObjectService = new CelestialObjectService(celestialObjectRepository);
    }

    @Test
    public void testCreateCelestialObject() {
        CelestialObjectDTO dto = new CelestialObjectDTO();
        dto.setTitle("Test Title");

        CelestialObject expected = CelestialObject.builder()
                .id(1)
                .title("Test Title")
                .build();

        Mockito.when(celestialObjectRepository.save(Mockito.any(CelestialObject.class)))
                .thenReturn(expected);

        CelestialObject actual = celestialObjectService.createCelestialObject(dto);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetCelestialById_Exists() {
        CelestialObject expected = CelestialObject.builder()
                .id(1)
                .title("Test Title")
                .build();

        Mockito.when(celestialObjectRepository.findById(1))
                .thenReturn(Optional.of(expected));

        CelestialObject actual = celestialObjectService.getCelestialById(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetCelestialById_NotFound() {
        Mockito.when(celestialObjectRepository.findById(1))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> celestialObjectService.getCelestialById(1));
    }

    @Test
    public void testGetAllCelestialObject() {
        CelestialObject object1 = CelestialObject.builder().id(1).title("Title 1").build();
        CelestialObject object2 = CelestialObject.builder().id(2).title("Title 2").build();
        List<CelestialObject> expectedList = Arrays.asList(object1, object2);

        Mockito.when(celestialObjectRepository.findAll())
                .thenReturn(expectedList);

        List<CelestialObject> actualList = celestialObjectService.getAllCelestialObject();

        Assertions.assertEquals(expectedList, actualList);
    }
}