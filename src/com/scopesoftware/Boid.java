package com.scopesoftware;

import javax.swing.*;
import java.awt.*;

/**
 * Created by aran on 15/12/2014.
 */
public class Boid extends JPanel {
    Vector2 position = Vector2.zero;
    Vector2 velocity = Vector2.zero;

    int size = 25;

    public Boid(){
        this.setSize(size, size);
        this.setOpaque(false);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.fillOval(0, 0, size, size);
    }
}
