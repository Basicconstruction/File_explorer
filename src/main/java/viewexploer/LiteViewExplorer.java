package viewexploer;


import boostup.FileExplorer;
import boostup.RelationHandler;

import javax.swing.*;
import java.awt.*;

/**
 * 可以添加在可改变大小的组件内
 * */
public class LiteViewExplorer extends JScrollPane{
    private final RelationHandler handler;

    public RelationHandler getHandler() {
        return handler;
    }

    public int width;
    public int height;
    public NormalBigIconViewHolder viewHolder;
    public final static int defaultWidth = 910;
    public final static int defaultHeight = 530;
    public LiteViewExplorer(RelationHandler handler,NormalBigIconViewHolder child){
        super(child,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.viewHolder = child;
        this.handler = handler;
        super.setSize(defaultWidth,defaultHeight);
        super.setPreferredSize(new Dimension(defaultWidth,defaultHeight));
        this.width = defaultWidth;
        this.height = defaultHeight;
        viewHolder.setPanelSize(LiteViewExplorer.defaultWidth, LiteViewExplorer.defaultHeight);
        viewHolder.setParentPane(this);
    }
    public void syncFlowSpeed(){
        this.getVerticalScrollBar().setUnitIncrement(getNewScrollSpeed ( viewHolder.getHeight() ));
    }
    public int getNewScrollSpeed( int scrollPaneHeight ){
        return (int) ( scrollPaneHeight * 0.1 );
    }
}
