package soapappeducacionit.demo.Endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soapappeducacionit.demo.Repositories.PaisRepository;
import localhost._8081.ws.ObtenerPaisResponse;

@Endpoint
public class PaisEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8081/ws"; // esto podria estar en el properties tambien
    @Autowired
    private PaisRepository paisRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "obtenerPaisRequest") // carga util del response, se vincula con la operacion que se ejecuta del xml
    @ResponsePayload
    public ObtenerPaisResponse obtenerPaisResponse(@RequestPayload ObtenerPaisRequest request) { // carga util del request
        ObtenerPaisResponse response = new ObtenerPaisResponse();

        response.setPais(paisRepository.findPais(request.getName())); // busco el pais ingresado por el usuario

        return response;
    }

}
