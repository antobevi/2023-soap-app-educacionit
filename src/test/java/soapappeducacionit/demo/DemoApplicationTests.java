package soapappeducacionit.demo;

import localhost._8081.ws.ObtenerPaisRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.assertj.core.api.Assertions.assertThat;

// Test de integracion
// Se recomienda poner un puerto random en los test para evitar posibles inconvenientes
// con las configuraciones de los puertos

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@LocalServerPort
	private int port = 0; // se pone cualquier numero ya que despues se pisa
	private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	private String baseURL = "http://localhost:";

	@BeforeEach
	public void init() throws Exception {
		marshaller.setPackagesToScan(ClassUtils.getPackageName(ObtenerPaisRequest.class));
		marshaller.afterPropertiesSet();
	}

	@Test
	public void testSendAndReceive() {
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		ObtenerPaisRequest request = new ObtenerPaisRequest();

		request.setName("argentina");

		Assertions.assertNotNull(ws.marshalSendAndReceive(baseURL + port + "/ws", request));
		//assertThat(ws.marshalSendAndReceive(baseURL + port + "/ws", request) != null);
	}

}