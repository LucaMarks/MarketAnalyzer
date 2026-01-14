package Unloader;

import Interface.Panel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Loader {


    ArrayList<Line> lines = new ArrayList<>();
    //Converted from the previous arrayList
    public float[] chartIndexes;

    public String name;
    public Loader(String name){
        setName(name);
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
                    String[] date = list[0].split(" ");
                    lines.add(new Line(date[0], Float.parseFloat(list[4]), Float.parseFloat(list[1])));

                }
            }catch(Exception e){System.out.println(e.getMessage());}
        }catch(Exception e){e.printStackTrace();}

        /*
            There is one issue with this
                Any value for scale > 21 will cause the chart not to move forward
                This is because the tilesize is 21 so (int) 21 / x, x > 21 = 0 -> therefore no movement for the x-direction
            Two fixes
                1. Make bar smaller when scale is > 21
                    -> The issue is bar width is centered:
                    -> Bar width is independent of x coord, would need to change that if we are going with this solution
                2. Do chartStartX + 1 when scale > 21
         */
        Panel.scale = (int) (lines.size() / 85) + 1;
        //for now this doesn't change
        int scaleCounter = Panel.scale;
        if(Panel.scale > Panel.tileSize){
            Panel.scale = Panel.tileSize;//should be 21
//            Panel.overScaleFix = Panel.lineWidth;
//            Panel.barWidthFactor--;
        }
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

    public void setName(String fileName){
        name = fileName.split("_")[0];
    }
}
