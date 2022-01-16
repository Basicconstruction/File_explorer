package boostup;

import java.awt.*;
import java.io.FileNotFoundException;

public class BoostUp {
    public static void main(String[] args) throws FileNotFoundException {
        FileExplorer fe = new FileExplorer();
        fe.setLocation(new Point(400,100));
        fe.setVisible(true);
    }
}
