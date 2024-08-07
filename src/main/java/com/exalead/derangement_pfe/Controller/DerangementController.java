package com.exalead.derangement_pfe.Controller;

import com.exalead.derangement_pfe.Entity.Derangement;
import com.exalead.derangement_pfe.Entity.Statut;
import com.exalead.derangement_pfe.Entity.User;
import com.exalead.derangement_pfe.Service.DerangementService.IDerangementService;
import com.exalead.derangement_pfe.Service.UserService.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/derangement")

@Tag(name="derangement")
public class DerangementController {

    @Autowired
    IDerangementService derangementService;


    @PostMapping("/add/{userId}")
    public ResponseEntity<Derangement> addDerangement(@RequestBody Derangement derangement, @PathVariable Long userId, @RequestParam(required = false) Long idEquip) {
        Derangement newDerangement = derangementService.addDerangement(derangement, userId, idEquip);
        return new ResponseEntity<>(newDerangement, HttpStatus.CREATED);
    }

    @PutMapping("/updateDerangement/{idDerangement}")
    public ResponseEntity<Derangement> updateDerangement(@PathVariable("idDerangement") Long idDerangement, @RequestBody Derangement d) {
        Derangement updatedDerangement = derangementService.updateDerangement(idDerangement, d);
        return new ResponseEntity<>(updatedDerangement, HttpStatus.OK);
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
    @GetMapping("/getAllDerangementByUser/{idUser}")
    @ResponseBody
    public List<Derangement> getDerangementByUser (@PathVariable ("idUser") Long idUser){
        return  derangementService.getDerangementByUser(idUser);
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


    @GetMapping("/api/derangements")
    public Page<Derangement> getDerangements(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return derangementService.getDerangements(page, size);
    }
}
