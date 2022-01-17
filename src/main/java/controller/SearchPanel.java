package controller;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel{
    private int defaultWidth = 314;
    private int defaultHeight = 36;
    private int width;
    private int height;
    private LocationSearchControl lsc;
    public SearchPanel(){
        super();
        this.setSize(defaultWidth,defaultHeight);
    }
    public LocationSearchControl getLocationSearchControl() {
        return lsc;
    }

    public void setLocationSearchControl(LocationSearchControl lsc) {
        this.lsc = lsc;
    }

    @Override
    public void setSize(int width_param,int height_param){
        width = width_param;
        height = height_param;
        super.setPreferredSize(new Dimension(width,height));
        super.setSize(width,height);
        setLayout(null);
    }
    public void setWidth(int width_param){
        this.width = width_param;
        super.setSize(width,height);
        super.setPreferredSize(new Dimension(width,height));
    }
    public void setHeight(int height_param){
        this.height = height_param;
        super.setSize(width,height);
        super.setPreferredSize(new Dimension(width,height));
    }
}
