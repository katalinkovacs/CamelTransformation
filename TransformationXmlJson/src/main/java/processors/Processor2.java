package processors;

import customer1.Customer1;
import customer2.Customer2;
import org.apache.camel.Exchange;

public class Processor2 {

    // processor2Method method --> argument (Exchange ex)

    public void processor2Method (Exchange ex) throws Exception {

        //change file name to have a different output file name
        ex.getIn().setHeader("CamelFileName", "Customer2toCustomer1.xml");
        // camel file component
        //gets the unmarshaled java object from message body
        // this is standard camel how to get the body
        Customer2 customer2 = (Customer2) ex.getIn().getBody();

        //get data from p1 object
        String lastName = customer2.getFamilyname();
        String firstName = customer2.getGivenname();



        //Create a new empty Person2 object
        Customer1 customer1 = new Customer1();

        // add the retrieved data from p1 to p2
        customer1.setFirstname(firstName);
        customer1.setLastname(lastName);

        // set the body with new p2 object
        ex.getIn().setBody(customer1);

    }


}
