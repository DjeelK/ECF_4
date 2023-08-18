package CelestialObject.ECF.service;

import CelestialObject.ECF.dto.CelestialObjectDTO;
import CelestialObject.ECF.entity.CelestialObject;
import CelestialObject.ECF.exception.EntityNotFoundException;
import CelestialObject.ECF.repository.CelestialObjectRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CelestialObjectService {
    private final CelestialObjectRepository celestialObjectRepository;

    public CelestialObjectService(CelestialObjectRepository celestialObjectRepository) {
        this.celestialObjectRepository = celestialObjectRepository;
    }

    //Création d'un object céleste
    public CelestialObject createCelestialObject(CelestialObjectDTO celestialObjectDTO){
        CelestialObject celestialObject = CelestialObject.builder().title( celestialObjectDTO.getTitle()).build();
        return celestialObjectRepository.save(celestialObject);
    }

    //Recherche d'un objet céleste par son id
    public CelestialObject getCelestialById(int id){
        return celestialObjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Celestial object not found"));
    }

    //Affichage des objets célestes
    public List<CelestialObject> getAllCelestialObject(){
        return (List<CelestialObject>) celestialObjectRepository.findAll();
    }
}
