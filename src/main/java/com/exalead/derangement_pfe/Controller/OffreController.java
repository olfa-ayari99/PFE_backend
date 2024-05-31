package com.exalead.derangement_pfe.Controller;

import com.exalead.derangement_pfe.Entity.Equipement;
import com.exalead.derangement_pfe.Entity.Offre;
import com.exalead.derangement_pfe.Repository.OffreRepository;
import com.exalead.derangement_pfe.Service.OffreService.IOffreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name="offre")
public class OffreController {

    @Autowired
    IOffreService offreService;

    @PostMapping("/addOffre")
    @ResponseBody
    public Offre addOffre (@RequestBody Offre offre){
        return offreService.addOffre(offre);
    }

    @PutMapping("/updateOffre")
    public Offre updateOffre(@RequestBody Offre o){
        return offreService.updateOffre(o);
    }

    @DeleteMapping("/deleteOffre/{idOffre}")
    public void deleteOffre(@PathVariable("idOffre") Long idOffre){
        offreService.deleteOffre(idOffre);

    }

    @GetMapping("/getOffre/{idOffre}")
    @ResponseBody
    public Offre getOffre(@PathVariable("idOffre") Long idOffre){
        return offreService.getOffre(idOffre);

    }

    @GetMapping("/getAllOffres")
    @ResponseBody
    public List<Offre> getAllOffres(){
        List<Offre> offres=offreService.getOffres();
        return offres;
    }
}
