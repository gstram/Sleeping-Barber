package main;

public class SleepingBarber {
 
    public static void main(String a[])
    {
    	// creates barber shop object, and attaches a barber and simulation to it
        Barbershop shop = new Barbershop();
        Barber barber = new Barber(shop, 1);
        Barber barber2 = new Barber(shop, 2);
        CustomerSimulator cg = new CustomerSimulator(shop);
     
        // starts the thread for the barbershop and customer simulation
        Thread thbarber = new Thread(barber);
        Thread thbarber2 = new Thread(barber2);
        Thread thcg = new Thread(cg);
        thcg.start();
        thbarber.start();
        thbarber2.start();
    }
}
