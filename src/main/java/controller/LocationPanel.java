package controller;

import FileLiteExplorer.fileroot.FileRoot;
import boostup.FileExplorer;
import boostup.RelationHandler;
import filenormal.FileType;
import viewexploer.NormalBigIconViewHolder;

import javax.swing.*;
import java.awt.*;

public class LocationPanel extends JPanel {
    private final int defaultWidth = 543;
    private final int defaultHeight = 36;
    private int width;
    private int height;
    private LocationSearchControl lsc;
    private NormalBigIconViewHolder viewHolder;
    private JLabel explorerPath;
    private final RelationHandler handler;
    public LocationPanel(RelationHandler handler) {
        super();
        this.handler = handler;
        this.setSize(defaultWidth,defaultHeight);
        explorerPath = new JLabel("计算机");
        setLayout(null);
        explorerPath.setBounds(0,0,getWidth(),getHeight());
        add(explorerPath);
    }
    public void syncViewPort(String path){
        explorerPath.setText(FileRoot.getFileType(path)== FileType.CurrentComputer?"计算机":path);
        if(viewHolder!=null){
            viewHolder.repaintView(path);
        }else{
            this.viewHolder = this.getViewHolder();
            this.viewHolder.repaintView(path);
        }

    }
    public void syncViewPort(String path,boolean boot){
        explorerPath.setText(FileRoot.getFileType(path)== FileType.CurrentComputer?"计算机":path);
    }
    public void setViewHolder(NormalBigIconViewHolder viewHolder){
        this.viewHolder = viewHolder;
    }
    public NormalBigIconViewHolder getViewHolder(){
        if(this.viewHolder==null){
            return this.getHandler().getNormalBigIconViewHolder();
        }else{
            return this.viewHolder;
        }
    }
    private RelationHandler getHandler() {
        return handler;
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
