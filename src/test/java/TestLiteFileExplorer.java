import FileLiteExplorer.LiteFileExplorer;
import FileLiteExplorer.LiteFilePanel;
import FileLiteExplorer.fileroot.FileRoot;
import FileLiteExplorer.fileroot.FileRootItem;
import filenormal.FileType;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.concurrent.SynchronousQueue;


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
