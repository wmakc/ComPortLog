package com.portlogger.comport.protocol;

import java.util.Arrays;

/**
 * Created by maksimov_mu on 27.02.2017.
 */

public class CRC16Calc {

    public static byte[] calc(byte[] bytes) {
        int crc_0 = 0xFFFF; // initial value

        int crc_1 = 0x0000;
        byte[] crc = new byte[2];

        int i, j;

        for (i = 0; i < bytes.length; i++) // Loop through characters of input
        // array
        {
            crc_0 ^= bytes[i] & 0x00ff; // XOR current character with 0x00ff
            for (j = 0; j < 8; j++) // Loop through characters bits
            {
                crc_1 = (crc_0 >> 1) & 0x7fff; // shift result right one place
                // and store

                if ((crc_0 & 0x0001) == 0x0001) // if pre-shifted value bit 0 is
                // set
                {
                    crc_0 = (crc_1 ^ 0xa001); // XOR the shifted value with
                    // 0xa001
                } else // if pre-shifted value bit 0 is not set
                {
                    crc_0 = crc_1; // set the pre-shifted value equal to the
                    // shifted value
                }
            }
        }

        crc[1] = (byte) ((crc_0 / 256) & 0x00ff); // Hi byte
        crc[0] = (byte) (crc_0 & 0x00ff); // Lo byte

        return crc;
    }

    public static byte[] calc(Packet packet) {


        return calc(Arrays.copyOfRange(packet.getData(), 0, packet.getData().length - 2));

    }

    public static boolean CrcTest(byte[] data) {
        byte nData[] = Arrays.copyOfRange(data, 0, data.length - 2);
        byte crc[] = calc(nData);

        if (crc[0] == data[data.length - 2] && crc[1] == data[data.length - 1]) {
            return true;
        } else {
            return false;
        }

    }
}