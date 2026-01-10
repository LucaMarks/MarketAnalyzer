package Unloader;

import Interface.Panel;

public class Main {

    public static void main(String[] args) throws Exception {

//        if(args.length == 0){
//            throw new Exception("Error, no data Provided");
//        }
        Loader[] loaders = new Loader[args.length];

        for(int i = 0; i < args.length; i++){loaders[i] = new Loader(args[i]);}

        new Panel(loaders);

    }

}
