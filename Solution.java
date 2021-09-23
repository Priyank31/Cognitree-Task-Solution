import java.io.*;
import java.util.*;


public class Solution {
    
    //ArrayList to store the car objects
    ArrayList<Car> arr_Cars = new ArrayList<Car>();

    //Map to store the origin and corresponding Pair of total horsepower and total no. of cars
    Map<String, Pair<Float, Integer> > tree_map = new TreeMap<String, Pair<Float, Integer> >();

    int No_of_data;

    //using constructor to count no. of data in the input file
    Solution() throws FileNotFoundException{
        int cnt=0;

        //creating a file object
        File file = new File("car_input");
        
        //passing the file object in Scanner to read data
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()){
            sc.nextLine();
            cnt++;
        }
        
        this.No_of_data = cnt;

    }

    //getData() function to get the Data from the input file
    public void getData()throws FileNotFoundException{

        //creating a file object
        File file = new File("car_input");
        
        //passing the file object in Scanner to read data
        Scanner sc = new Scanner(file);

        //Creating array of Car object
        Car[] Cars = new Car[No_of_data - 1];

        //temporary variables
        String temp_str;
        Float temp_f = 0.0f;

        //array of string to store name, origin and horsepower
        String[] arr_str = new String[3];

        //integer variable for iteration
        int i=0;

        
        while(sc.hasNextLine()){
            
            //storing input line from the input file 
            temp_str = sc.nextLine();

            //Spliting that line into 3 parts i.e name, origin and horsepower
            arr_str = temp_str.split(",", 3);

            //For ignoring the 1st line of input i.e the header of the columns
            if(i != 0){

                //converting the horsepower(string) into float
                try{
                    temp_f = Float.parseFloat(arr_str[2]);
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                //Creating ojects from the data of the input file
                Cars[i - 1] = new Car(arr_str[0], arr_str[1], temp_f);

                //Adding car objects to ArrayList
                this.arr_Cars.add(Cars[i - 1]);
                
                //
                this.calculateAverage(tree_map, Cars[i - 1]);    
            }

            i++;
        }

        this.SortArrayList();
    }

    public void calculateAverage(Map<String, Pair<Float, Integer> > tree_map, Car car_obj){
        
        Pair<Float, Integer> temp;
        
        //if map is empty then add the values
        if(tree_map.isEmpty()){

            //create Pair<horsepower, 1>
            temp = new Pair <Float, Integer> (car_obj.horsepower, 1);

            //Add origin as KEY and pair of horsepower and no. of car as VALUE in Map
            tree_map.put(car_obj.origin, temp);
        }

        else{

            //If map already contain the KEY(origin) then just add the horsepower and increment the no. of car
            if(tree_map.containsKey(car_obj.origin)){
                temp = new Pair <Float, Integer> (tree_map.get(car_obj.origin).getFirstValue() + car_obj.horsepower, tree_map.get(car_obj.origin).getSecondValue() + 1);
                tree_map.put(car_obj.origin, temp);
            }

            //if KEY(origin) is not present then add the new KEY(origin)
            else{
                temp = new Pair <Float, Integer> (car_obj.horsepower, 1);
                tree_map.put(car_obj.origin, temp);
            }
        }
    }

    //Sorting ArrayList
    public void SortArrayList(){
        Collections.sort(this.arr_Cars, new SortCarObjects());
    }


    //findIndex() function finds and return the index of the ArrayList from where the output needs to be provided 
    public int findIndex(String input_origin){

        int index = 0;
        
        //Taking KEY(origin) from the Map
        for (Map.Entry<String, Pair<Float, Integer> > map : tree_map.entrySet()){
            
            //Checking KEY(origin) with the input_origin
            if(map.getKey().equals(input_origin)){
                return index; 
            }
            else{
                //if KEY(origin) is not found then add VALUE(no. of cars) of that origin and move to the next KEY(origin)
                index = index + map.getValue().getSecondValue();
            }
        }
        return index;
    }


    //finalResult() function will provide the final output
    //It take input_origin, No_of_cars and index as parameter
    public void finalResult(String input_origin, int index, int input_No_of_cars){
        
        //if KEY = input_origin is present 
        if(tree_map.containsKey(input_origin)){

            //avg = total horsepower/ no. of cars
            Float avg = tree_map.get(input_origin).getFirstValue()/tree_map.get(input_origin).getSecondValue();
            //System.out.println("Average: "+ avg);


            //if input no. of cars is greater than the actual cars in that particular origin
            //then set input no. of cars to actual cars
            if(input_No_of_cars > tree_map.get(input_origin).getSecondValue()){
                input_No_of_cars = tree_map.get(input_origin).getSecondValue();
            }

            int cnt = 1;
            //Print the output from the ArrayList
            for (int i = index; i < (index + input_No_of_cars); i++){

                //if condition to check (avg > horsepower) and also to avoid index out of bound 
                if((i < 406) && (avg < arr_Cars.get(i).horsepower)){
                    System.out.println(cnt + ") Name :" + arr_Cars.get(i).name + "   Origin :" + arr_Cars.get(i).origin + "   Horsepower :" + arr_Cars.get(i).horsepower);
                }
                cnt++;
            }
        }

        //if KEY is not present
        else{
            System.out.println("There is no origin as " + input_origin);
            System.out.println("Please provide valid key");
        }
    }


    //displayMap() to display KEY and VALUE of Map
    public void displayMap(){
        for (Map.Entry<String, Pair<Float, Integer> > map : tree_map.entrySet()){
            System.out.println("Key(origin) = " + map.getKey() + ", Total Horsepower = " + map.getValue().getFirstValue() + ", Total Cars = " + map.getValue().getSecondValue());
        }
    }


    //displayArrayList() to display objects of ArrayList
    public void displayArrayList(){
        for (int i = 0; i < this.arr_Cars.size(); i++){
            System.out.println((i+1) + ") Origin: " + this.arr_Cars.get(i).origin + " " + this.arr_Cars.get(i).horsepower);
        }
    }


    //Main Function
    public static void main(String[] args) throws FileNotFoundException{

        Solution sol = new Solution();
        
        //Calling getData() function
        sol.getData();

        Scanner sc = new Scanner(System.in);

        //taking input
        String input_origin = sc.next();
        int No_of_cars = sc.nextInt();


        //calling findindex() function
        int index = sol.findIndex(input_origin);

        //Passing the inputs to the finalResult() to get the desired output
        sol.finalResult(input_origin, index, No_of_cars);
        
        //sol.displayArrayList();
        //sol.displayMap();
    }
}