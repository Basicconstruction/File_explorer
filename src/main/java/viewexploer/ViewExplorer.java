package viewexploer;

import javax.swing.*;

public class ViewExplorer extends JPanel {
    public String path;
    public ViewExplorer(){
        super();
    }
    public void setViewPort(String path){
        this.path = path;
    }
}
