package boostup;

import javax.swing.*;
import java.io.FileNotFoundException;

import static boostup.ApplicationIcon.appIcon;

public class FileExplorer extends JFrame {

    private static final int defaultWidth = 1070;
    private static final int defaultHeight = 700;
    private static RelationHandler handler;

    public static RelationHandler getHandler() {
        return handler;
    }

    public FileExplorer() throws FileNotFoundException {
        super("File explorer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(defaultWidth+16,defaultHeight);
        System.out.println(appIcon);
        this.setIconImage(new ImageIcon(appIcon).getImage());
        initComponents();
    }
    public void initComponents() throws FileNotFoundException {
        handler = new RelationHandler(this);
    }
    public void syncTitlePath(){

    }
}
