package viewexploer;

import boostup.FileExplorer;

import javax.swing.*;
import java.awt.*;

public class ViewExplorer extends JPanel {
    public String path;
    private JFrame parent;
    private final int defaultWidth = 910;
    private final int defaultHeight = 530;
    public ViewExplorer(JFrame parent){
        super();
        this.setSize(defaultWidth,defaultHeight);
        this.setPreferredSize(new Dimension(defaultWidth,defaultHeight));
        this.parent = parent;
    }
    public void setViewPort(String path){
        this.path = path;
    }
    public JFrame getParentFrame(){
        return this.parent;
    }
    public void setParentFrame(FileExplorer fileExplorer) {
        this.parent = fileExplorer;
    }
}
