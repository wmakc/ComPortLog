package com.portlogger.comport.protocol;

/**
 * Created by maksimov_mu on 27.02.2017.
 */
import com.portlogger.comport.protocol.Packet;

public class SetDeviceNumber extends Packet {

    public SetDeviceNumber(int deviceNumber) {
        super((byte) 0x0E, (byte) 0x0F, (byte) deviceNumber, (byte) 0x1F, null);
    }

}
