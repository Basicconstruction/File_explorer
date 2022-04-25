package viewexploer;

import boostup.FileExplorer;
import boostup.RelationHandler;
import fileLiteExplorer.fileroot.FileRoot;
import viewexploer.FileItem.NormalItem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static viewexploer.LiteViewExplorer.defaultHeight;

public class NormalBigIconViewHolder extends JPanel implements ViewHolder,Pickable{
    private int x_gap;
    private static final int defaultXGap = 7;
    private int c;
    private int width;
    private int height;
    private String paintPath;
    private JScrollPane jsp;
    public final int defaultItemWidth = 108;
    public final int defaultItemHeight = 118;
    private final ArrayList<NormalItem> normalItems = new ArrayList<>();
    private Point p;
    private Point p2;
    private final JPanel paint = new JPanel();
    private RelationHandler handler;
    public NormalBigIconViewHolder(RelationHandler handler){
        super();
        this.handler = handler;
        sharedInitAction();
        notifyViewChanged("计算机");
    }
    public NormalBigIconViewHolder(String path){
        super();
        sharedInitAction();
    }
    private void sharedInitAction(){
        paint.setBackground(new Color(0xab,0xcd,0xef,0x55));
        paint.setBounds(0,0,0,0);
        paint.setBorder(new LineBorder(Color.BLACK));
        add(paint);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                p2 = e.getPoint();
                if(p != null){
                    int x1 = (int) p.getX();
                    int y1 = (int) p.getY();
                    int x2 = (int) p2.getX();
                    int y2 = (int) p2.getY();
                    int x = (int) Math.abs(p2.getX()-p.getX());
                    int y = (int) Math.abs(p2.getY()-p.getY());
                    int px,py;
                    px = Math.min(x2, x1);
                    py = Math.min(y2, y1);
                    getPaint().setBounds(px,py,x,y);
                    NormalBigIconViewHolder.this.setChildItemSelected();
                    NormalBigIconViewHolder.this.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });
        setLayout(null);
        this.addMouseListener(this);
    }
    public void repaintView(String path) {
//        System.out.println(path);
        repaintView(new FileRoot(path,FileRoot.getFileType(path)));
    }
    public int getHeight() {
        return height;
    }

    @Override
    public void notifyViewChanged(String path) {
        repaintView(path);
        getHandler().getLocationPanel().syncViewPort(path,false);
    }

    public RelationHandler getHandler() {
        return handler;
    }

    @Override
    public void clearAllPaintItems() {
        normalItems.clear();
    }

    @Override
    public ArrayList<NormalItem> getNormalItems() {
        return normalItems;
    }

    public void repaintView(FileRoot fr) {
        try{
            paintPath = fr.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println(fr);
        try{
            System.out.println("PAINT "+fr.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setGap();
        ArrayList<FileRoot> frs = fr.getChildFileRoot();
        setPanelSize(width, Math.max(registeringY(frs.size() - 1) + defaultItemHeight, defaultHeight));
        int i = 0,j = 0;
        for(int k=0;k<this.getComponents().length;k++){
            System.out.println(k+" "+this.getComponents()[k]);
        }
        removeWhereIndexBiggerThan(0);
        System.out.println(getComponents().length+" BEFORE PAINTING");
//        for(NormalItem ni:normalItems){
//            this.remove(ni);//移除Ui组件(NormalItem)
//        }
        normalItems.clear();//清空陈旧数据
//        System.out.println(fr+""+frs);
        for(FileRoot f:frs){
            try{
//                System.out.println(f);
                NormalItem ni = new NormalItem(this,f.getAbsolutePath());
                ni.setLocation(new Point(registeringX(i++),registeringY(j++)));
                add(ni);
                normalItems.add(ni);//保存当前视图的元素引用
//                System.out.println(ni);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(normalItems.size());
        this.repaint();
        if( this.getParentPane()!=null){
            ((LiteViewExplorer)( this.getParentPane())).syncFlowSpeed();
            System.out.println("paint well");
        }
    }

    public void removeWhereIndexBiggerThan(int index){
        while(this.getComponents().length>index+1){
            this.remove(this.getComponent(1));
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
    public void setChildItemSelected(boolean select) {
        for(NormalItem normalItem:normalItems){
            normalItem.setSelect(select);
        }
    }
    @Override
    public void setChildItemSelected() {
        for(NormalItem normalItem:normalItems){
            Rectangle r1 = normalItem.getBounds();
            normalItem.setSelect(overlapping(r1));
        }
    }

    @Override
    public JPanel getPaint() {
        return this.paint;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("COMPONENTS SIZE"+getComponents().length+" NORMALIZES SIZE"+ normalItems.size());
        System.out.println("VIEWHOLDER PAINT PATH"+this.getPaintPath());
//        if (normalItems.size() == 0) {
//            notifyViewChanged(paintPath);
//        }
//        this.repaint();
//        for(int i = 0;i<normalItems.size();i++){
//            System.out.println(i+" "+normalItems.get(i));
//        }
        setChildItemSelected(false);
        System.out.println("click jpanel");
        System.out.println("LOCATION SRC"+FileExplorer.getHandler().getLocationPanel().getExplorerPath().getText());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        p = e.getPoint();
        System.out.println("Click Listener PRESS"+p);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        p = null;
        p2 = null;
        getPaint().setBounds(0,0,0,0);
//        this.repaint();
    }

    @Override
    public String toString() {
        return "NormalBigIconViewHolder{" +
                "x_gap=" + x_gap +
                ", c=" + c +
                ", width=" + width +
                ", height=" + height +
                ", paintPath='" + paintPath + '\'' +
                ", jsp=" + jsp +
                ", defaultItemWidth=" + defaultItemWidth +
                ", defaultItemHeight=" + defaultItemHeight +
                ", p=" + p +
                ", p2=" + p2 +
                ", paint=" + paint +
                '}';
    }
}
