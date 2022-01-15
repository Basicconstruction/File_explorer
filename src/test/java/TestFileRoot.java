import FileLiteExplorer.fileroot.FileRoot;
import filenormal.FileType;
import org.junit.Test;

public class TestFileRoot {
    public static void main(String[] args) {

    }
    @Test
    public void testFile(){
        FileRoot fr = new FileRoot("I:\\Recovery\\RecoveryImage", FileType.Directory,true);
        System.out.println(fr.getScalable());
    }
}
