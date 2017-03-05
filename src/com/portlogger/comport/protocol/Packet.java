package com.portlogger.comport.protocol;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;

/**
 * Created by maksimov_mu on 27.02.2017.
 */
public class Packet extends AbstractPacket {
    public static final byte START_BYTE = 0x7E;
    public static final byte MARKER_BYTE = 0x7C;

    public static final byte MASTER = 0x0E;
    public static final byte SLAVE = 0x0F;

    public static final byte BAZALT = 0x01;
    public static final byte DOSTUP = 0x02;
    public static final byte ZAMOK_3 = 0x03;
    public static final byte PTU = 0x11;
    public static final byte PTU_L = 0x12;
    public static final byte RECOILING_GATE = 0x13;
    public static final byte SWING_GATE = 0x14;

    private byte data[];

    public Packet(int length) {
        super(length);
    }

    public Packet(byte sender, byte receiver, byte deviceNumber, byte messageType, byte[] parametres) {
        super();
        if (parametres != null) {
            data = new byte[8 + parametres.length];

            for (int i = 0; i < parametres.length; i++) {
                data[6 + i] = parametres[i];
            }
        } else {
            data = new byte[8];
        }

        data[0] = START_BYTE;
        data[1] = (byte) data.length;
        data[2] = sender;
        data[3] = receiver;
        data[4] = deviceNumber;
        data[5] = messageType;

        byte crc[] = CRC16Calc.calc(this);
        data[data.length - 2] = crc[0];
        data[data.length - 1] = crc[1];

    }

    public Packet(byte data[]) {
        this.data = data;
    }

    // ======================================================�������==========================================
    public byte getStartByte() {
        return data[0];

    }

    public byte getLength() {
        return data[1];
    }

    public void setLength(int l) {

        if ((byte) l == getLength()) {

        } else {
            byte newData[] = new byte[l];
            System.arraycopy(data, 0, newData, 0, 6);
            System.arraycopy(data, data.length - 2, newData, l - 2, 2);
            data[1] = (byte) l;
        }

    }

    public void setLength(byte b) {
        data[1] = b;
    }

    public byte getSender() { // �� ����
        return data[2];
    }

    public void setSender(byte b) { // �� ����
        data[2] = b;
    }

    public byte getReceiver() { // ����
        return data[3];
    }

    public void setReceiver(byte b) { // ����
        data[3] = b;
    }

    public byte getGetDeviceNumber() { // ����� ����������
        return data[4];
    }

    public void setGetDeviceNumber(byte b) { // ����� ����������
        data[4] = b;
    }

    public byte getMessageType() { // ��� �������
        return data[5];
    }

    // ======================================================�������==========================================

    public void setMessageType(byte b) { // ��� �������
        data[5] = b;
    }

    public byte[] getParameters() {

        return Arrays.copyOfRange(data, 6, data.length - 2);

    }

    public void setParameters(byte[] b) {
        if (getParameters().length == b.length)
            System.arraycopy(b, 0, data, 6, b.length);
        else {
            setLength(8 + b.length);
            System.arraycopy(b, 0, data, 6, b.length);
        }

    }

    public byte getCRC1() { // �RC1
        return data[data.length - 2];
    }

    public byte getCRC2() { // �RC1
        return data[data.length - 1];
    }

    public byte[] getData() {
        return data;
    }

    public byte[] getSendBytes() {
        byte nData[] = new byte[1024];
        int length = 1;
        int j = 0;
        nData[0] = START_BYTE;
        for (int i = 1; i < data.length; i++) {
            if (data[i] == START_BYTE) {
                nData[i + j] = MARKER_BYTE;
                nData[i + j + 1] = (byte) 0x9E;
                length += 2;
                j++;
            } else if (data[i] == MARKER_BYTE) {
                nData[i + j] = MARKER_BYTE;
                nData[i + j + 1] = (byte) 0x9C;
                length += 2;
                j++;
            } else {
                nData[i + j] = data[i];
                length++;
            }
        }


        return Arrays.copyOfRange(nData, 0, length);
    }

    public void setCRC() { // �RC
        byte crc[] = CRC16Calc.calc(data);

        data[data.length - 2] = crc[0];
        data[data.length - 1] = crc[1];
    }

    @Override
    public String toString() {

        return DatatypeConverter.printHexBinary(data);
    }

}