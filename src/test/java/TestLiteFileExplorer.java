import FileLiteExplorer.LiteFileExplorer;
import FileLiteExplorer.LiteFilePanel;

import java.awt.*;
import java.io.File;
import java.util.Arrays;


public class TestLiteFileExplorer {
    public static void main(String[] args) {
        FrameShow fm = new FrameShow();
        LiteFilePanel litePanel = new LiteFilePanel();
        LiteFileExplorer liteExplorer = new LiteFileExplorer(fm,litePanel,200,400);
        litePanel.setBackground(Color.PINK);
        fm.setComponentIn(liteExplorer);
        fm.setVisible(true);

//        litePanel.setParentPane(liteExplorer);

    }
}
