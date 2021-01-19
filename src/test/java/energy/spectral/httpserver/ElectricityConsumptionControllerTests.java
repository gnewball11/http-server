package energy.spectral.httpserver;

import energy.spectral.httpserver.dao.IElectricityConsumptionDAO;
import energy.spectral.httpserver.service.IElectricityConsumptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ElectricityConsumptionControllerTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private IElectricityConsumptionService electricityConsumptionService;

	@MockBean
	private IElectricityConsumptionDAO electricityConsumptionDAO;

	@Test
	void getOneHourReads() throws Exception {

		Map<String, String> meterUsageHour  = new HashMap<String, String>() {{
			put("2019-01-01 01:00:00", "56.03");
			put("2019-01-01 01:15:00", "55.77");
			put("2019-01-01 01:30:00", "55.45");
			put("2019-01-01 01:45:00", "55.74");
		}};

		given(electricityConsumptionDAO.readMeterUsage("2019-01-01 01:00:00","2019-01-01 01:45:00")).willReturn(meterUsageHour);

		mvc.perform(get("/electricity-consumption/get-meter-reads")
				.param("startDate","2019-01-01T01:00:00")
				.param("endDate", "2019-01-01T01:45:00")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(4)));

	}

}
