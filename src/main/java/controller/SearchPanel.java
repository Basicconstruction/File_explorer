package controller;

import boostup.RelationHandler;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static FileLiteExplorer.Utils.load;

public class SearchPanel extends JPanel{
    private int width;
    private int height;
    private final RelationHandler handler;
    private LocationSearchControl lsc;
    private JTextField search_box;
    private JLabel search_icon;
    private JButton search_delete;
    private JButton search_go;
    public SearchPanel(RelationHandler handler){
        super();
        this.handler = handler;
        int defaultWidth = 364;
        int defaultHeight = 36;
        this.setSize(defaultWidth, defaultHeight);
        setLayout(null);
        search_delete = new JButton(new ImageIcon(load+"search_delete.png"));
        search_go = new JButton(new ImageIcon(load+"search_go.png"));
        search_delete.addActionListener(e->{
            this.search_box.setText("");
        });
        search_go.addActionListener(e->{
            SearchPanel.this.getHandler().getLocationPanel().syncViewPort("virtual://"+"search="+this.search_box.getText());
            //注释:这里埋了一个坑,需要为该操作编写特定的查询算法,最好是多线程查询,影响 LocationPanel的高级设计和NormalViewHolder(简写)的
            //设计
        });
        add(search_delete);
        add(search_go);
        search_box = new JTextField();
        search_box.setBounds(36,0,324,36);
        search_icon = new JLabel(new ImageIcon(load+"search_icon.png"));
        search_icon.setBounds(0,0,36,36);
        add(search_icon);
        add(search_box);
        Document document = search_box.getDocument();
        document.addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(!SearchPanel.this.search_box.getText().equals("")){
                    SearchPanel.this.putIn();
                }else{
                    SearchPanel.this.removeOut();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(!SearchPanel.this.search_box.getText().equals("")){
                    SearchPanel.this.putIn();
                }else{
                    SearchPanel.this.removeOut();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(!SearchPanel.this.search_box.getText().equals("")){
                    SearchPanel.this.putIn();
                }else{
                    SearchPanel.this.removeOut();
                }
            }
        });

    }
    private void putIn(){
        search_box.setSize(256,36);
        search_delete.setBounds(288,0,36,36);
        search_go.setBounds(324,0,36,36);
        this.repaint();
    }
    public void removeOut(){
        search_box.setSize(324,36);
        search_delete.setBounds(364,0,0,0);
        search_go.setBounds(364,0,0,0);
        this.repaint();
    }
    private RelationHandler getHandler() {
        return handler;
    }
    public LocationSearchControl getLocationSearchControl() {
        return lsc;
    }

    public void setLocationSearchControl(LocationSearchControl lsc) {
        this.lsc = lsc;
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
}
