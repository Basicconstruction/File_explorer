package toolskit;

import boostup.ApplicationIcon;
import boostup.RelationHandler;

import javax.swing.*;
import java.awt.*;

public class ToolsKit extends JPanel {
    private RelationHandler handler;
    private JButton newCreate;
    private JButton cut;
    private JButton copy;
    private JButton paste;
    private JButton delete;
    private JButton share;
    private JButton rename;
    private JButton sort;
    private JButton show;
    private JButton more;
    private JPanel group;
    public ToolsKit(RelationHandler handler){
        super();
        this.handler = handler;
        setLayout(null);
        Dimension d = new Dimension(1070,55);
        setSize(d);
        setPreferredSize(d);
        initComponents();
    }
    private void initComponents() {
        newCreate = new JButton(new ImageIcon(ApplicationIcon.newCreate));
        cut = new JButton(new ImageIcon(ApplicationIcon.cut));
        copy = new JButton(new ImageIcon(ApplicationIcon.copy));
        paste = new JButton(new ImageIcon(ApplicationIcon.paste));
        delete = new JButton(new ImageIcon(ApplicationIcon.delete));
        share = new JButton(new ImageIcon(ApplicationIcon.share));
        rename = new JButton(new ImageIcon(ApplicationIcon.rename));
        sort = new JButton(new ImageIcon(ApplicationIcon.sort));
        show = new JButton(new ImageIcon(ApplicationIcon.show));
        more = new JButton(new ImageIcon(ApplicationIcon.more));
        group = new JPanel(new GridLayout(1,6));
        group.setSize(new Dimension(292,31));
        group.setPreferredSize(new Dimension(292,31));
        group.add(cut);
        group.add(copy);
        group.add(paste);
        group.add(delete);
        group.add(share);
        group.add(rename);
        newCreate.setBounds(16,15,87,31);
        group.setBounds(120,15,292,31);
        sort.setBounds(430,15,83,31);
        show.setBounds(530,15,83,31);
        more.setBounds(630,15,48,31);
        add(newCreate);
        add(group);
        add(sort);
        add(show);
        add(more);
    }
    public JButton getNewCreate() {
        return newCreate;
    }

    public RelationHandler getHandler() {
        return handler;
    }

    public JButton getRename() {
        return rename;
    }

    public JPanel getGroup() {
        return group;
    }

    public JButton getCut() {
        return cut;
    }

    public JButton getCopy() {
        return copy;
    }

    public JButton getPaste() {
        return paste;
    }

    public JButton getDelete() {
        return delete;
    }

    public JButton getShare() {
        return share;
    }

    public JButton getSort() {
        return sort;
    }

    public JButton getShow() {
        return show;
    }

    public JButton getMore() {
        return more;
    }
}
