public class Pair<T1, T2> {

    private Float first_value;
    private int second_value;

    //using Constructor to set the value of Pair
    //This Pair is used to save total horsepower of cars and total no. of cars in a particular origin
    public Pair(Float horsepower, int i) {
        this.first_value = horsepower;
        this.second_value = i;
    }

    //getFirstValue function() returns the first value of the Pair
    //That is it return the total horsepower of cars in a particular origin
    public Float getFirstValue(){
        return this.first_value;
    }

    //getSecondValue function() returns the second value of the Pair
    //That is it return the total no. of cars in a particular origin
    public int getSecondValue(){
        return this.second_value;
    }
}
