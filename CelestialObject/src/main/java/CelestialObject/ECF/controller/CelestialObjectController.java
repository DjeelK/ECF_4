package CelestialObject.ECF.controller;

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
    public ResponseEntity<CelestialObject> post(@RequestParam String title) {
        CelestialObject celestialObject = celestialObjectService.createCelestialObject(title);
        return ResponseEntity.ok(celestialObject);
    }

    @GetMapping("{id}")
    public ResponseEntity<CelestialObject> get(@PathVariable int id) {
        CelestialObject celestialObject = celestialObjectService.getCelestialById(id);
        return ResponseEntity.ok(celestialObject);
    }
    @GetMapping("/objectslist")
    public ResponseEntity<List<CelestialObject>> getAllCelestials() {
        return new ResponseEntity(celestialObjectService.getAllCelestialObject(), HttpStatus.OK);
    }

    //Faire get search
}
