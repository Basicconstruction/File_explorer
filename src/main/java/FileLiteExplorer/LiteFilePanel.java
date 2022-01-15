package FileLiteExplorer;

import FileLiteExplorer.fileroot.FileRoot;
import FileLiteExplorer.fileroot.FileRootItem;
import filenormal.FileType;
import viewexploer.ViewExplorer;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LiteFilePanel extends JPanel {
    public LiteFileExplorer parent;
    public int width = 0;
    public int height = 0;
    public ViewExplorer ve;
    private FileRoot froot;
    private FileRootItem root;
    private int itemCount = 1;
    private int defaultHeight = 0;
    public LiteFilePanel() throws FileNotFoundException {
        super();
        froot = new FileRoot("计算机", FileType.CurrentComputer,true);
        root = new FileRootItem(froot,this,0,0);
        repaintFileRoot();
    }
    public void setParentPane(LiteFileExplorer parent){
        this.parent = parent;
        this.defaultHeight = this.getParentPane().height;
    }
    public void setViewExplorer(ViewExplorer ve){
        this.ve = ve;
    }
    public void setSize(int width_param,int height_param){
        width = width_param;
        height = height_param;
        super.setPreferredSize(new Dimension(width,height));
        super.setSize(width,height);
        setLayout(null);
    }
    public void setWidth(int width_param){
        this.width = width_param;
        super.setSize(width,height);
        super.setPreferredSize(new Dimension(width,height));
    }
    public void setHeight(int height_param){
        this.height = height_param;
        super.setSize(width,height);
        super.setPreferredSize(new Dimension(width,height));
    }
    public void initWH(int width_param,int height_param){
        this.setSize(width_param,height_param);
    }

    public void repaintFileRoot(FileRoot fr) {

    }
    public void repaintFileRoot() throws FileNotFoundException {
        int[] order = new int[]{0};
        this.removeAll();
        root.syncRootItem();
        add(root);
        if(root.getFileRoot().explode){
            ArrayList<FileRoot> frs = this.froot.getChildFileRoot();
            for(FileRoot f:frs){
                FileRootItem fr = new FileRootItem(f,this,1,++order[0]);//for drive grade = 1
                add(fr);
                repaintFileRoot(fr,order);
            }
        }
        itemCount = order[0];
        if(itemCount*28>defaultHeight){
            setSize(width,itemCount*28);
        }else{
            setSize(width,defaultHeight);
        }
        if(defaultHeight!=0){
            this.getParentPane().syncFlowSpeed();
        }
        this.repaint();

    }
    public void repaintFileRoot(FileRootItem fri,int[] order) throws FileNotFoundException {
        if(fri.getFileRoot().explode){
            ArrayList<FileRoot> frs = fri.getFileRoot().getChildFileRoot();
            for(FileRoot f:frs){
                FileRootItem fr = new FileRootItem(f,this,fri.getGrade()+1,++order[0]);
                add(fr);
                repaintFileRoot(fr,order);
            }
        }

    }
    public void syncViewPort(String path){
        if(ve!=null){
            ve.setViewPort(path);
        }
    }
    public LiteFileExplorer getParentPane(){
        return this.parent;
    }
}
