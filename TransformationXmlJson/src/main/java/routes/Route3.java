package routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import processors.Processor3;

public class Route3 extends RouteBuilder {

    Processor3 processor3Object = new Processor3();

    public void configure() throws Exception {

        //marshall method needs a java object called jaxbDataFormat1
        JaxbDataFormat myCustomer1jaxbDataFormat = new JaxbDataFormat("customer1");

        JacksonDataFormat myCustomer3JsonFormat = new JacksonDataFormat(customer3.Customer3.class);

        //from("file:Transformation/src/main/resources/data/inbox/inbox2?noop=true")
        from("file:C:/Katalin/GitHub/FileReadings/ProjectCamelTransformation/From3")
        //from("amq:route2.data.in")
                .id("route3")
                //unmarshalling is the process to read in xml/json/etc and convert to java object using jaxb generated template
                .unmarshal(myCustomer1jaxbDataFormat)
                .bean(processor3Object, "processor3Method")   // object + method
                //marshalling is the process to convert java to xml/json/etc
                .marshal(myCustomer3JsonFormat)
                //.to("file:Transformation/src/main/resources/data/outbox");
                //.to("amq:route2.data.out");
                .to("file:C:/Katalin/GitHub/FileReadings/ProjectCamelTransformation/To3");
    }
}
