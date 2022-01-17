package viewexploer.FileItem;

import FileLiteExplorer.fileroot.FileRoot;
import filenormal.EnhancedFile;
import filenormal.FileIcon;
import filenormal.FileType;
import viewexploer.ViewHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class NormalItem extends JPanel {
    private File file;
    private String name;
    private JLabel iconLabel;
    private JLabel textLabel;
    private final static int width = 108;
    private final static int height = 118;
    private ViewHolder viewHolder;
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
            icon = new ImageIcon(FileIcon.text);
        }
        iconLabel = new JLabel(icon);
        iconLabel.setBounds(9,10,90,73);
        textLabel.setBounds(9,83,90,30);
        add(iconLabel);
        add(textLabel);
        this.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                NormalItem.this.getViewHolder().notifyViewChanged(NormalItem.this.file.getAbsolutePath());
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
}
