package energy.spectral.httpserver.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MeterRead {
    LocalDateTime time;
    Double usage;
}
