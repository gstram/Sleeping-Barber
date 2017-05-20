package main;

import java.util.concurrent.TimeUnit;

//simulates the customer barbershop situation
class CustomerSimulator implements Runnable
{
	//initialize variable for shop
    Barbershop shop;
    
    // constructor for simulator
    public CustomerSimulator(Barbershop shop)
    {
        this.shop = shop;
    }
 
    //runs thread
    public void run()
    {
    	// sets counter for how many customers are generated
    	int counter = 1;
    	
    	// Runs the add customer loop until there are 20 customers
        while(counter < 21)
        {
        	//Creates a new customer
            Customer customer = new Customer(shop);
            Thread thcustomer = new Thread(customer);
            
            //Assigns the customer a number
            customer.setNumber(counter);
            counter++;
            thcustomer.start();
 
            try
            {
            	// Sets the time interval for how long it takes a new customer to come in
                TimeUnit.SECONDS.sleep(1);
            }
            catch(InterruptedException iex)
            {
                iex.printStackTrace();
            }
        }
    }
 
}