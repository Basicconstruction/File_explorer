package fileLiteExplorer.fileroot;

import boostup.FileExplorer;
import fileLiteExplorer.LiteFilePanel;
import boostup.ApplicationIcon;
import boostup.FileIcon;
import filenormal.FileType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class FileRootItem extends JPanel {
    private static final int offsetFactor = 10;
    private int width;
    private int height;
    private JButton bt;
    private JLabel fileIconLabel;
    private JLabel label;
    public FileRoot fr;
    private LiteFilePanel fp;
    public final int GRADE;
    public int componentOrder;
    public FileRootItem(FileRoot fr,LiteFilePanel fp,int GRADE,int componentOrder){
        super();
        this.fp = fp;
        this.fr = fr;
        this.GRADE = GRADE;
        this.componentOrder = componentOrder;
        if(this.fr.explode){
            bt = new JButton(new ImageIcon(ApplicationIcon.bt_expand));
        }else{
            bt = new JButton(new ImageIcon(ApplicationIcon.bt_close));
        }
        label = new JLabel(fr.getName(),JLabel.LEFT);
        initComponent();
        this.fr.setFileRootItem(this);
    }
    public int getGrade(){
        return this.GRADE;
    }
    public void syncRootItem(){
        if(this.getFileRoot().explode){
            bt.setIcon(new ImageIcon(ApplicationIcon.bt_expand));
        }else{
            bt.setIcon(new ImageIcon(ApplicationIcon.bt_close));
        }
        this.setVisible(true);
    }
    public void initComponent() {
        this.setLayout(null);
        this.setLocation(offsetFactor*GRADE,componentOrder*28);
        this.setPreferredSize(new Dimension(214,28));
        this.setSize(214,28);
        if(this.getFileRoot().getScalable()){
            bt.setBounds(2,2,24,24);
            label.setBounds(56,0,158,28);
            this.add(bt);
            this.add(label);
            bt.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    FileRootItem.this.fr.setExplode(!FileRootItem.this.fr.explode);
                    try {
                        FileRootItem.this.fp.repaintFileRoot();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }else{
            label.setBounds(56,0,158,28);
            this.add(label);
        }
        if(this.getFileRoot().getFileType()== FileType.CurrentComputer){
            fileIconLabel = new JLabel(new ImageIcon(FileIcon.currentComputer));
        }else if(this.getFileRoot().getFileType()== FileType.DiskDrive){
            fileIconLabel = new JLabel(new ImageIcon(FileIcon.diskDrive));
        }else{
            fileIconLabel = new JLabel(new ImageIcon(FileIcon.directory));
        }
        this.add(fileIconLabel);
        fileIconLabel.setBounds(30,4,24,20);
        MouseListener ml = new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                FileRootItem.this.setFocused();
                try {
                    System.out.println("CHANGE BY LEFT");
                    if(FileRootItem.this.getFileRoot().getFileType()== FileType.CurrentComputer){
                        FileExplorer.getHandler().getLocationPanel().syncViewPort("计算机");
                    }else{
                        FileExplorer.getHandler().getLocationPanel().syncViewPort(FileRootItem.this.fr.getAbsolutePath());
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }   ;
        label.addMouseListener(ml);
        this.addMouseListener(ml);
    }
    public FileRoot getFileRoot(){
        return this.fr;
    }
    public void setFocused(){
        this.setBackground(Color.GRAY);
        this.getParentPanel().setFocusFileRoot(this.getFileRoot());
    }
    public LiteFilePanel getParentPanel(){
        return this.fp;
    }
    private void revertFocused(){
        this.setBackground(Color.WHITE);
    }

    @Override
    public String toString() {
        return "FileRootItem{" +
                "width=" + width +
                ", height=" + height +
                ", bt=" + bt +
                ", fileIconLabel=" + fileIconLabel +
                ", label=" + label +
                ", fr=" + fr +
                ", fp=" + fp +
                ", GRADE=" + GRADE +
                ", componentOrder=" + componentOrder +
                '}';
    }
}
