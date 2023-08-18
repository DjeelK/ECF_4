package SpaceObservation.ECF.controller;

import SpaceObservation.ECF.dto.CelestialObjectDTO;
import SpaceObservation.ECF.entity.SpaceObservation;
import SpaceObservation.ECF.service.SpaceObservationService;
import SpaceObservation.ECF.tool.RestClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/observation")
public class SpaceObservationController {
    private final SpaceObservationService spaceObservationService;

    public SpaceObservationController(SpaceObservationService spaceObservationService) {
        this.spaceObservationService = spaceObservationService;
    }

    @PostMapping("")
    public ResponseEntity<SpaceObservation> postObservation(
            @RequestParam int userId,
            @RequestParam int celestialObjectId,
            @RequestParam String dateObservationCelestial,
            @RequestParam String placeObservationCelestial,
            @RequestParam String instrumentUsed,
            @RequestParam String viewingCondition,
            @RequestParam String descriptionCelestial,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            RestClient<String, String> restClient = new RestClient<>();
            if (restClient.testToken(token, String.class)) {
                SpaceObservation createdObservation = spaceObservationService.createObservation(
                        userId,
                        celestialObjectId,
                        dateObservationCelestial,
                        placeObservationCelestial,
                        instrumentUsed,
                        viewingCondition,
                        descriptionCelestial
                );
                return ResponseEntity.ok(createdObservation);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<SpaceObservation>> getObservationsByUserId(
            @PathVariable int userId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            RestClient<String, String> restClient = new RestClient<>();
            if (restClient.testToken(token, String.class)) {
                List<SpaceObservation> observations = spaceObservationService.getObservationsByUserId(userId);
                if (!observations.isEmpty()) {
                    return ResponseEntity.ok(observations);
                }
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("object/{celestialObjectId}")
    public ResponseEntity<List<SpaceObservation>> getObject(
            @PathVariable int celestialObjectId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            RestClient<String, String> restClient = new RestClient<>();
            if (restClient.testToken(token, String.class)) {
                List<SpaceObservation> observations = spaceObservationService.getObservationsByCelestialId(celestialObjectId);
                if (!observations.isEmpty()) {
                    return ResponseEntity.ok(observations);
                }
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("place/{placeObservationCelestial}")
    public ResponseEntity<List<SpaceObservation>> getByPlace(
            @PathVariable String placeObservationCelestial,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            RestClient<String, String> restClient = new RestClient<>();
            if (restClient.testToken(token, String.class)) {
                List<SpaceObservation> observations = spaceObservationService.getObservationsByPlace(placeObservationCelestial);
                if (!observations.isEmpty()) {
                    return ResponseEntity.ok(observations);
                }
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
