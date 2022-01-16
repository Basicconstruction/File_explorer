package controller;

import javax.swing.*;

public class LocationSearchControl extends JPanel {
    private LocationPanel lop;
    private SearchPanel sp;
    private JFrame parent;
    public LocationSearchControl(){
        super();
    }
    public LocationPanel getLocationPanel() {
        return lop;
    }

    public void setLocationPanel(LocationPanel lop) {
        this.lop = lop;
    }

    public SearchPanel getSearchPanel() {
        return sp;
    }

    public void setSearchPanel(SearchPanel sp) {
        this.sp = sp;
    }

    public JFrame getParentFrame() {
        return parent;
    }

    public void setParentFrame(JFrame parent) {
        this.parent = parent;
    }


}
