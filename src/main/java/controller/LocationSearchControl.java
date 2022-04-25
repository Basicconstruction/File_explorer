package controller;

import boostup.RelationHandler;

import javax.swing.*;
import java.awt.*;

public class LocationSearchControl extends JPanel {
    private final int defaultWidth = 930;
    private final int defaultHeight = 55;
    private int width;
    private int height;
    private LocationPanel lop;
    private SearchPanel sp;
    public LocationSearchControl(){
        super();
        setLayout(null);
        setSize(defaultWidth, defaultHeight);
        lop = new LocationPanel();
        sp = new SearchPanel();
        lop.setLocation(new Point(10,10));
        sp.setLocation(new Point(560,10));
        add(lop);
        add(sp);
    }

    public LocationPanel getLop() {
        return lop;
    }

    public SearchPanel getSp() {
        return sp;
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
