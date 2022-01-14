import FileLiteExplorer.LiteFileExplorer;
import FileLiteExplorer.LiteFilePanel;
import FileLiteExplorer.fileroot.FileRoot;
import FileLiteExplorer.fileroot.FileRootItem;
import filenormal.FileType;

import java.awt.*;
import java.io.File;

public class TestFileRootItem {
    public static void main(String[] args) {
        FrameShow fm = new FrameShow();
        LiteFilePanel litePanel = new LiteFilePanel();
        LiteFileExplorer liteExplorer = new LiteFileExplorer(fm,litePanel,300,400);
//        litePanel.setBackground(Color.PINK);
        System.out.println("ok");
        litePanel.add(new FileRootItem(
                new FileRoot("H:\\", FileType.DiskDrive),litePanel,0,0));
        System.out.println("ok");
        fm.setComponentIn(liteExplorer);
        fm.setVisible(true);
        System.out.println("ok");
    }
}
