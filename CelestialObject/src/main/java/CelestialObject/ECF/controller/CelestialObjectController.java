package CelestialObject.ECF.controller;

import CelestialObject.ECF.dto.CelestialObjectDTO;
import CelestialObject.ECF.service.CelestialObjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import CelestialObject.ECF.entity.CelestialObject;

import java.util.List;

@RestController
@RequestMapping("api/celestialobject")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class CelestialObjectController {
    private final CelestialObjectService celestialObjectService;

    public CelestialObjectController(CelestialObjectService celestialObjectService) {
        this.celestialObjectService = celestialObjectService;
    }
    @PostMapping("")
    public ResponseEntity<CelestialObject> createCelestialObject(@RequestBody CelestialObjectDTO celestialObjectDTO) {
        CelestialObject celestialObject = celestialObjectService.createCelestialObject(celestialObjectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(celestialObject);
    }

    @GetMapping("{id}")
    public ResponseEntity<CelestialObject> getCelestialObject(@PathVariable int id) {
        CelestialObject celestialObject = celestialObjectService.getCelestialById(id);
        return ResponseEntity.ok(celestialObject);
    }
    @GetMapping("/objectslist")
    public ResponseEntity<List<CelestialObject>> getAllCelestialObjects() {
        List<CelestialObject> celestialObjects = celestialObjectService.getAllCelestialObject();
        return ResponseEntity.ok(celestialObjects);
    }
}
