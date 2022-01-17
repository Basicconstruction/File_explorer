package boostup;

import FileLiteExplorer.LiteFileExplorer;
import FileLiteExplorer.LiteFilePanel;
import controller.LocationPanel;
import controller.LocationSearchControl;
import controller.SearchPanel;
import viewexploer.ViewExplorerController;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class FileExplorer extends JFrame {
    private ViewExplorerController vec;
    private LiteFileExplorer lfe;
    private LocationSearchControl lsc;

    private static final int defaultWidth = 1070;
    private static final int defaultHeight = 700;
    public FileExplorer() throws FileNotFoundException {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(defaultWidth+16,defaultHeight);
        initComponents();
    }
    private void initComponents() throws FileNotFoundException {
        LiteFilePanel lfp = new LiteFilePanel();
        LiteFileExplorer lfe = new LiteFileExplorer(this,lfp,LiteFileExplorer.defaultWidth,LiteFileExplorer.defaultHeight);
        ViewExplorerController vec = new ViewExplorerController("Normal");
        LocationSearchControl lsc = new LocationSearchControl();

        lsc.setLocation(new Point(140,54));
        lfe.setLocation(new Point(0,110));
        vec.getLiteFileExplorer().setLocation(new Point(160,110));

        setLiteFileExplorer(lfe);
        setViewExplorerController(vec);
        setLocationSearchControl(lsc);

        setLayout(null);
        add(lfe);
        add(vec.getLiteFileExplorer());
        add(lsc);
    }
    public ViewExplorerController getViewExplorerController() {
        return vec;
    }

    public void setViewExplorerController(ViewExplorerController vec) {
        this.vec = vec;
        this.vec.setParentFrame(this);
    }
    public LiteFileExplorer getLiteFileExplorer() {
        return lfe;
    }
    public void setLiteFileExplorer(LiteFileExplorer lfe) {
        this.lfe = lfe;
        this.lfe.setParentFrame(this);
    }
    public LocationSearchControl getLocationSearchControl() {
        return lsc;
    }
    public void setLocationSearchControl(LocationSearchControl lsc) {
        this.lsc = lsc;
        this.lsc.setParentFrame(this);
    }
    public LocationPanel getLocationPanel() {
        return this.lsc.getLocationPanel();
    }
    public void setLocationPanel(LocationPanel lop) {
        this.lsc.setLocationPanel(lop);
    }
    public SearchPanel getSearchPanel() {
        return this.lsc.getSearchPanel();
    }
    public void setSearchPanel(SearchPanel sp) {
        this.lsc.setSearchPanel(sp);
    }
}
