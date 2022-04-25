package viewexploer.FileItem;

import boostup.FileExplorer;
import fileLiteExplorer.fileroot.FileRoot;
import filenormal.EnhancedFile;
import boostup.FileIcon;
import filenormal.FileType;
import toolskit.BufferedPhoto;
import viewexploer.ViewHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 默认的文件展示形式，实现了可被选择接口
 * **/
public class NormalItem extends JPanel implements Selectable{
    private final File file;
    private String name;
    private BufferedPhoto iconLabel;
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
    /**
     * 识别双击，并运行可执行文件或者打开文件夹
     * **/
    public Thread getDkThread(){
        dkThread = new Thread(() -> {
            long t = System.currentTimeMillis();
            while(System.currentTimeMillis()-t<1000){
                if(b1&&b2){
                    if(NormalItem.this.file.isDirectory()){
//                        NormalItem.this.getViewHolder().notifyViewChanged(NormalItem.this.file.getAbsolutePath());
                        System.out.println("change view to "+NormalItem.this.file.getAbsolutePath()+" by" +
                                " double click NormalItem");
                        FileExplorer.getHandler().getLocationPanel().syncViewPort(NormalItem.this.file.getAbsolutePath());
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
                    NormalItem.this.dkThread.interrupt();
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
        BufferedPhoto bp = null;
        if(new EnhancedFile(this.file).matchFileType()== FileType.Directory){
            bp = new BufferedPhoto(FileIcon.directory);
        }else if(new EnhancedFile(this.file).matchFileType()== FileType.DiskDrive){
            bp = new BufferedPhoto(FileIcon.diskDrive);
        }else{
            EnhancedFile en = new EnhancedFile(this.file);
            if(en.matchFileType()!=FileType.Image){
//                System.out.println(en.matchFileIconPath(en.matchFileType()));
                bp = new BufferedPhoto(en.matchFileIconPath(en.matchFileType()));
            }else{
//                System.out.println(file.getAbsolutePath());
                bp = new BufferedPhoto(this.file.getAbsolutePath());
            }
        }

        if(bp==null){
            bp = new BufferedPhoto(FileIcon.text);
        }
        bp.setImageSize(90,73);
        iconLabel = bp;
        iconLabel.setBounds(9,10,90,73);
        textLabel.setBounds(9,83,90,30);
        add(iconLabel);
        add(textLabel);
        this.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                //选择处理，当单次点击时，取消其他文件的选中，并将本文件选中
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
                System.out.println("click child item"+NormalItem.this);
            }

        });
        this.repaint();
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

    /**
     * 选中与否的背景变化
     * **/
    @Override
    public void selectWork(boolean select) {
        if(select){
            this.setBackground(new Color(204,232,255));
        }else{
            this.setBackground(getViewHolder().getParentPane().getBackground());
        }
    }

    @Override
    public String toString() {
        return "NormalItem{" +
                "file=" + file;
    }
}
