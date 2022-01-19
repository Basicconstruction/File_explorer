package viewexploer;

import FileLiteExplorer.fileroot.FileRoot;
import boostup.FileExplorer;
import boostup.RelationHandler;
import filenormal.EnhancedFile;
import filenormal.FileType;
import viewexploer.FileItem.NormalItem;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static viewexploer.LiteViewExplorer.defaultHeight;

public class NormalBigIconViewHolder extends JPanel implements ViewHolder{
    private int x_gap;
    private static final int defaultXGap = 7;
    private int c;
    private int width;
    private int height;
    private String paintPath;
    private JScrollPane jsp;
    public final int defaultItemWidth = 108;
    public final int defaultItemHeight = 118;
    private final RelationHandler handler;

    public RelationHandler getHandler() {
        return handler;
    }

    public NormalBigIconViewHolder(RelationHandler handler){
        super();
        this.handler = handler;
    }
    public NormalBigIconViewHolder(RelationHandler handler,String path){
        super();
        this.handler = handler;
    }
    public void repaintView(String path) {
        repaintView(new FileRoot(path,FileRoot.getFileType(path)));
    }
    public int getHeight() {
        return height;
    }

    @Override
    public void notifyViewChanged(String path) {
        repaintView(path);
        this.getHandler().getLocationPanel().syncViewPort(path,false);
    }

    public void repaintView(FileRoot fr) {
        try{
            paintPath = fr.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setGap();
        ArrayList<FileRoot> frs = fr.getChildFileRoot();
        setPanelSize(width, Math.max(registeringY(frs.size() - 1) + defaultItemHeight, defaultHeight));
        int i = 0,j = 0;
        this.removeAll();
        for(FileRoot f:frs){
            try{
                NormalItem ni = new NormalItem(this,f.getAbsolutePath());
                ni.setLocation(new Point(registeringX(i++),registeringY(j++)));
                add(ni);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        this.repaint();
        if( this.getParentPane()!=null){
            ((LiteViewExplorer)( this.getParentPane())).syncFlowSpeed();
            System.out.println("paint well");
        }
    }

    public void repaintView(File f) {
        repaintView(f.getAbsolutePath());
    }

    public int registeringX(int order) {
        return (defaultItemWidth+x_gap)*((order)%c)+x_gap;
    }

    public int registeringY(int order) {
        return defaultItemHeight*(order/c);
    }

    public int getItemWidth() {
        return defaultItemWidth;
    }

    public void setGap() {
        int preWidth = getParentWidth();
        c = preWidth/(defaultItemWidth+defaultXGap);
        x_gap = (preWidth - c*defaultItemWidth)/(c+1);
    }


    public int getItemHeight() {
        return defaultItemHeight;
    }



    public int getParentWidth() {
        if(this.getParentPane()==null){
            return LiteViewExplorer.defaultWidth;
        }
        return this.getParentPane().getWidth();
    }


    public int getParentHeight() {
        if(this.getParentPane()==null){
            return defaultHeight;
        }
        return this.getParentPane().getHeight();
    }


    public String getPaintPath() {
        return this.paintPath;
    }


    public void setParentPane(JScrollPane jsp) {
        this.jsp = jsp;
        repaintView("计算机");
    }


    public JScrollPane getParentPane() {
        return this.jsp;
    }


    public void setPanelSize(int width_param,int height_param){
        width = width_param;
        height = height_param;
        setPreferredSize(new Dimension(width,height));
        setSize(width,height);
        setLayout(null);
    }
}
