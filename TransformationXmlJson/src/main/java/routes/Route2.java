package routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import processors.Processor2;


public class Route2 extends RouteBuilder {

    Processor2 processor2Object = new Processor2();

    public void configure() throws Exception {

        //unmarshall method needs a java object called jaxbDataFormat1
        JaxbDataFormat jaxbDataFormat1 = new JaxbDataFormat("customer2");

        //marshall method needs a java object called jaxbDataFormat2
        JaxbDataFormat jaxbDataFormat2 = new JaxbDataFormat("customer1");


        // FROM FILE TO FILE

        from("file:C:/Katalin/GitHub/FileReadings/ProjectCamelTransformation/From2")
                //C:\Katalin\GitHub\FileReadings\ProjectCamelTransformation\From
                //from("file:TransformationXmlToXml/src/main/resources/data/inbox/inbox1?noop=true")
                .id("route2")
                //unmarshalling is the process to read in xml and convert to java object using jaxb generated template
                // classes
                .unmarshal(jaxbDataFormat1)
                //this is to invoke processor to transform person1 to person2
                .bean(processor2Object, "processor2Method")   // object + method
                //marshalling is the process to convert java to xml -- the processor from previous step returns
                // a person2 which will be used to create the output xml
                .marshal(jaxbDataFormat2)
                //.to("file:TransformationXmlToXml/src/main/resources/data/outbox");
                .to("file:C:/Katalin/GitHub/FileReadings/ProjectCamelTransformation/To2");




    }


}
