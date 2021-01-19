package energy.spectral.httpserver.dao;

import java.util.Map;

public interface IElectricityConsumptionDAO {

    Map<String, String> readMeterUsage(String startDate, String endDate) ;

}
