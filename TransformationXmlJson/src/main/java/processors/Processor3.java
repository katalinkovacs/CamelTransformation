package processors;

import customer1.Customer1;
import customer3.Customer3;
import org.apache.camel.Exchange;

public class Processor3 {


    public void processor3Method (Exchange ex) throws Exception {

        ex.getIn().setHeader("CamelFileName", "Customer1toCustomer3.json");

        Customer1 c1 = (Customer1) ex.getIn().getBody();
        String firstName = c1.getFirstname();
        String lastName = c1.getLastname();

        Customer3 c3 = new Customer3();
        c3.setGivenName(firstName);
        c3.setFamilyName(lastName);

        ex.getIn().setBody(c3);
    }


}
