package boostup;

import FileLiteExplorer.LiteFileExplorer;
import FileLiteExplorer.LiteFilePanel;
import controller.LocationPanel;
import controller.LocationSearchControl;
import controller.SearchPanel;
import viewexploer.ViewExplorer;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class FileExplorer extends JFrame {
    private ViewExplorer ve;
    private LiteFileExplorer lfe;
    private LocationSearchControl lsc;

    private final int defaultWidth = 1070;
    private final int defaultHeight = 700;
    public FileExplorer() throws FileNotFoundException {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(defaultWidth,defaultHeight);
        this.setPreferredSize(new Dimension(defaultWidth,defaultHeight));
        initComponents();
    }
    private void initComponents() throws FileNotFoundException {
        LiteFilePanel lfp = new LiteFilePanel();
        LiteFileExplorer lfet = new LiteFileExplorer(this,lfp,LiteFileExplorer.defaultWidth,LiteFileExplorer.defaultHeight);
        ViewExplorer vet = new ViewExplorer(this);
        lfet.setLocation(new Point(0,110));
        vet.setLocation(new Point(160,110));
        setLiteFileExplorer(lfet);
        setViewExplorer(vet);
        setLayout(null);
        add(lfe);
        add(ve);
    }
    public ViewExplorer getViewExplorer() {
        return ve;
    }

    public void setViewExplorer(ViewExplorer ve) {
        this.ve = ve;
        this.ve.setParentFrame(this);
    }

    public LiteFileExplorer getLiteFileExplorer() {
        return lfe;
    }

    public void setLiteFileExplorer(LiteFileExplorer lfe) {
        this.lfe = lfe;
    }
    public LocationSearchControl getLocationSearchControl() {
        return lsc;
    }
    public void setLocationSearchControl(LocationSearchControl lsc) {
        this.lsc = lsc;
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
