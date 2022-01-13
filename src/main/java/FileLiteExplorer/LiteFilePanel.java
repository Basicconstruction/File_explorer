package FileLiteExplorer;

import javax.swing.*;
import java.awt.*;

public class LiteFilePanel extends JPanel {
    public LiteFileExplorer parent;
    public int width = 0;
    public int height = 0;
    public LiteFilePanel(){
        super();
    }
    public void setParentPane(LiteFileExplorer parent){
        this.parent = parent;
    }
    public void setSize(int width_param,int height_param){
        width = width_param;
        height = height_param;
        super.setPreferredSize(new Dimension(width,height));
        super.setSize(width,height);
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
    public void initWH(int width_param,int height_param){
        this.setSize(width_param,height_param);
    }
}
