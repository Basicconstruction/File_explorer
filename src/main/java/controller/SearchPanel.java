package controller;

import javax.swing.*;

public class SearchPanel extends JPanel{
    private LocationSearchControl lsc;
    public SearchPanel(){
        super();
    }
    public LocationSearchControl getLocationSearchControl() {
        return lsc;
    }

    public void setLocationSearchControl(LocationSearchControl lsc) {
        this.lsc = lsc;
    }
}
