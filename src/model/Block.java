package model;

import java.util.List;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;


public class Block {
    public static final int MAX = 7;
    private int x;
    private int y;
    private final List<Observer> observers = new ArrayList<>();
    private final Timer timer;
    
     public Block(int x, int y){
        this.x = x;
        this.y = y;

        this.timer = new Timer();
        this.timer.schedule(task(), 1000, 500);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void left(){
        if(x == 1) return;
        x--;
        changed();
    }
    
    public void right(){
        if(x == MAX) return;
        x++;
        changed();
    }
    
    public void up(){
        if(y == MAX) return;
        y++;
        changed();
    }
    
    public void down(){
        if(y == 1) return;
        y--;
        changed();
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
        changed();
    }
    public void  register(Observer observer){
        observers.add(observer);
    }
    
    private void changed() {
        for (Observer observer : observers) {
            observer.changed();
        }
    }
    
    private TimerTask task(){
        return new TimerTask() {
            @Override
            public void run(){
                double r = Math.random();
                if(r > 0.4) return;
                else if(r > 0.3) up();
                else if(r > 0.2)down();
                else if(r > 0.1) left();
                else right() ;
            }
        };
    }

    
    
    public interface Observer{
        void changed();
    }




}
