package boostup;

import fileLiteExplorer.LiteFileExplorer;
import fileLiteExplorer.LiteFilePanel;
import controller.LocationPanel;
import controller.LocationSearchControl;
import controller.SearchPanel;
import direction.DirectionPanel;
import toolskit.ToolsKit;
import viewexploer.LiteViewExplorer;
import viewexploer.NormalBigIconViewHolder;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class RelationHandler {
    private final FileExplorer fileExplorer;
    private LocationPanel locationPanel;
    private SearchPanel searchPanel;
    private LocationSearchControl locationSearchControl;
    private LiteFileExplorer liteFileExplorer;
    private LiteFilePanel liteFilePanel;
    private volatile NormalBigIconViewHolder normalBigIconViewHolder;
    private LiteViewExplorer liteViewExplorer;
    private DirectionPanel directionPanel;
    private ToolsKit toolsKit;
    public RelationHandler(FileExplorer fileExplorer) throws FileNotFoundException {
        this.fileExplorer = fileExplorer;
        initLayout();
    }

    public void initLayout() throws FileNotFoundException {
        LiteFilePanel liteFilePanel = new LiteFilePanel();
        LiteFileExplorer liteFileExplorer = new LiteFileExplorer(liteFilePanel);

        LocationSearchControl locationSearchControl = new LocationSearchControl();

        this.searchPanel = locationSearchControl.getSp();
        this.locationPanel = locationSearchControl.getLop();
        NormalBigIconViewHolder viewHolder = new NormalBigIconViewHolder(this);
        this.normalBigIconViewHolder = viewHolder;

        LiteViewExplorer liteViewExplorer = new LiteViewExplorer(viewHolder);

        DirectionPanel directionPanel = new DirectionPanel();

        ToolsKit toolsKit = new ToolsKit(this);
        toolsKit.setLocation(0,0);
        locationSearchControl.setLocation(new Point(140,55));//
        liteFileExplorer.setLocation(new Point(0,110));
        liteViewExplorer.setLocation(new Point(160,110));//
        directionPanel.setLocation(new Point(4,66));

        //
        this.liteFileExplorer = liteFileExplorer;
        this.locationSearchControl = locationSearchControl;
        this.liteFilePanel = liteFilePanel;
        this.directionPanel = directionPanel;
        this.toolsKit = toolsKit;

        this.getFileExplorer().setLayout(null);
        this.getFileExplorer().add(liteFileExplorer);
        this.getFileExplorer().add(liteViewExplorer);
        this.getFileExplorer().add(locationSearchControl);
        this.getFileExplorer().add(directionPanel);
        this.getFileExplorer().add(toolsKit);
    }
    public JButton getBackDirection(){
        return this.directionPanel.getBack();
    }
    public JButton getForwardDirection(){
        return this.directionPanel.getForward();
    }
    public JButton geLatestDirection(){
        return this.directionPanel.getLatest();
    }
    public JButton getUpBackDirection(){
        return this.directionPanel.getUpBack();
    }
    public DirectionPanel getDirectionPanel(){
        return this.directionPanel;
    }
    public FileExplorer getFileExplorer(){
        return fileExplorer;
    }
    public LocationPanel getLocationPanel() {
        return locationPanel;
    }

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    public LocationSearchControl getLocationSearchControl() {
        return locationSearchControl;
    }

    public LiteFileExplorer getLiteFileExplorer() {
        return liteFileExplorer;
    }

    public LiteFilePanel getLiteFilePanel() {
        return liteFilePanel;
    }

    public NormalBigIconViewHolder getNormalBigIconViewHolder() {
        return normalBigIconViewHolder;
    }

    public LiteViewExplorer getLiteViewExplorer() {
        return liteViewExplorer;
    }

    public ToolsKit getToolsKit() {
        return toolsKit;
    }

}
