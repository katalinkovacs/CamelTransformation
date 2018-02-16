package processors;

//import employeeversion1.Employeeversion1;
//import employeeversion2.Employeeversion2;
import customer1.Customer1;
import customer1.Customer2;
import org.apache.camel.Exchange;


public class Processor1 {

    // processor1Method method --> argument (Exchange ex)

    public void processor1Method (Exchange ex) throws Exception {

        //change file name to have a different output file name
        ex.getIn().setHeader("CamelFileName", "Customer1toCustomer2.xml");
        // camel file component
        //gets the unmarshaled java object from message body
        // this is standard camel how to get the body
        Customer1 customer1 = (Customer1) ex.getIn().getBody();

        //get date from p1 object
        String firstName = customer1.getFirstname();
        String lastName = customer1.getLastname();



        //Create a new empty Person2 object
        Customer2 customer2 = new Customer2();

        // add the retrieved data from p1 to p2
        customer2.setGivenname(firstName);
        customer2.setFamilyname(lastName);

        // set the body with new p2 object
        ex.getIn().setBody(customer2);

    }

}
