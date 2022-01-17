package viewexploer;


import boostup.FileExplorer;

import javax.swing.*;
import java.awt.*;

/**
 * 可以添加在可改变大小的组件内
 * */
public class LiteViewExplorer extends JScrollPane{
    public JFrame parent;
    public int width;
    public int height;
    public ViewHolder viewHolder;
    public final static int defaultWidth = 910;
    public final static int defaultHeight = 530;
    public LiteViewExplorer(JFrame parent, ViewHolder child, int width, int height){
        super((NormalBigIconViewHolder)child,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.width = width;
        this.viewHolder = child;
        this.height = height;
        this.parent = parent;
        super.setSize(width,height);
        super.setPreferredSize(new Dimension(width,height));

    }
    public ViewHolder getViewHolder(){
        return this.viewHolder;
    }
    public void syncFlowSpeed(){
        this.getVerticalScrollBar().setUnitIncrement(getNewScrollSpeed ( viewHolder.getHeight() ));
    }
    public int getNewScrollSpeed( int scrollPaneHeight ){
        return (int) ( scrollPaneHeight * 0.1 );
    }
    public FileExplorer getParentFrame(){
        return (FileExplorer)this.parent;
    }
    public void setParentFrame(JFrame parent){
        this.parent = parent;
    }
}
