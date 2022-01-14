import java.io.File;
import java.io.IOException;

public class TestDrive {
    public static void main(String[] args) throws IOException {
//        File f = new File("C://");
//        System.out.println(f.isDirectory());
//        System.out.println(f.getAbsolutePath());
//        System.out.println(f.length());
        File f = new File("M://");
        System.out.println(f.exists());
        f.createNewFile();
    }
}
