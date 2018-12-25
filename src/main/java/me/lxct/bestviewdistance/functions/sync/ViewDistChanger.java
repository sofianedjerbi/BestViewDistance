package me.lxct.bestviewdistance.functions.sync;

public class ViewDistChanger implements Runnable {

    private Object pcm;
    private int vdist;

    public ViewDistChanger(Object pcm, int vdist) {
        this.pcm = pcm;
        this.vdist = vdist;
    }

    @Override
    public void run() {
        try {
            pcm.getClass().getMethod("a", int.class).invoke(pcm, vdist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}