package main;

// Barber class
class Barber implements Runnable
{
	// intitialize the variables 
    Barbershop shop;
    int barberNumber;
 
    // constructor that sets the barber to a barbershop and a number
    // to the barber
    public Barber(Barbershop shop, int barberNumber)
    {
        this.shop = shop;
        this.barberNumber = barberNumber;
    }
    
    // runs thread
    public void run()
    {
        try
        {
            Thread.sleep(0);
        }
        catch(InterruptedException iex)
        {
            iex.printStackTrace();
        }
        while(true)
        {
            shop.cutHair(barberNumber);
        }
    }
    
    // gets barber number
    public int getName()
    {
    	return barberNumber;
    }
    
    // sets barber number
    public void setName(int name)
    {
    	barberNumber = name;
    }

}
