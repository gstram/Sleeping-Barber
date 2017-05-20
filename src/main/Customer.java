package main;

// customer class
class Customer implements Runnable
{
	//initialize variables for customer number and barber shop
    int number;
    Barbershop shop;
 
    //constructor sets customer to barbershop
    public Customer(Barbershop shop)
    {
        this.shop = shop;
    }
 
    // return customer number
    public int getNumber() {
        return number;
    }
 
    //sets customer number
    public void setNumber(int number) {
        this.number = number;
    }
 
    //runs thread for customer to get haircut
    public void run()
    {
        simulateHairCut();
    }
    
    //adds customer to shop customer list
    private synchronized void simulateHairCut()
    {
        shop.add(this);
    }
}