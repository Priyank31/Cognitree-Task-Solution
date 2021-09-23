public class Car{
    String name;
    String origin;
    Float horsepower;

    //using Constructor to set the values of the object.
    Car(String name, String origin, Float horsepower){
        this.name = name;
        this.origin = origin;
        this.horsepower = horsepower;
    }

    //display() function to view the object's values.
    public void display(){
        System.out.println("Name: " + this.name);
        System.out.println("Origin: " + this.origin);
        System.out.println("Horsepower: " + this.horsepower);
    }
}