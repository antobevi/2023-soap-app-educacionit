package soapappeducacionit.demo.WebServices;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet>  messageDispatcherServlet(ApplicationContext applicationContext) { // configuraciones del lado del servlet
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();

        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformSchemaLocations(true);

        return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
    }

    @Bean(name = "paises")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema paisesSchema) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();

        defaultWsdl11Definition.setPortTypeName("PaisesPort"); // puerto ficticio
        defaultWsdl11Definition.setLocationUri("/ws"); // parte de la url que vamos a exponer para acceder al wsdl
        defaultWsdl11Definition.setTargetNamespace("http://localhost:8081/"); // espacio de trabajo que debe coincidir con lo que figura en el xsd sin el uri
        defaultWsdl11Definition.setSchema(paisesSchema);

        return defaultWsdl11Definition;
    }

    @Bean // inversion de control
    public XsdSchema paisesSchema() { // especificamos de donde se obtiene estructura del esquema
        return new SimpleXsdSchema(new ClassPathResource("paises.xsd")); // si tuvieramos el archivo dentro de mas carpetas en /resources se deben especificar
    }

}
