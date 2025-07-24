package activities;

interface BicycleParts {
    String PARTS_TYPE = "Metal Parts";
}

interface BicycleOperations {
    void applyBrake(int decrement);
    void speedUp(int increment);
}

//Base class
class Bicycle implements BicycleParts, BicycleOperations {

    public int gears;
    public int currentSpeed;

    //the Bicycle class has one constructor
    public Bicycle(int gears, int currentSpeed) {
        this.gears = gears;
        this.currentSpeed = currentSpeed;
    }

    //Bicycle class has three methods
    @Override
    public void applyBrake(int decrement) {
        currentSpeed -= decrement;
        System.out.println("Applying break, so current speed: " + currentSpeed);
    }

    @Override
    public void speedUp(int increment) {
        currentSpeed += increment;
        System.out.println("Accelerating so current speed: " + currentSpeed);
    }

    //Method to print info of Bicycle
    public String bicycleDesc() {
        return("No of gears are "+ gears + "\nSpeed of bicycle is " + currentSpeed+" and model type from "+PARTS_TYPE);
    }
}

//Derived class
class MountainBike extends Bicycle {

    //The MountainBike subclass adds one more field
    public int seatHeight;

    //The MountainBike subclass has one constructor
    public MountainBike(int gears, int currentSpeed, int startHeight) {
        //Invoking base-class(Bicycle) constructor
        super(gears, currentSpeed);
        seatHeight = startHeight;
    }

    // the MountainBike subclass adds one more method
    public void setHeight(int newValue) {
        seatHeight = newValue;
    }

    public String bicycleDesc() {
        return (super.bicycleDesc()+ "\nSeat height is " + seatHeight);
    }
}

//Driver class
public class Activity7 {
    public static void main(String args[]) {
        MountainBike mb = new MountainBike(3, 0, 25);
        System.out.println(mb.bicycleDesc());
        mb.speedUp(20);
        mb.applyBrake(5);
    }
}
