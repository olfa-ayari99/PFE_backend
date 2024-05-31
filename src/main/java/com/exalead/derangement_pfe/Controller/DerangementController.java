package com.exalead.derangement_pfe.Controller;

import com.exalead.derangement_pfe.Entity.Derangement;
import com.exalead.derangement_pfe.Entity.Statut;
import com.exalead.derangement_pfe.Entity.User;
import com.exalead.derangement_pfe.Service.DerangementService.IDerangementService;
import com.exalead.derangement_pfe.Service.UserService.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Tag(name="derangement")
public class DerangementController {

    @Autowired
    IDerangementService derangementService;


    @PostMapping("/add/{userId}/{idEquip}")
    public ResponseEntity<Derangement> addDerangement(@RequestBody Derangement derangement, @PathVariable Long userId, @PathVariable Long idEquip) {
        Derangement newDerangement = derangementService.addDerangement(derangement, userId, idEquip);
        return new ResponseEntity<>(newDerangement, HttpStatus.CREATED);
    }

    @PutMapping("/updateDerangement")
    public Derangement updateDerangement(Derangement d){
        return derangementService.updateDerangement(d);
    }
    @DeleteMapping("/deleteDerangement/{idDerangement}")
    public void deleteDerangement(@PathVariable("idDerangement") Long idDerangement){
        derangementService.deleteDerangement(idDerangement);

    }

    @GetMapping("/getDearngement/{idDerangement}")
    @ResponseBody
    public Derangement getDerangement(@PathVariable("idDerangement") Long idDerangement){
        return derangementService.getDerangement(idDerangement);

    }

    @GetMapping("/getAllDerangements")
    @ResponseBody
    public List<Derangement> getAllDerangements(){
        List<Derangement> derangements=derangementService.getAllDerangements();
        return derangements;
    }


    @GetMapping("/getDerangementByStatus/{statut}")
    @ResponseBody
    public List<Derangement> findDerangementByStatus(@PathVariable("statut") Statut statut){
        return derangementService.findDerangementByStatus(statut);

    }
    @GetMapping("/getDerangementByClientImpacte/{idClient}")
    @ResponseBody
    public List<Derangement> findDerangementByClient (@PathVariable("idClient") Long idClient){
        return derangementService.findDerangementByClient(idClient);
    }


    @PostMapping("/search")
    public List<Derangement> searchDerangements(@RequestBody Map<String, Object> criteria) {
        return derangementService.searchDerangements(criteria);
    }

}
