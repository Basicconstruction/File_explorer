package controller;

import boostup.FileExplorer;
import viewexploer.ViewExplorer;

import javax.swing.*;

public class LocationPanel extends JPanel {
    private final int defaultWidth = 543;
    private final int defaultHeight = 36;
    private JFrame parent;
    public ViewExplorer ve;
    public LocationPanel() {
        super();
    }
    public void syncViewPort(String path){
        if(ve!=null){
            ve.setViewPort(path);
        }
    }
    public void setViewExplorer(ViewExplorer ve){
        this.ve = ve;
    }
    public ViewExplorer getViewExplorer(){
        if(this.ve==null){
            return ((FileExplorer)(this.getParentFrame())).getViewExplorer();
        }else{
            return this.ve;
        }
    }
    public void setParentFrame(JFrame parent){
        this.parent = parent;;
    }
    public JFrame getParentFrame(){
        return this.parent;
    }
}
