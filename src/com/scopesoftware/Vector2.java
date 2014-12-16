package com.scopesoftware;

import java.awt.*;

/**
 * Created by aran on 15/12/2014.
 */
public class Vector2 {
    double x;
    double y;

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2(Point point){
        this.x = point.x;
        this.y = point.y;
    }

    public Vector2 add(Vector2 addVector){
        return new Vector2(this.x + addVector.x, this.y + addVector.y);
    }

    public Vector2 sub(Vector2 addVector){
        return new Vector2(this.x - addVector.x, this.y - addVector.y);
    }

    public Point point(){
        return new Point((int)this.x, (int)this.y);
    }

    public Vector2 div(Vector2 divVector){
        return new Vector2(this.x/divVector.x, this.y/divVector.y);
    }

    public Vector2 div(double divDouble){
        return new Vector2(this.x/divDouble, this.y/divDouble);
    }

    public Vector2 mult(double multDouble){
        return new Vector2(this.x*multDouble, this.y*multDouble);
    }

    public Vector2 mult(Vector2 mult){
        return new Vector2(this.x*mult.x, this.y*mult.y);
    }

    public double abs(){
        return Math.sqrt((this.x * this.x + this.y * this.y));
    }

    public static Vector2 zero = new Vector2(0, 0);
    public static Vector2 minus = new Vector2(-1, -1);
}
