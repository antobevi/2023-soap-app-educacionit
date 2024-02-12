package soapappeducacionit.demo.Repositories;
package soapappeducacionit.demo.

import jakarta.annotation.PostConstruct;
import target/generated-sources/jaxb/localhost/_8081/ws.Pais;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaisRepository {
    private static final Map<String, Pais> paises = new HashMap<String, Pais>(); // mas optimo ya que no nos interesa un orden

    @PostConstruct // apenas arranca la aplicacion se cargan los datos en memoria, util para pruebas
    public void initData() {
        Pais argentina = new Pais();

        argentina.setNombre("Argentina");
        argentina.setCapital("Buenos Aires");
        argentina.setMoneda(Moneda.ARS);
        argentina.setPoblacion(46234830);
        argentina.setBandera("https://flagcdn.com/w2560/ar.png");

        paises.put(argentina.getNombre(), argentina);

        Pais colombia = new Pais();
        colombia.setNombre("Colombia");
        colombia.setCapital("Bogota");
        colombia.setMoneda(Moneda.COP);
        colombia.setPoblacion(52210913);
        colombia.setBandera("https://flagcdn.com/w2560/co.png");

        paises.put(colombia.getNombre(), colombia);

        Pais ecuador = new Pais();

        ecuador.setNombre("Ecuador");
        ecuador.setCapital("Quito");
        ecuador.setMoneda(Moneda.DOL);
        ecuador.setPoblacion(17757000);
        ecuador.setBandera("https://flagcdn.com/w2560/ec.png");

        paises.put(ecuador.getNombre(), ecuador);
    }

    public Pais findPais(String name) {
        Assert.notNull(name, "Country's name can not be empty.");

        return paises.get(name.toLowerCase());
    }

}
