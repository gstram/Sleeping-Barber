package main;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Barbershop 
{
	//initializes all variables needed
    int numOfchair;
    List<Customer> listCustomer;
    int totalCustomers = 0;
    int customersLeave = 0;
    int customersHairCut = 0;
 
    public Barbershop()
    {
    	// sets the number of chairs in the barbershop to 5
        numOfchair = 5;
        
        // creates linked list to add customers
        listCustomer = new LinkedList<Customer>();
    }
 
    // cut hair method
    public void cutHair(int barber)
    {
    	//initialize variables needed
        Customer customer;
        
        // begins to process linked list of customers
        synchronized (listCustomer)
        {
 
        	// while statement checks to see if there are no customers left waiting and all customers were done
        	// getting their hair cut
            while(listCustomer.size()==0 && customersLeave > 1 && totalCustomers == (customersLeave + customersHairCut))
            {
            	//Prints all the final data and then exits the program
            	System.out.println();
            	System.out.println("Final Data:");
            	System.out.println("Customer Entry interval: 1 second");
            	System.out.println("Number of chairs: " + numOfchair);
            	System.out.println("Barber cut time: 4 second");
            	System.out.println("Total Customers: " + totalCustomers);
            	System.out.println("Customers that received haircut: " + customersHairCut);
            	System.out.println("Customers that left: " + customersLeave);
                System.exit(1);
                
            }
            
            //checks to see if there are any customers waiting
            while(listCustomer.size()==0)
            {
            	//Prints statement if not
                System.out.println("No Customers. Barber " + barber + " is sleeping.");
                
                try
                {
                	// waits until notified of customer
                    listCustomer.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
            
            customer = (Customer)((LinkedList<?>)listCustomer).poll();
        }
        try
        {    
        	// prints out which barber is cutting which customers hair
            System.out.println("Barber " + barber + " cutting hair.");
            System.out.println("Customer "+customer.getNumber() + " getting hair cut.");
            
            // sets the haircut duration to 4 seconds
            TimeUnit.SECONDS.sleep(4);
        }
        catch(InterruptedException iex)
        {
            iex.printStackTrace();
        }
        
        // prints out that the haircut is complete. Customer leaves the shop. 1 is added to counter.
        System.out.println("Hair cut completed for Customer "+customer.getNumber()+ "." + " Customer " + 
        customer.getNumber() + " leaves barber shop.");
        customersHairCut++;
        
    }
 
    public void add(Customer customer)
    {
    	// adds 1 to total customer counter
    	totalCustomers++;
    	
    	// prints that customer had entered barber shop
        System.out.println("Customer "+customer.getNumber()+ " enters the barber shop");
 
        
        synchronized (listCustomer)
        {
        	
        	//checks to see if there are any open chairs available
            if(listCustomer.size() == numOfchair)
            {
            	//prints that customer is leaving because no chairs
                System.out.println("No more chairs in waiting room, Customer "+customer.getNumber()+  " leaves shop.");
                
                // adds 1 to customerleaves counter and exits method
                customersLeave++;
                return ;
            }
 
            // adds customer to linked list and prints that the customer is waiting
            ((LinkedList<Customer>)listCustomer).offer(customer);
            System.out.println("Customer "+customer.getNumber()+ " waiting in waiting room");
             
            if(listCustomer.size()==1)
                listCustomer.notify();
        }
    }
}
