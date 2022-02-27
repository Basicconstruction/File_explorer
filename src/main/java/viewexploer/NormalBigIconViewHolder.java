package viewexploer;

import fileLiteExplorer.fileroot.FileRoot;
import boostup.RelationHandler;
import viewexploer.FileItem.NormalItem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
    private final RelationHandler handler;
    private final ArrayList<NormalItem> normalItems = new ArrayList<>();
    private Point p;
    private Point p2;
    private final JPanel paint = new JPanel();

    public RelationHandler getHandler() {
        return handler;
    }

    public NormalBigIconViewHolder(RelationHandler handler){
        super();
        this.handler = handler;
        sharedInitAction();
    }
    public NormalBigIconViewHolder(RelationHandler handler,String path){
        super();
        this.handler = handler;
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
        setGap();
        ArrayList<FileRoot> frs = fr.getChildFileRoot();
        setPanelSize(width, Math.max(registeringY(frs.size() - 1) + defaultItemHeight, defaultHeight));
        int i = 0,j = 0;
        for(NormalItem ni:getNormalItems()){
            this.remove(ni);
        }
        normalItems.clear();//清空陈旧数据
        for(FileRoot f:frs){
            try{
                NormalItem ni = new NormalItem(this,f.getAbsolutePath());
                ni.setLocation(new Point(registeringX(i++),registeringY(j++)));
                add(ni);
                normalItems.add(ni);//保存当前视图的元素引用
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
        setChildItemSelected(false);
        System.out.println("click jpanel");
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
        this.repaint();
    }
}
