package viewexploer.FileItem;

import java.awt.*;

public interface Selectable {
    void setSelect(boolean select);
    boolean getSelect();
    void setParentPanel(Component component);
    Component getParentPanel();
    void selectWork(boolean select);
}
