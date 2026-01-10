package Unloader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Loader {


    ArrayList<Line> lines = new ArrayList<>();
    //Converted from the previous arrayList
    public float[] chartIndexes;

    public Loader(String name){
       receiveData(name);
       process();
    }


//    public Loader(String name1, String name2){
//        receiveData();
//        process();
//    }


    /*
        Receive the data and store the lines in the array list
        Subtract the close from the open to determine the change
     */
    public void receiveData(String fileName){
        String[] intervals = new String[2];
        int currIndex = 0;
        try{
            Scanner sc = new Scanner(new File("./" + fileName));
            sc.nextLine();
            sc.nextLine();
            sc.nextLine();
            try {
                while (true) {
                    String line = sc.nextLine();
                    String[] list = line.split(",");
//                    System.out.println(Arrays.toString(list));
                    String[] interval = list[0].split(":");
                    String[] date = interval[0].split(" ");
                    if(currIndex < 1) {intervals[currIndex] = interval[2];currIndex++;}
                    lines.add(new Line(date[0], Float.parseFloat(list[4]), Float.parseFloat(list[1])));

                }
            }catch(Exception e){}
        }catch(Exception e){e.printStackTrace();}

//        for(int i = 0; i < lines.size(); i++){System.out.println(lines.get(i).toString());}
    }

    public void process(){
        chartIndexes = new float[lines.size()];

        //Loop through the array and convert each to the difference between the highest and the lowest
        for(int i = 0; i < chartIndexes.length; i++){
            chartIndexes[i] = lines.get(i).close - lines.get(i).open;
//            System.out.println(chartIndexes[i]);
        }
    }

    public void export(){}
}
