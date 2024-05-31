package com.exalead.derangement_pfe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/map")

public class MapController {
    @GetMapping("/map")
    public String showMapPage() {
        return "map";
    }

}
