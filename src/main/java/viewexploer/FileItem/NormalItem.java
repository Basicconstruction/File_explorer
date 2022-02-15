package viewexploer.FileItem;

import fileLiteExplorer.fileroot.FileRoot;
import filenormal.EnhancedFile;
import boostup.FileIcon;
import filenormal.FileType;
import viewexploer.ViewHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NormalItem extends JPanel implements Selectable{
    private final File file;
    private String name;
    private JLabel iconLabel;
    private JLabel textLabel;
    private final static int width = 108;
    private final static int height = 118;
    private final ViewHolder viewHolder;
    private Thread dkThread;
    volatile boolean b1,b2;
    private boolean select = false;
    public NormalItem(ViewHolder viewHolder,File file){
        super();
        this.file = file;
        setItemSize();
        this.viewHolder = viewHolder;
        initComponents();
    }
    public NormalItem(ViewHolder viewHolder,String file){
        super();
        this.file = new File(file);
        setItemSize();
        this.viewHolder = viewHolder;
        initComponents();
    }
    public Thread getDkThread(){
        dkThread = new Thread(() -> {
            long t = System.currentTimeMillis();
            while(System.currentTimeMillis()-t<1000){
                if(b1&&b2){
                    if(NormalItem.this.file.isDirectory()){
                        NormalItem.this.getViewHolder().notifyViewChanged(NormalItem.this.file.getAbsolutePath());
                    }else{
                        if(NormalItem.this.file.getAbsolutePath().charAt(1)==':'&&(!NormalItem.this.file.getAbsolutePath().endsWith(".exe"))){
                            /*使用默认程序 打开文本文件和图片等。
                             * */
                            Desktop dek = Desktop.getDesktop();
                            if(dek.isSupported(Desktop.Action.OPEN)){
                                try {
                                    dek.open(new File(NormalItem.this.file.getAbsolutePath()));
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }else{
                            //运行程序或脚本, 或许要扩展
                            Runtime rt = Runtime.getRuntime();
                            try {
                                Process process = rt.exec(NormalItem.this.file.getAbsolutePath());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    break;
                }
                try{
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            b1 = false;
            b2 = false;
        });
        return dkThread;
    }
    public void initComponents(){
        setLayout(null);
        name = switch(FileRoot.getFileType(file)){
            case CurrentComputer -> "我的电脑";
            case DiskDrive -> file.getAbsolutePath().toUpperCase().charAt(0)+"";
            default -> file.getName();
        };
        textLabel = new JLabel(name,JLabel.CENTER);
        ImageIcon icon;
        if(new EnhancedFile(this.file).matchFileType()== FileType.Directory){
            icon = new ImageIcon(FileIcon.directory);
        }else if(new EnhancedFile(this.file).matchFileType()== FileType.DiskDrive){
            icon = new ImageIcon(FileIcon.diskDrive);
        }else{
            EnhancedFile en = new EnhancedFile(this.file);
            if(en.matchFileType()!=FileType.Image){
                icon = new ImageIcon(en.matchFileIconPath(en.matchFileType()));
            }else{
                icon = new ImageIcon(this.file.getAbsolutePath());
            }
        }
        iconLabel = new JLabel(icon);
        iconLabel.setBounds(9,10,90,73);
        textLabel.setBounds(9,83,90,30);
        add(iconLabel);
        add(textLabel);
        this.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                if(!b1){
                    b1 = true;
                    getDkThread().start();
                    ArrayList<NormalItem> normalItems = NormalItem.this.getViewHolder().getNormalItems();
                    for(NormalItem nor:normalItems){
                        nor.setSelect(false);
                    }
                    NormalItem.this.setSelect(true);
                }else{
                    b2 = true;
                }
                System.out.println("click child item");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    public void setItemSize(){
        super.setPreferredSize(new Dimension(width,height));
        super.setSize(width,height);
    }
    public ViewHolder getViewHolder() {
        return this.viewHolder;
    }

    @Override
    public void setSelect(boolean select) {
        this.select = select;
        selectWork(select);
    }

    @Override
    public boolean getSelect() {
        return select;
    }

    @Override
    public void setParentPanel(Component component) {

    }

    @Override
    public Component getParentPanel() {
        return null;
    }

    @Override
    public void selectWork(boolean select) {
        if(select){
            this.setBackground(new Color(204,232,255));
        }else{
            this.setBackground(getViewHolder().getParentPane().getBackground());
        }
    }
}
