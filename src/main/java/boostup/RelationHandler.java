package boostup;

import FileLiteExplorer.LiteFileExplorer;
import FileLiteExplorer.LiteFilePanel;
import controller.LocationPanel;
import controller.LocationSearchControl;
import controller.SearchPanel;
import direction.DirectionPanel;
import toolskit.ToolsKit;
import viewexploer.LiteViewExplorer;
import viewexploer.NormalBigIconViewHolder;
import viewexploer.ViewHolder;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.nio.file.DirectoryStream;

public class RelationHandler {
    private final FileExplorer fileExplorer;
    private LocationPanel locationPanel;
    private SearchPanel searchPanel;
    private LocationSearchControl locationSearchControl;
    private LiteFileExplorer liteFileExplorer;
    private LiteFilePanel liteFilePanel;
    private NormalBigIconViewHolder normalBigIconViewHolder;
    private LiteViewExplorer liteViewExplorer;
    private DirectionPanel directionPanel;
    private ToolsKit toolsKit;
    public RelationHandler(FileExplorer fileExplorer) throws FileNotFoundException {
        this.fileExplorer = fileExplorer;
        initLayout();
    }

    public void initLayout() throws FileNotFoundException {
        LiteFilePanel liteFilePanel = new LiteFilePanel(this);
        LiteFileExplorer liteFileExplorer = new LiteFileExplorer(this,liteFilePanel);

        NormalBigIconViewHolder viewHolder = new NormalBigIconViewHolder(this);
        LiteViewExplorer liteViewExplorer = new LiteViewExplorer(this,viewHolder);

        LocationSearchControl locationSearchControl = new LocationSearchControl(this);

        DirectionPanel directionPanel = new DirectionPanel(this);

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
        this.normalBigIconViewHolder = viewHolder;
        this.liteViewExplorer = liteViewExplorer;
        this.searchPanel = locationSearchControl.getSearchPanel();
        this.locationPanel = locationSearchControl.getLocationPanel();
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
