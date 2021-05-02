package org.perom.trainercalculator.api;

import org.perom.trainercalculator.estimator.FitEstimateResponse;
import org.perom.trainercalculator.estimator.FitProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EstimateController {

    private final AtomicLong counter = new AtomicLong();
    private static final Logger logger = LoggerFactory.getLogger(EstimateController.class);

    @Autowired
    private FileStorageService fileStorageService;


    //Add a query string param for imperial / metric
    @PostMapping("/estimate")
    public FitEstimateResponse calculateDistance(@RequestParam("file") MultipartFile file, @RequestParam("weight") String weight) {

        String fileName = fileStorageService.getFileStorageLocation() + "/" + fileStorageService.storeFile(file);

        FitProcessor fitPro = new FitProcessor();
        FitEstimateResponse response = fitPro.getEstimate(fileName, Double.parseDouble(weight));

        return response;
    }

}
