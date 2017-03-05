package com.portlogger.comport.protocol;

/**
 * Created by maksimov_mu on 27.02.2017.
 */
public final class PacketType {
    public static final byte SET_DEVICE_NUMBER = 0x1F;// ��������� ������ ����������

    public static final byte REQEST_DEVICE_TYPE = 0x2F;// ������ ���� ����������
    public static final byte REPORT_DEVICE_TYPE = 0x3F;// ����� �� ������ ���� ����������

    public static final byte SEND_STATUS_POLLING = 0x1B;// ����� ���������
    public static final byte REPORT_STATUS_POLLING = 0x2B;// ����� �� ����� ���������

    public static final byte SEND_OPEN = (byte) 0xBB;// ������� ������ ����������������/ �������ܔ
    public static final byte SEND_CLOSE = (byte) 0xCC;// ������� ������ ���������������/ �������ܔ
    public static final byte SEND_STOP = (byte) 0xDD;// ������� ������ ����ϔ

    public static final byte REQEST_CYCLES = (byte) 0x1C;// ������� ������ ���������� ���������� ������
    public static final byte REPORT_CYCLES = (byte) 0x2C;// ����� �� ���������� ���������� ������
    public static final byte RESET_CYCLES = (byte) 0x3C;// ������� ���������� ������� ������

    public static final byte WRITE_REGISTER_PARAMS = (byte) 0x1A;// ������ ���������� � ������� ��
    public static final byte REQEST_REGISTER_PARAMS = (byte) 0x2A;// ������ ���������� �� �������� ��
    public static final byte REPORT_REGISTER_PARAMS = (byte) 0x3A;// ����� �� ������ ����������

    public static final byte REQEST_LOG = 0x1D;// ������ ����
    public static final byte REPORT_LOG = 0x2D;// ����� �� ������ ����
    public static final byte SEND_CLEAR_LOG = 0x3D;// ������� ����

    public static final byte OK = 0x0A;// ��������� ���������� ���������� �������
    public static final byte ERROR = 0x0B;// ��������� ������������ ���������� �������

    public static final byte RESET = 0x19;// �����
}
