package com.scopesoftware;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;
import java.util.Timer;

/**
 * Created by aran on 15/12/2014.
 */
public class BoidController extends JFrame {
    int freq = 30;

    Random ra = new Random();

    Boid[] boids = new Boid[100];


    double mindist = 10;
    double vLim = 500;

    double centreMod = 1000;
    double pvMod = 8;
    double tendMod = 100;

    Point mousePos = new Point(500, 500);
    static Dimension frameSize = new Dimension(1000, 1000);

    public BoidController(){
        setSize(frameSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        final ControlPanel cp = new ControlPanel();
        add(cp);
        setLayout(null);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                mousePos = e.getPoint();
                mindist = cp.mindist;
                vLim = cp.vLim;
                centreMod = cp.centreMod;
                pvMod = cp.pvMod;
                tendMod = cp.tendMod;

            }
        });

        for(int x = 0; x< boids.length; x++){
            boids[x] = new Boid();

            boids[x].position = new Vector2(ra.nextInt(1000), ra.nextInt(1000));


            boids[x].setLocation(boids[x].position.point());
            System.out.println("Boid " + x + ": " + boids[x].getLocation());
            add(boids[x]);
        }
        initialisePositions();
    }

    public void initialisePositions(){
        Timer timer = new Timer();

        TimerTask reposition = new TimerTask() {
            @Override
            public void run() {
                setPositions();
                repaint();
            }
        };

        timer.schedule(reposition, 0, 1000/freq);
    }

    public void setPositions(){
        Vector2 v1, v2, v3, v4, v5, v6, v7;

        for(int x = 0; x< boids.length; x++){
            v1 = rule1(boids[x]);
            v2 = rule2(boids[x]);
            v3 = rule3(boids[x]);
            v4 = tendToPlace(boids[x]);

            v5 = boundPosition(boids[x]);

            boids[x].velocity = boids[x].velocity.add(v1).add(v2).add(v3).add(v4).add(v5);
            limitVelocity(boids[x]);
            boids[x].position = boids[x].position.add(boids[x].velocity);
            boids[x].setLocation(boids[x].position.point());
        }
    }

    public Vector2 tendToPlace(Boid boid){
        Vector2 place = new Vector2(mousePos);
        return place.sub(boid.position).div(tendMod);
    }

    public Vector2 tendFromPlace(Boid boid){
        Vector2 place = new Vector2(mousePos);
        return Vector2.minus.mult(place.sub(boid.position).div(100));
    }

    public void limitVelocity(Boid boid){
        Vector2 vel;

        if(boid.velocity.abs() > vLim){
            boid.velocity = boid.velocity.div(boid.position.abs()).mult(vLim);
        }
    }

    public Vector2 boundPosition(Boid boid){
        Vector2 vel = Vector2.zero;

        if(boid.position.x < 0) vel.x = 1;
        if(boid.position.x > getWidth()) vel.x = -1;
        if(boid.position.y < 0) vel.y = 1;
        if(boid.position.y > getHeight()) vel.y = -1;

        return vel;
    }


    public Vector2 rule1(Boid boid){
        Vector2 perceivedCentre = Vector2.zero;

        for (Boid currentBoid: boids){
            if(boid != currentBoid){
                perceivedCentre = perceivedCentre.add(currentBoid.position);
            }
        }

        perceivedCentre = perceivedCentre.div(boids.length - 1);

        return (perceivedCentre.sub(boid.position).div(centreMod));
    }

    public Vector2 rule2(Boid boid){
        Vector2 distance = Vector2.zero;

        for (Boid currentBoid: boids){
            if(boid != currentBoid){
                if(boid.position.sub(currentBoid.position).abs() <  mindist){
                    distance = distance.sub(currentBoid.position.sub(boid.position));
                }
            }
        }

        return distance;
    }

    public Vector2 rule3(Boid boid){
        Vector2 perceivedVelocity = Vector2.zero;

        for(Boid currentBoid: boids){
            if(currentBoid != boid){
                perceivedVelocity = perceivedVelocity.add(currentBoid.velocity);
            }
        }

        perceivedVelocity = perceivedVelocity.div(boids.length - 1);
        return perceivedVelocity.sub(boid.velocity).div(pvMod);
    }
}
