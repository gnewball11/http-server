package energy.spectral.httpserver.controller;


import energy.spectral.httpserver.model.MeterRead;
import energy.spectral.httpserver.service.IElectricityConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("electricity-consumption")
public class ElectricityConsumptionController {

    private IElectricityConsumptionService electricityConsumptionService;

    @GetMapping(value = "/get-meter-reads")
    public ResponseEntity getMeterReads(@RequestParam String startDate, @RequestParam String endDate) {

        List<MeterRead> meterReads = null;
        try{
            meterReads = electricityConsumptionService.getMeterReads(LocalDateTime.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME), LocalDateTime.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }catch (DateTimeParseException e){
            e.printStackTrace();
            return new ResponseEntity("You should specify dates in this format yyyy-MM-ddTHH:mm:ss",HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity(meterReads,HttpStatus.OK);

    }

    @Autowired
    public void setElectricityConsumptionService(IElectricityConsumptionService electricityConsumptionService) {
        this.electricityConsumptionService = electricityConsumptionService;
    }

}
