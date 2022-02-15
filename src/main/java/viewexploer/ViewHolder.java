package viewexploer;

import fileLiteExplorer.fileroot.FileRoot;
import viewexploer.FileItem.NormalItem;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

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
    int getHeight();
    void notifyViewChanged(String path);
    void clearAllPaintItems();
    ArrayList<NormalItem> getNormalItems();
}
