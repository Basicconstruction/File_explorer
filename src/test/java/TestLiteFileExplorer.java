import FileLiteExplorer.LiteFileExplorer;
import FileLiteExplorer.LiteFilePanel;

import java.awt.*;
import java.io.FileNotFoundException;


public class TestLiteFileExplorer {
    public static void main(String[] args) throws FileNotFoundException {
        FrameShow fm = new FrameShow();
        LiteFilePanel litePanel = new LiteFilePanel();
        LiteFileExplorer liteExplorer = new LiteFileExplorer(fm,litePanel,200,400);
        litePanel.setBackground(Color.PINK);
        fm.setComponentIn(liteExplorer);
        fm.setVisible(true);
        System.out.println("end");
//        litePanel.setParentPane(liteExplorer);

    }
}
