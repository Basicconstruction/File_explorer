package cj;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TestArrayList {
    public static void main(String[] args) {
        JFileChooser f = new JFileChooser();
        f.setFileFilter(new FileNameExtensionFilter("*.txt","txt"));
        int res = f.showOpenDialog(null);
        if(res==JFileChooser.APPROVE_OPTION){
            System.out.println(f.getSelectedFile().getAbsoluteFile());
        }
//        FileNameExtensionFilter filter=new FileNameExtensionFilter("*.txt","txt");
//        JFileChooser file=new JFileChooser();
//        file.setFileFilter(filter);
//        int result = file.showOpenDialog(null);
//        file.setMultiSelectionEnabled(true);
//        System.out.println(file.isMultiSelectionEnabled());
//        if(result == JFileChooser.APPROVE_OPTION){
//            String path = file.getSelectedFile().getAbsolutePath();
//            System.out.println(path);
//        }
    }
}
