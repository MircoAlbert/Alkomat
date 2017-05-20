package alkomat;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

/**
 *
 */
public class MyBorder implements Border {

    private int thickness_ = 4;
    private Color white = Color.lightGray;
    private Color gray = Color.GRAY;
    private Color black = Color.BLACK;
    
    public void paintBorder(Component c, Graphics g, int x, int y, int width,
            int height) {
        Color oldColor = g.getColor();
        int i;
        /*
        for (i = 0; i < thickness_; i++) {
            g.setColor(white);
            g.drawRect(x + i, y + i, width - i - i - 1, height - i - i - 1); //White Rectangle
        }
        */
        g.setColor(white);
        g.drawRect(x, y, width, height);
        g.drawRect(x+thickness_-1, y+thickness_-1, width-thickness_-1, height-thickness_-1);
        
        for (i = 0; i < thickness_/2; i++) {
            g.setColor(black);
            g.drawLine(x+i-1, y+height- i-1, (width - x) - (i * 2)-1, y +height-i-1); //Bottom Outer Edge
            g.drawLine(x+width- i-1, y + i-1, x +width-i-1, (height - y) - i-1);  //Right Outer Edge
        }
        for (i = thickness_/2; i < thickness_; i++) {
            g.setColor(gray);
            g.drawLine(x + i-1, y+height- i-1, (width - x) - i-1, y +height-i-1); //Bottom Inner Edge
            g.drawLine(x +width- i-1, y + i-1, x +width- i-1, (height - y) - i -1); //Right Inner Edge

        }
        g.setColor(oldColor);
    }

    public int getThickness() {
        return thickness_;
    }

    public void setThickness(int i) {
        thickness_ = i;
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(thickness_, thickness_, thickness_, thickness_);
    }

}