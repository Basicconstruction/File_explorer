package toolskit;

import toolskit.zoom.BufferedImageZoom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 展示单张图片的类，主要是重写了JComponent类的paint方法，使得其绘制一个bufferedImage
 * **/
public class BufferedPhoto extends JComponent {
    private int w;
    private int h;
    private BufferedImage bufferedImage;
    public BufferedPhoto(BufferedImage bufferedImage){
        super();
        this.bufferedImage = bufferedImage;
    }
    public BufferedPhoto(File imgSource){
        super();
        BufferedImage bufferedImage1 = null;
        try{
//            System.out.println("xx"+imgSource);
            bufferedImage1 = ImageIO.read(imgSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bufferedImage = bufferedImage1;
    }
    public BufferedPhoto(String imgSource){
        this(new File(imgSource));
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(bufferedImage,0,0,null);
    }

    public void setImageSize(int w, int h) {
        this.w = w;
        this.h = h;
        try {
            bufferedImage = BufferedImageZoom.getBufferedImageZoom(this.bufferedImage,w,h);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
