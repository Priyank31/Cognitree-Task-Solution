import java.util.*;

//Comparator interface is implemented in SortCarObjects class
//It is used to implement sorting in Cars objects

/*Idea:
    Here while sorting, first priority is given to origin and if origin 
   of Car objects are same then consider horsepower as second priority to 
   sort the Car objects*/

public class SortCarObjects implements Comparator<Car> {
    public int compare(Car obj1, Car obj2)
    {
        //First check whether origin of both the Car objects are same or not
        int check_origin = obj1.origin.compareTo(obj2.origin);

        //Now check whether horsepower of both the Car objects are same or not
        //And if not then check which object's horsepower is more
        int check_horsepower = obj2.horsepower.compareTo(obj1.horsepower);

        //If origin of both the Car objects are same then it will return check_horsepower.
        if(check_origin == 0){
            return check_horsepower;
        }
        else{
            return check_origin;
        }
    }
}
