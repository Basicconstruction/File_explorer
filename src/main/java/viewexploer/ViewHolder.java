package viewexploer;

import FileLiteExplorer.fileroot.FileRoot;

import javax.swing.*;
import java.io.File;

public interface ViewHolder {
    void repaintView(String path);
    void repaintView(FileRoot fr);
    void repaintView(File f);
    int registeringX(int x);
    int registeringY(int y);
    int getItemWidth();
    int getItemHeight();
    void setGap();
    int getParentWidth();
    int getParentHeight();
    String getPaintPath();
    void setParentPane(JScrollPane jsp);
    JScrollPane getParentPane();
    void setSize(int width_param,int height_param);
    void setController(Controller controller);
    Controller getController();
    int getHeight();
    void notifyViewChanged(String path);
}
