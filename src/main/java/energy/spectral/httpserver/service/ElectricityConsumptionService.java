package energy.spectral.httpserver.service;

import energy.spectral.httpserver.dao.IElectricityConsumptionDAO;
import energy.spectral.httpserver.model.MeterRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElectricityConsumptionService implements IElectricityConsumptionService{

    private IElectricityConsumptionDAO electricityConsumptionDAO;

    @Override
    public List<MeterRead> getMeterReads(LocalDateTime startDate, LocalDateTime endDate) {

        List<MeterRead> meterReads = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        electricityConsumptionDAO.readMeterUsage(startDate.format(formatter), endDate.format(formatter))
                                 .entrySet()
                                 .forEach( meterUsage -> meterReads.add(MeterRead.builder()
                                                                   .time(LocalDateTime.parse(meterUsage.getKey(), formatter))
                                                                   .usage(Double.valueOf(meterUsage.getValue()))
                                                                   .build()
                                                         ));
        return meterReads;
    }

    @Autowired
    public void setElectricityConsumptionDAO(IElectricityConsumptionDAO electricityConsumptionDAO) {
        this.electricityConsumptionDAO = electricityConsumptionDAO;
    }

}
