package FileLiteExplorer;

import FileLiteExplorer.fileroot.FileRoot;
import FileLiteExplorer.fileroot.FileRootItem;
import boostup.RelationHandler;
import filenormal.FileType;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LiteFilePanel extends JPanel {
    private final RelationHandler handler;

    public RelationHandler getHandler() {
        return handler;
    }
    public LiteFileExplorer parent;
    private FileRoot focus;
    public int width = 0;
    public int height = 0;
    private final FileRoot froot;
    private final FileRootItem root;
    private int defaultHeight = 0;
    public LiteFilePanel(RelationHandler handler) throws FileNotFoundException {
        super();
        this.handler = handler;
        froot = new FileRoot("计算机", FileType.CurrentComputer,true);
        root = new FileRootItem(froot,this,0,0);
        repaintFileRoot();
    }
    public void setFocusFileRoot(FileRoot focus){
        if(this.focus==null){
            this.focus = focus;
        }else{
            if(this.focus!=focus){
                this.focus.getFileRootItem().setBackground(this.getBackground());
                this.focus = focus;
            }
        }

    }
    public FileRoot getFocusFileRoot(){
        return this.focus;
    }
    public void setParentPane(LiteFileExplorer parent){
        this.parent = parent;
        this.defaultHeight = this.getParentPane().height;
    }
    public void setSize(int width_param,int height_param){
        width = width_param;
        height = height_param;
        super.setPreferredSize(new Dimension(width,height));
        super.setSize(width,height);
        setLayout(null);
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
                if(f==this.focus){
                    fr.setFocused();
                }
                add(fr);
                repaintFileRoot(fr,order);
            }
        }
        int itemCount = order[0];
        setSize(width, Math.max(itemCount * 28, defaultHeight));
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
                if(f==this.focus){
                    fr.setFocused();
                }
                repaintFileRoot(fr,order);
            }
        }

    }
    public void syncLocationPanel(String path){
        this.getHandler().getLocationPanel().syncViewPort(path);
    }
    public LiteFileExplorer getParentPane(){
        return this.parent;
    }
}
