package com.exalead.derangement_pfe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/map")

public class MapController {
    @GetMapping("/map")
    public String showMapPage() {
        return "map";
    }

}
