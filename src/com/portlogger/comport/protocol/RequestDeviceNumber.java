package com.portlogger.comport.protocol;

/**
 * Created by maksimov_mu on 27.02.2017.
 */

public class RequestDeviceNumber extends Packet {

    public RequestDeviceNumber(int deviceNumber) {
        super((byte) 0x0F, (byte) 0x0E, (byte) deviceNumber, (byte) 0x2F, null);
    }

}