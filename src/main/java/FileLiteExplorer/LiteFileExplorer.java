package FileLiteExplorer;

import boostup.FileExplorer;
import boostup.RelationHandler;
import viewexploer.NormalBigIconViewHolder;

import javax.swing.*;
import java.awt.*;

/**
 * 可以添加在可改变大小的组件内
 * */
public class LiteFileExplorer extends JScrollPane{
    private final RelationHandler handler;
    public RelationHandler getHandler() {
        return handler;
    }

    public LiteFilePanel child;//向上转型,不是必须的
    public int width;
    public int height;
    public final static int defaultWidth = 160;
    public final static int defaultHeight = 530;
    public LiteFileExplorer(RelationHandler handler,LiteFilePanel child){
        super(child,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.handler = handler;
        super.setSize(defaultWidth,defaultHeight);
        super.setPreferredSize(new Dimension(defaultWidth,defaultHeight));
        this.width = defaultWidth;
        this.height = defaultHeight;
        setChildPanel(child);
    }
    public void syncFlowSpeed(){
        this.getVerticalScrollBar().setUnitIncrement(getNewScrollSpeed ( child.getHeight() ));
    }
    public void setChildPanel(LiteFilePanel child){
        this.child = child;
        child.initWH(defaultWidth,defaultHeight);
        this.child.setParentPane(this);
    }
    public void appriseChildPanelChangeSize(int width_param,int height_param){
        if(this.child!=null){
            this.child.setSize(width_param,height_param);
        }
    }
    int getNewScrollSpeed( int scrollPaneHeight ){
        return (int) ( scrollPaneHeight * 0.1 );
    }

}
