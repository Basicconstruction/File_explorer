import FileLiteExplorer.fileroot.FileRoot;
import FileLiteExplorer.fileroot.FileRootItem;
import filenormal.FileType;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestGetChildRoot {
    public static void main(String[] args) throws FileNotFoundException {
        FileRootItem root = new FileRootItem(new FileRoot("计算机", FileType.CurrentComputer,true),null,0,0);
        root.getFileRoot().explode = true;
        if(root.getFileRoot().explode){
            ArrayList<FileRoot> frs = root.getFileRoot().getChildFileRoot();
            for(FileRoot f:frs){
                System.out.println(f.getAbsolutePath());
            }
        }
    }
}
