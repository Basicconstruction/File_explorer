package viewexploer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface Pickable extends MouseListener {
    /**
     *
     * * https://www.geeksforgeeks.org/find-two-rectangles-overlap/?ref=lbp
     *
     * */
    default boolean overlapping(Point l1,Point r1,Point l2,Point r2){
        if (l1.x == r1.x || l1.y == r1.y || l2.x == r2.x
                || l2.y == r2.y) {
            return false;
        }

        if (l1.x >= r2.x || l2.x >= r1.x){
            return false;
        }
        return l1.y < r2.y && l2.y < r1.y;
    }
    default boolean overlapping(Point pl, Point pr) {
        if(pr.getX()> pl.getX()){
            return overlapping(pl,(int)(pr.getX()-pl.getX()),(int)(pr.getY()-pl.getY()));
        }
        return overlapping(pr,(int)(pl.getX()-pr.getX()),(int)(pl.getY()-pr.getY()));
    }

    default boolean overlapping(Point coml, int width, int height) {
        Rectangle r = getPaint().getBounds();
        Point comr = new Point((int)(coml.getX()+width),(int)(coml.getY()+height));
        Point tl = getPaintPoint();
        Point tr = new Point(tl.x+getPaint().getWidth(), tl.y+getPaint().getHeight());
        return overlapping(tl,tr,coml,comr);
    }

    default boolean overlapping(Rectangle r1) {
        return overlapping(r1.getLocation(),(int)r1.getWidth(),(int)r1.getHeight());
    }
    void setChildItemSelected();
    JPanel getPaint();
    default Point getPaintPoint(){
        return getPaint().getLocation();
    }
    @Override
    default void mouseEntered(MouseEvent e) {

    }

    @Override
    default void mouseExited(MouseEvent e) {

    }

}
