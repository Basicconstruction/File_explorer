package boostup;

import javax.swing.*;
import java.io.FileNotFoundException;

import static boostup.ApplicationIcon.appIcon;
import static boostup.Utils.load;

public class FileExplorer extends JFrame {

    private static final int defaultWidth = 1070;
    private static final int defaultHeight = 700;
    private RelationHandler handler;

    public RelationHandler getHandler() {
        return handler;
    }

    public FileExplorer() throws FileNotFoundException {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(defaultWidth+16,defaultHeight);
        System.out.println(appIcon);
        this.setIconImage(new ImageIcon(appIcon).getImage());
        initComponents();
    }
    public void initComponents() throws FileNotFoundException {
        this.handler = new RelationHandler(this);
    }
    public void syncTitlePath(){

    }
}
