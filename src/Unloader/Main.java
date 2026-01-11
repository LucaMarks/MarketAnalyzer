package Unloader;

import Interface.Panel;

public class Main {

    public static void main(String[] args) throws Exception {

        if(args.length == 0){
            throw new Exception("Error, no data Provided");
        }
        //This isn't used yet but it should probably be used to help understand the chart
        //{interval, period}
        int[] intervalPeriod = getIntervalPeriod(args[args.length - 1]);

        //The last argument in the interval & period so we want one less for the length of this list
        Loader[] loaders = new Loader[args.length - 1];
//        for(int i = 0; i < args.length; i++){System.out.println(args[i]);}
        for(int i = 0; i < args.length - 1; i++){loaders[i] = new Loader(args[i]);}



        new Panel(loaders);
    }

    public static int [] getIntervalPeriod(String arg){
        String[] list = arg.split(" ");
        return new int []{Integer.valueOf(list[0].charAt(0)), Integer.valueOf(list[1].charAt(0))};
    }

}
