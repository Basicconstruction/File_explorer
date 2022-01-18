package boostup;

import FileLiteExplorer.LiteFileExplorer;
import FileLiteExplorer.LiteFilePanel;
import controller.LocationPanel;
import controller.LocationSearchControl;
import controller.SearchPanel;
import viewexploer.LiteViewExplorer;
import viewexploer.NormalBigIconViewHolder;
import viewexploer.ViewHolder;

import java.awt.*;
import java.io.FileNotFoundException;

public class RelationHandler {
    private final FileExplorer fileExplorer;
    private LocationPanel locationPanel;
    private SearchPanel searchPanel;
    private LocationSearchControl locationSearchControl;
    private LiteFileExplorer liteFileExplorer;
    private LiteFilePanel liteFilePanel;
    private NormalBigIconViewHolder normalBigIconViewHolder;
    private LiteViewExplorer liteViewExplorer;
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

        locationSearchControl.setLocation(new Point(140,54));
        liteFileExplorer.setLocation(new Point(0,110));
        liteViewExplorer.setLocation(new Point(160,110));

        //
        this.liteFileExplorer = liteFileExplorer;
        this.locationSearchControl = locationSearchControl;
        this.liteFilePanel = liteFilePanel;
        this.normalBigIconViewHolder = viewHolder;
        this.liteViewExplorer = liteViewExplorer;
        this.searchPanel = locationSearchControl.getSearchPanel();
        this.locationPanel = locationSearchControl.getLocationPanel();

        this.getFileExplorer().setLayout(null);
        this.getFileExplorer().add(liteFileExplorer);
        this.getFileExplorer().add(liteViewExplorer);
        this.getFileExplorer().add(locationSearchControl);
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
}
