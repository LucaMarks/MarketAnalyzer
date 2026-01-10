package Unloader;

import java.text.Format;
import java.util.Formatter;

public class Line {

    String date;
    int interval;
    float open;
    float close;

    public Line(String date, float open, float close){
        this.date = date;
        this.open = open; this.close = close;
    }

    @Override
    public String toString(){
        return String.format("%s %s %s", date, open, close);
    }
}
