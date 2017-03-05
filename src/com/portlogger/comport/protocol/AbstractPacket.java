package com.portlogger.comport.protocol;

/**
 * Created by maksimov_mu on 27.02.2017.
 */
public abstract class AbstractPacket {
    protected byte[] data;
    protected long timeStamp;
    protected int reSendMax;
    protected int reSendCount;
    protected String title;

    public AbstractPacket() {

    }

    public AbstractPacket(int length) {
        this.data = new byte[length];
    }


    public long getTimeStamp() {
        return timeStamp;
    }


    public void setTimeStamp() {
        this.timeStamp = System.currentTimeMillis();
    }


    public int getReSendMax() {
        return reSendMax;
    }


    public void setReSendMax(int reSendMax) {
        this.reSendMax = reSendMax;
    }


    public int getReSendCount() {
        return reSendCount;
    }


    public void setReSendCount(int reSendCount) {
        this.reSendCount = reSendCount;
    }


}