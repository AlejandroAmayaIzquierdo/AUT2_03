package com.example.aut2_03;

import android.widget.TextView;

public class CounterTime implements Runnable {

    Thread thread;
    private TextView textView;

    private double seconds;
    private boolean pause = false;

    public CounterTime(TextView t){
        this.textView = t;
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }
    public void stopThread() {
        this.thread = null;
        this.textView.setText("00:00:00");
    }

    @Override
    public void run() {
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while(thread != null) {
            if(!pause){
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime);
                timer += delta;
                lastTime = currentTime;

                if(timer >= 1000000000) {
                    long seconds = timer / 1000000000;
                    long minutes = seconds / 60;
                    long hours = seconds / 3600;
                    long remainingMinutes = minutes % 60;
                    long remainingSeconds = seconds % 60;
                    textView.setText(hours + ":" + remainingMinutes + ":" + remainingSeconds);
                    timer = 0;
                }
            }
        }
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
