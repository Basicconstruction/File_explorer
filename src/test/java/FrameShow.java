import FileLiteExplorer.LiteFileExplorer;
import FileLiteExplorer.LiteFilePanel;
import FileLiteExplorer.Utils;

import javax.swing.*;
import java.awt.*;

public class FrameShow extends JFrame {
    public FrameShow(){
        super("FrameShow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(700,100,400,400);
    }
    public void setComponentIn(LiteFileExplorer com){
        int width = com.getWidth();
        int height = com.getHeight();
        if(width>100&&height>40){
            this.setSize(width+ Utils.x,height+Utils.y);
        }
        this.setLayout(null);
        getContentPane().add(com);
        com.setBounds(0,0,width,height);
        this.repaint();
        this.setVisible(true);
        System.out.println("add finished");
        System.out.println(width+" "+height);
    }
}
