package com.portlogger.comport;

/**
 * Created by max on 26.02.2017.
 */

import com.portlogger.ui.WindowWork;
import jssc.SerialPort;  /*Импорт классов библиотеки jssc*/
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class ComPort {
    private static SerialPort serialPort; /*Создаем объект типа SerialPort*/

    String namePort;
    int baudrate;
    int dataBits;
    int parity;
    int stopBits;
    int flowControl;

    public ComPort(String name, int baud, int data, int par, int stop, int flow)
    {
        namePort = name;
        baudrate = baud;
        dataBits = data;
        parity = par;
        stopBits = stop;
        flowControl = flow;
    }

    public static boolean openPort(String name)
    {
        serialPort = new SerialPort (name); /*Передаем в конструктор суперкласса имя порта с которым будем работать*/
        try {
            serialPort.openPort (); /*Метод открытия порта*/
            serialPort.setParams (SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); /*Задаем основные параметры протокола UART*/
            serialPort.setEventsMask (SerialPort.MASK_RXCHAR); /*Устанавливаем маску или список события на которые будет происходить реакция. В данном случае это приход данных в буффер порта*/
            serialPort.addEventListener (new EventListener()); /*Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс*/
            return true;
        }
        catch (SerialPortException ex) {
            System.out.println (ex);
        }
        return false;
    }

    public static boolean closePort()
    {
        try {
            serialPort.closePort (); /*Метод открытия порта*/
            return true;
        }
        catch (SerialPortException ex) {
            System.out.println (ex);
        }
        return false;
    }

    private static class EventListener implements SerialPortEventListener { /*Слушатель срабатывающий по появлению данных на COM-порте*/
        public void serialEvent (SerialPortEvent event) {
            if (event.isRXCHAR () && event.getEventValue () > 0){ /*Если происходит событие установленной маски и количество байтов в буфере более 0*/
                try {
                    String data = serialPort.readString (event.getEventValue ()); /*Создаем строковую переменную  data, куда и сохраняем данные*/
                    System.out.print (data);/*Выводим данные на консоль*/
                    WindowWork.logOut(data);
                }
                catch (SerialPortException ex) {
                    System.out.println (ex);
                }
            }
        }
    }

    public static String[] getComPorts()
    {
        String[] portNames = SerialPortList.getPortNames();
        return portNames;
    }

}