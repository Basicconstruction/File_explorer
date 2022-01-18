package controller;

import boostup.RelationHandler;

import javax.swing.*;
import java.awt.*;

public class LocationSearchControl extends JPanel {
    private LocationPanel lop;
    private SearchPanel sp;
    private final int defaultWidth = 930;
    private final int defaultHeight = 76;
    private int width;
    private int height;
    private final RelationHandler handler;
    public LocationSearchControl(RelationHandler handler){
        super();
        setLayout(null);
        this.handler = handler;
        setSize(defaultWidth, defaultHeight);
        LocationPanel lop = new LocationPanel(this.getHandler());
        SearchPanel sp = new SearchPanel(this.getHandler());
        lop.setLocation(new Point(10,20));
        sp.setLocation(new Point(560,20));
        setLocationPanel(lop);
        setSearchPanel(sp);
        this.sp = sp;
        add(lop);
        add(sp);
    }

    private RelationHandler getHandler() {
        return handler;
    }

    public LocationPanel getLocationPanel() {
        return lop;
    }

    public void setLocationPanel(LocationPanel lop) {
        this.lop = lop;
        this.lop.setLocationSearchControl(this);
    }

    public SearchPanel getSearchPanel() {
        return sp;
    }

    public void setSearchPanel(SearchPanel sp) {
        this.sp = sp;
        this.sp.setLocationSearchControl(this);
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
