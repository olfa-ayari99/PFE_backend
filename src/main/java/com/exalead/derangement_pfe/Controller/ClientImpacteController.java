package com.exalead.derangement_pfe.Controller;


import com.exalead.derangement_pfe.Service.ClientImpacteService.IClientImpacteService;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/ClientImpacte")
@Tag(name="ClientImpacte")
public class ClientImpacteController {

    @Autowired
    IClientImpacteService clientImpacteService;





   /* @GetMapping("/fetch")
    public ClientImpacte[] fetchClientImpactes() throws JAXBException {
        return externalAPIService.fetchClientImpactes();
    }*/


    /*@PostMapping("/addClient")
    @ResponseBody
    public ClientImpacte addClient(@RequestBody ClientImpacte client){
        return clientImpacteService.addClient(client);
    }*/

    /*@PutMapping("/updateClient")
    public ClientImpacte updateClient(@RequestBody ClientImpacte c){
        return clientImpacteService.updateClient(c);
    }*/
    /*@DeleteMapping("/deleteUser/{idClient}")
    public void deleteClient(@PathVariable("idClient") Long idClient){
        clientImpacteService.deleteClient(idClient);

    }*/

   /* @GetMapping("/getClient/{idClient}")
    @ResponseBody
    public ClientImpacte getClient(@PathVariable("idClient") Long idClient){
        return clientImpacteService.getClient(idClient);

    }*/

    /*@GetMapping("/getAllClients")
    @ResponseBody
    public List<ClientImpacte> getAllClients(){
        List<ClientImpacte> clients=clientImpacteService.getAllClients();
        return clients;
    }*/

    @PutMapping("/affectClientsToDerangement/{idDerangement}")
    public void affectClientsToDerangement(@PathVariable("idDerangement") Long idDerangement, @RequestBody List<Long> clientIds) {
        clientImpacteService.affectClientsToDerangement(idDerangement, clientIds);
    }
}
