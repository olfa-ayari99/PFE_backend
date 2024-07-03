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
    @CrossOrigin(origins = "http://localhost:4200")
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

        @DeleteMapping("/deleteLocation/{idEquip}")
        public ResponseEntity<Void> deleteLocation(@PathVariable("idEquip") Long idEquip) {
            localisationService.deleteLocation(idEquip);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/getlocation/{idEquip}")
        public ResponseEntity<Equipement> getLocation(@PathVariable("idEquip") Long idEquip) {
            Equipement location = localisationService.getLocation(idEquip);
            return ResponseEntity.ok(location);
        }

        @GetMapping("/getAllLocations")
        public ResponseEntity<List<Equipement>> getAllLocations() {
            List<Equipement> locations = localisationService.getAllLocations();
            return ResponseEntity.ok(locations);
        }
    }
