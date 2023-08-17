package SpaceObservation.ECF.controller;


import SpaceObservation.ECF.dto.CelestialObjectDTO;
import SpaceObservation.ECF.dto.ObservationResponseDTO;
import SpaceObservation.ECF.entity.SpaceObservation;
import SpaceObservation.ECF.service.SpaceObservationService;
import SpaceObservation.ECF.tool.RestClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/observation")
public class SpaceObservationController {
    private final SpaceObservationService spaceObservationService;

    public SpaceObservationController(SpaceObservationService spaceObservationService) {
        this.spaceObservationService = spaceObservationService;
    }
    public ResponseEntity<SpaceObservation> post(@RequestParam int userId,@RequestParam int celestialObjectId,@RequestParam String dateObservationCelestial,@RequestParam String placeObservationCelestial,@RequestParam String instrumentUsed,@RequestParam String viewingCondition, @RequestParam String descriptionCelestial,@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        RestClient<String,String>restClient = new RestClient<>();
        if(restClient.testToken(token, String.class)) {
            SpaceObservation spaceObservation = spaceObservationService.createObservation(userId,celestialObjectId,dateObservationCelestial,placeObservationCelestial,instrumentUsed,viewingCondition,descriptionCelestial);
            return ResponseEntity.ok(spaceObservation);
        }
        return ResponseEntity.status(401).body(null);
    }
    @GetMapping("{userId}")
    public ResponseEntity<ObservationResponseDTO> getUser(@PathVariable int userId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            RestClient<String, String> restClient = new RestClient<>();
            if(restClient.testToken(token, String.class)) {
                return ResponseEntity.ok(spaceObservationService.getObservationByUserId(userId));
            }
            return ResponseEntity.status(401).body(null);
        }catch (Exception ex) {
            return ResponseEntity.status(401).body(null);
        }
    }
//    @GetMapping("{celestialObjectId}")
//    public ResponseEntity<CelestialObjectDTO> getObject(@PathVariable int celestialObjectId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
//        try {
//            RestClient<String, String> restClient = new RestClient<>();
//            if(restClient.testToken(token, String.class)) {
//                return ResponseEntity.ok(spaceObservationService.getObservationByCelestialId(celestialObjectId).getCelestialObjectDTO());
//            }
//            return ResponseEntity.status(401).body(null);
//        }catch (Exception ex) {
//            return ResponseEntity.status(401).body(null);
//        }
//    }
}
