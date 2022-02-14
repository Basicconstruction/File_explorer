package cj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class DoubleClickTest extends JFrame {
    JPanel j1 = new JPanel();
    JLabel l1 = new JLabel("white");
    Thread t1;
    volatile boolean b1,b2;
    Random r1 = new Random();
    public DoubleClickTest(){
        super("double click");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(400,200,600+13,400+39);
        setVisible(true);
        j1.setBackground(Color.WHITE);
        add(j1);
        j1.setBounds(100,60,200,200);
        j1.add(l1);
        j1.setLayout(null);
        l1.setBounds(30,50,150,60);
        addListener();
    }
    public Thread getT1(){
        t1 = new Thread(() -> {
            long t = System.currentTimeMillis();
            while(System.currentTimeMillis()-t<1000){
                if(b1&&b2){
                    System.out.println("double click");
                    int x = r1.nextInt(255);int y = r1.nextInt(255);int z = r1.nextInt(255);
                    j1.setBackground(new Color(x,y,z));
                    l1.setText("r: "+x+" g: "+y+" b: "+z);
                    break;
                }
                try{
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            b1 = false;
            b2 = false;
        });
        return t1;
    }
    public void addListener(){
        j1.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!b1){
                    b1 = true;
                    getT1().start();
                }else{
                    b2 = true;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    public static void main(String[] args) {
        DoubleClickTest dbt = new DoubleClickTest();
        dbt.setVisible(true);
    }
}
