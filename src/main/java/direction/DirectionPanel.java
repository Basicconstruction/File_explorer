package direction;

import boostup.ApplicationIcon;
import boostup.RelationHandler;

import javax.swing.*;

import java.awt.*;

import static boostup.Utils.load;

public class DirectionPanel extends JPanel {

    private RelationHandler handler;
    private JButton back;
    private JButton forward;
    private JButton latest;
    private JButton upBack;
    public DirectionPanel(RelationHandler handler){
        super();
        this.handler = handler;
        this.setLayout(new GridLayout(1,4));
        this.setSize(132,34);
        this.setPreferredSize(new Dimension(132,34));
        initComponents();
    }
    public void initComponents(){
        back = new JButton(new ImageIcon(ApplicationIcon.back));
        forward = new JButton(new ImageIcon(ApplicationIcon.forward));
        latest = new JButton(new ImageIcon(ApplicationIcon.latest));
        upBack = new JButton(new ImageIcon(ApplicationIcon.upBack));
        add(back);
        add(forward);
        add(latest);
        add(upBack);
    }
    public RelationHandler getHandler() {
        return handler;
    }

    public JButton getBack() {
        return back;
    }

    public JButton getForward() {
        return forward;
    }

    public JButton getLatest() {
        return latest;
    }

    public JButton getUpBack() {
        return upBack;
    }


}
