package sample.threads;

import sample.proccess.helper4DownInfo;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class thread4StartSpiderAgain implements Runnable{
    private helper4DownInfo helper;

    public void setHelper(helper4DownInfo helper) {
        this.helper = helper;
    }

    @Override
    public void run() {
        try {
            sleep(2000);
            helper.restartTheSpiders();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
