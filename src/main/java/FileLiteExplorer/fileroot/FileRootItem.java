package FileLiteExplorer.fileroot;

import FileLiteExplorer.LiteFilePanel;
import filenormal.FileType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import static FileLiteExplorer.Utils.load;

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
            bt = new JButton(new ImageIcon(load+"bt2.png"));
        }else{
            bt = new JButton(new ImageIcon(load+"bt1.png"));
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
            bt.setIcon(new ImageIcon(load+"bt2.png"));
        }else{
            bt.setIcon(new ImageIcon(load+"bt1.png"));
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
            fileIconLabel = new JLabel(new ImageIcon(load+"computer.png"));
        }else if(this.getFileRoot().getFileType()== FileType.DiskDrive){
            fileIconLabel = new JLabel(new ImageIcon(load+"disk.png"));
        }else{
            fileIconLabel = new JLabel(new ImageIcon(load+"file.png"));
        }
        this.add(fileIconLabel);
        fileIconLabel.setBounds(30,4,24,20);
        MouseListener ml = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                FileRootItem.this.setFocused();
                try {
                    if(FileRootItem.this.getFileRoot().getFileType()== FileType.CurrentComputer){
                        FileRootItem.this.fp.syncLocationPanel("计算机");
                    }else{
                        FileRootItem.this.fp.syncLocationPanel(FileRootItem.this.fr.getAbsolutePath());
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
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
}
