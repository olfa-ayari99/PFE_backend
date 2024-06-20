    package com.exalead.derangement_pfe.Controller;

    import com.exalead.derangement_pfe.Entity.Equipement;
    import com.exalead.derangement_pfe.Service.LocalisationService.ILocalisationService;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import lombok.AllArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @AllArgsConstructor
    @CrossOrigin(origins = "http://localhost:64317")
    @RequestMapping("/api/v1/localisation")
    @Tag(name= "localisation")
    public class LocalisationController {

        @Autowired
        ILocalisationService localisationService;

        @PostMapping("/addLocalisation")
        public ResponseEntity<Equipement> addLocation(@RequestBody Equipement location) {
            Equipement savedLocation = localisationService.addLocation(location);
            return ResponseEntity.ok(savedLocation);
        }

        @PutMapping("/updateLocation")
        public ResponseEntity<Equipement> updateLocation(@RequestBody Equipement l) {
            Equipement updatedLocation = localisationService.updateLocation(l);
            return ResponseEntity.ok(updatedLocation);
        }

        @DeleteMapping("/deleteLocation/{idLocal}")
        public ResponseEntity<Void> deleteLocation(@PathVariable("idLocal") Long idLocal) {
            localisationService.deleteLocation(idLocal);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/getlocation/{idLocal}")
        public ResponseEntity<Equipement> getLocation(@PathVariable("idLocal") Long idLocal) {
            Equipement location = localisationService.getLocation(idLocal);
            return ResponseEntity.ok(location);
        }

        @GetMapping("/getAllLocations")
        public ResponseEntity<List<Equipement>> getAllLocations() {
            List<Equipement> locations = localisationService.getAllLocations();
            return ResponseEntity.ok(locations);
        }
    }
