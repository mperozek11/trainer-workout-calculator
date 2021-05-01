package org.perom.trainercalculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EstimateController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/estimate")
    public String getEstimate(){
        return "Endpoint reached";
    }
}
