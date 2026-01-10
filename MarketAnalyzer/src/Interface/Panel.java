package Interface;

import Unloader.Loader;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel{

    int width = 3000;
    int height = 800;
    BufferedImage screen;

    final int tileSize = 21;
    final int lineWidth = 2;
    int axisSize = 35;
    final int chartStartX = tileSize * 2;
    final int chartStartY = tileSize * (axisSize / 2) + 1;

    JFrame window;
    JPanel mainPanel;
    final Color[] colours = {Color.CYAN, Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.MAGENTA, Color.PINK, Color.WHITE};

    public Panel(Loader[] datas){
        setup();
        makePanels();

        for(int i = 0; i < datas.length; i++){drawChart(datas[i].chartIndexes, colours[i]);}

        window.setVisible(true);
    }

    public void setup(){
        window = new JFrame();
//        panel = new JPanel();

//        panel.setBorder(BorderFactory.createEmptyBorder(width, height, width, height));
//        panel.setLayout(new GridLayout(0, 1));

//        window.add(panel, BorderLayout.CENTER);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
        window.setBackground(Color.gray);
        window.setLayout(null);
        window.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setBounds(0, 0, width, height);
        window.add(mainPanel);
        mainPanel.setLayout(null);

    }

    public void makePanels(){

        //Axes
        JPanel yaxis = new JPanel();
        yaxis.setBackground(Color.BLACK);
        yaxis.setBounds(chartStartX, tileSize, lineWidth, tileSize * axisSize);

        JPanel xaxis = new JPanel();
        xaxis.setBackground(Color.BLACK);
        xaxis.setBounds(chartStartX, chartStartY, tileSize * width - tileSize * 2, lineWidth);

        mainPanel.add(xaxis);
        mainPanel.add(yaxis);

        window.setVisible(true);
    }

    public void drawSideLines(){}

    public void loopData(){

    }
    public void drawChart(float[] data, Color colour){

        //Given data for a single stock, for a single interval, chart the difference
        float prevY = chartStartY;
        int startingX = 2;
        for(int i = 0; i < data.length; i++){
            JPanel panel = new JPanel();
            panel.setBackground(colour);
            int ycord = 0;
            int val = 0;
            if(data[i] > 0){
                                      /*Currently data is multiplied by 10 to take into account
                                        decimals, and also to help scale the data*/

//                val = (int) (data[i] * 10);
                val = (int) (data[i] * tileSize);
                ycord = (int) prevY - val;

            }else{
                ycord = (int) prevY;
//                val = (int) (data[i] * -10);//*-10 to get a positive # (negative #'s don't work)
                val = (int) (data[i] * -1 * tileSize);
            }

            panel.setBounds(chartStartX + (startingX * tileSize), ycord, lineWidth * 4, val);

//            drawIndexes(ycord,  startingX * tileSize, (int) (data[i] * 10));

            System.out.println(i + ": " + data[i] * 10);

            if(data[i] > 0){prevY = ycord;}else{prevY = ycord + val;}

            //StartingX is a misleading name for this
            startingX++;
            mainPanel.add(panel);

            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    public void drawIndexes(int ycord, int xcord, float val){
        JPanel dash = new JPanel();
        dash.setBackground(Color.BLACK);
        dash.setBounds(chartStartX, ycord, 5, lineWidth);

        JLabel num = new JLabel();

        num.setText(Integer.toString((int) val));
        num.setBackground(Color.BLACK);
        num.setBounds(chartStartX + xcord - (tileSize / 2), ycord, tileSize, tileSize);

        mainPanel.add(dash);
        mainPanel.add(num);

        mainPanel.repaint();
        mainPanel.revalidate();
    }
}
