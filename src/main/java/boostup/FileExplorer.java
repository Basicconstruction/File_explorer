package boostup;

import javax.swing.*;
import java.io.FileNotFoundException;

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
        initComponents();
    }
    public void initComponents() throws FileNotFoundException {
        this.handler = new RelationHandler(this);
    }
}
