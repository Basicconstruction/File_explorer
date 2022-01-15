package FileLiteExplorer.fileroot;

import FileLiteExplorer.LiteFilePanel;

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
        bt = new JButton(new ImageIcon(load+"resources\\bt1.png"));
        label = new JLabel(fr.getName(),new ImageIcon(load+"resource\\bt2.png"), SwingConstants.LEFT);
//        label = new JLabel(fr.getName(),JLabel.LEFT);
        initComponent();
    }
    public int getGrade(){
        return this.GRADE;
    }
    public void initComponent() {
        this.setLayout(null);
        this.setLocation(offsetFactor*GRADE,componentOrder*28);
        this.setPreferredSize(new Dimension(214,28));
        this.setSize(214,28);
        bt.setBounds(0,0,28,28);
        label.setBounds(28,0,186,28);
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
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    FileRootItem.this.fp.syncViewPort(FileRootItem.this.fr.getAbsolutePath());
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
        });
    }
    public FileRoot getFileRoot(){
        return this.fr;
    }
}
