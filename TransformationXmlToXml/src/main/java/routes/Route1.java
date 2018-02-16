package routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import processors.Processor1;


public class Route1 extends RouteBuilder{

    Processor1 processor1Object = new Processor1();

    public void configure() throws Exception {


        /* DataFormat jaxb = new JaxbDataFormat("com.acme.model");
         *  from("activemq:My.Queue").
         *       unmarshal(jaxb).
         *       to("mqseries:Another.Queue");
         */


        //unmarshall method needs a java object called jaxbDataFormat1
        JaxbDataFormat jaxbDataFormat1 = new JaxbDataFormat("customer1");

        //marshall method needs a java object called jaxbDataFormat2
        JaxbDataFormat jaxbDataFormat2 = new JaxbDataFormat("customer2");


        // FROM FILE TO FILE

        // from("file:C:/Kati/Marlo/GitRepo/FileReads/Project01/From")
        from("file:TransformationXmlToXml/src/main/resources/data/inbox/inbox1?noop=true")
                .id("route1")
                //unmarshalling is the process to read in xml and convert to java object using jaxb generated template
                // classes
                .unmarshal(jaxbDataFormat1)
                //this is to invoke processor to transform person1 to person2
                .bean(processor1Object, "processor1Method")   // object + method
                //marshalling is the process to convert java to xml -- the processor from previous step returns
                // a person2 which will be used to create the output xml
                .marshal(jaxbDataFormat2)
                .to("file:TransformationXmlToXml/src/main/resources/data/outbox");
        //.to("file:C:/Kati/Marlo/GitRepo/FileReads/Project01/To");



        // FROM AMQ TO AMQ

          /*  from("amq:data.in")
                    //unmarshalling is the process to read in xml and convert to java object using jaxb generated template
                    // classes
                    .unmarshal(jaxbDataFormatPerson1)
                    //this is to invoke processor to transform person1 to person2
                    .bean(processor, "myprocessor")
                    //marshalling is the process to convert java to xml -- the processor from previous step returns
                    // a person2 which will be used to create the output xml
                    .marshal(jaxbDataFormatPerson2)
                    .to("amq:data.out");
         */




    }

}
