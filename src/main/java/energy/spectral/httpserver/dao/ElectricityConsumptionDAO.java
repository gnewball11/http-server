package energy.spectral.httpserver.dao;


import energy.spectral.grpcstub.ElectricityConsumptionRequest;
import energy.spectral.grpcstub.ElectricityConsumptionResponse;
import energy.spectral.grpcstub.ElectricityConsumptionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class ElectricityConsumptionDAO implements IElectricityConsumptionDAO{


    @Override
    public Map<String, String> readMeterUsage(String startDate, String endDate)  {


        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9091)
                .usePlaintext()
                .build();
        ElectricityConsumptionServiceGrpc.ElectricityConsumptionServiceBlockingStub stub = ElectricityConsumptionServiceGrpc.newBlockingStub(channel);
        ElectricityConsumptionResponse electricityConsumptionResponse = stub.getMeterUsage(ElectricityConsumptionRequest.newBuilder()
                                                                            .setStartDate(startDate)
                                                                            .setEndDate(endDate)
                                                                            .build());
        channel.shutdown();
        return electricityConsumptionResponse.getTimeSeriesMap();

    }

}
