package com.scopesoftware;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;

/**
 * Created by aran on 15/12/2014.
 */
public class ControlPanel extends JPanel {

    JSlider minimumDistance = new JSlider();
    JSlider velocityLimit = new JSlider();
    JSlider centreModifier = new JSlider();
    JSlider perceivedVelocity = new JSlider();
    JSlider tendencyModifier = new JSlider();

    double mindist = 10;
    double vLim = 500;

    double centreMod = 1000;
    double pvMod = 8;
    double tendMod = 100;

    public ControlPanel(){
        this.setSize(new Dimension(200, 2000));

        this.setOpaque(false);

        add(new JLabel("Minimum Distance"));
        add(minimumDistance);
        add(new JLabel("Velocity Limit"));
        add(velocityLimit);
        add(new JLabel("Centre Modifier"));
        add(centreModifier);
        add(new JLabel("Perceived Vel Modifier"));
        add(perceivedVelocity);
        add(new JLabel("Central Tendency"));
        add(tendencyModifier);

        minimumDistance.setMinimum(0);
        minimumDistance.setMaximum(100);

        velocityLimit.setMinimum(0);
        velocityLimit.setMaximum(500);

        centreModifier.setMinimum(0);
        centreModifier.setMaximum(2000);

        perceivedVelocity.setMinimum(0);
        perceivedVelocity.setMaximum(50);

        tendencyModifier.setMinimum(0);
        tendencyModifier.setMaximum(200);

        minimumDistance.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mindist = minimumDistance.getValue();
            }
        });

        velocityLimit.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                vLim = velocityLimit.getValue();
            }
        });

        centreModifier.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                centreMod = centreModifier.getValue();
            }
        });

        perceivedVelocity.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pvMod = perceivedVelocity.getValue();
            }
        });

        tendencyModifier.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tendMod = tendencyModifier.getValue();
            }
        });
    }




}
