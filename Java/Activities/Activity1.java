package activities;
class Car {
    private int spead;
    public int make;
    public String color;
    public String transmission;
    public void displayCharacterstics() {
        System.out.println("YOu have selected the car with make "+make+" color "+color+ " transmission "+transmission);
    }

    public void accelerate() {
        System.out.println("Apply accelerate, so speed is to "+(++spead));
    }

    public void brake() {
        System.out.println("Apply break, so speed is to "+(--spead));
    }
}
public class Activity1 {

    public static void main(String[] args) {
        Car toyota = new Car();
        toyota.make = 2014;
        toyota.color = "Black";
        toyota.transmission = "Manual";

        //Using Car class method
        toyota.displayCharacterstics();
        toyota.accelerate();
        toyota.brake();
    }

}
