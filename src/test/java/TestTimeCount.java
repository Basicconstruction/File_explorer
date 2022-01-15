import FileLiteExplorer.fileroot.FileRoot;
import filenormal.FileType;

import java.io.File;

public class TestTimeCount {
    public static void main(String[] args) {
        File f = new File("H://");
        f.listFiles();
        FileRoot fr = new FileRoot(f, FileType.Directory,true);
        System.out.println("done");
    }
}
