package com.company.logic;

public class Coord {
    public int x;
    public int y;

    public Coord(int x, int y){// конструктор
        this.x = x;
        this.y = y;
    }

    public boolean equals (Object o){
        if (o instanceof Coord){
            Coord to = (Coord) o;
            return to.x == x && to.y == y;
        }else
            return super.equals(o);
    }
}
