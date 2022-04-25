package controller;

import boostup.FileExplorer;
import fileLiteExplorer.fileroot.FileRoot;
import boostup.RelationHandler;
import filenormal.FileType;
import viewexploer.NormalBigIconViewHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LocationPanel extends JPanel {
    private final int defaultWidth = 543;
    private final int defaultHeight = 36;
    private int width;
    private int height;
    private NormalBigIconViewHolder viewHolder;
    private JTextField explorerPath;
    public LocationPanel() {
        super();
        this.setSize(defaultWidth,defaultHeight);
        explorerPath = new JTextField();
        explorerPath.setText("计算机");
        explorerPath.setFont(new Font("微软雅黑",Font.PLAIN,16));
        setLayout(null);
        explorerPath.setBounds(0,0,getWidth(),getHeight());
        add(explorerPath);
        explorerPath.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    syncViewPort(explorerPath.getText());
//                    System.out.println(explorerPath.getText());
                }
            }
        });
    }
    public void syncViewPort(String path){
//        System.out.println(path);
        if(path.length() == 1){
            path = path+":";
        }
        System.out.println("将要转到"+path);
        explorerPath.setText(FileRoot.getFileType(path)== FileType.CurrentComputer?"计算机":path);
        if(viewHolder!=null){
            viewHolder.repaintView(path);
        }else{
            this.viewHolder = this.getViewHolder();
            this.viewHolder.repaintView(path);
        }

    }
    public void syncViewPort(String path,boolean boot){
        if(path.length() == 1){
            path = path+":";
        }
        explorerPath.setText(FileRoot.getFileType(path)== FileType.CurrentComputer?"计算机":path);
//        this.repaint();
    }
    public NormalBigIconViewHolder getViewHolder(){
        if(this.viewHolder==null){
            return FileExplorer.getHandler().getNormalBigIconViewHolder();
        }else{
            return this.viewHolder;
        }
    }
    @Override
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

    public int getDefaultWidth() {
        return defaultWidth;
    }

    public int getDefaultHeight() {
        return defaultHeight;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public JTextField getExplorerPath() {
        return explorerPath;
    }

    public void setExplorerPath(JTextField explorerPath) {
        this.explorerPath = explorerPath;
        syncViewPort(explorerPath.getText());
    }
}
