package CelestialObject.ECF.service;

import CelestialObject.ECF.entity.CelestialObject;
import CelestialObject.ECF.repository.CelestialObjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CelestialObjectService {
    private final CelestialObjectRepository CelestialObjectRepository;

    public CelestialObjectService(CelestialObjectRepository CelestialObjectRepository) {
        this.CelestialObjectRepository = CelestialObjectRepository;
    }

    //Création d'un object céleste
    public CelestialObject createCelestialObject(String title){
        CelestialObject celestialObject = CelestialObject.builder().title(title).build();
        CelestialObjectRepository.save(celestialObject);
        return celestialObject;
    }
    //Recherche d'un objet céleste par son id
    public CelestialObject getCelestialById(int id){
        Optional<CelestialObject> CelestialObjectOptional = CelestialObjectRepository.findById(id);
        if(CelestialObjectOptional.isPresent()){
            return CelestialObjectOptional.get();
        }
        throw new RuntimeException("Not found");
    }
    //Affichage des objets célestes
    public List<CelestialObject> getAllCelestialObject(){
        List<CelestialObject> CelestialObjects = (List<CelestialObject>) CelestialObjectRepository.findAll();
        return  CelestialObjects;
    }
}
