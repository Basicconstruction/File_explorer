package FileLiteExplorer;

import boostup.FileExplorer;

import javax.swing.*;
import java.awt.*;

/**
 * 可以添加在可改变大小的组件内
 * */
public class LiteFileExplorer extends JScrollPane{
    public JFrame parent;
    public LiteFilePanel child;//向上转型,不是必须的
    public int width;
    public int height;
    public final static int defaultWidth = 160;
    public final static int defaultHeight = 530;
    public LiteFileExplorer(JFrame parent,LiteFilePanel child,int width,int height){
        super(child,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.width = width;
        this.height = height;
        this.parent = parent;
        super.setSize(width,height);
        super.setPreferredSize(new Dimension(width,height));
        setChildPanel(child);

    }
    public void syncFlowSpeed(){
        this.getVerticalScrollBar().setUnitIncrement(getNewScrollSpeed ( child.getHeight() ));
    }
    public void setChildPanel(LiteFilePanel child){
        this.child = child;
        child.initWH(this.width,this.height);
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
    public FileExplorer getParentFrame(){
        return (FileExplorer)this.parent;
    }
}
