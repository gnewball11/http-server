package energy.spectral.httpserver.service;

import energy.spectral.httpserver.model.MeterRead;

import java.time.LocalDateTime;
import java.util.List;

public interface IElectricityConsumptionService {

    List<MeterRead> getMeterReads(LocalDateTime startDate, LocalDateTime endDate);

}
