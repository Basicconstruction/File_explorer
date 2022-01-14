package FileLiteExplorer.fileroot;

import FileLiteExplorer.LiteFilePanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static FileLiteExplorer.Utils.load;

public class FileRootItem extends JPanel {
    private static final int offsetFactor = 10;
    private int width;
    private int height;
    private JButton bt;
    private JLabel label;
    private FileRoot fr;
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
    public void initComponent() {
        this.setLayout(null);
        this.setLocation(offsetFactor*GRADE,componentOrder*28);
        this.setPreferredSize(new Dimension(214,28));
        this.setSize(214,28);
        bt.setBounds(0,0,28,28);
        label.setBounds(28,0,186,28);
        this.add(bt);
        this.add(label);
        try{
            new File("cc.xx").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
