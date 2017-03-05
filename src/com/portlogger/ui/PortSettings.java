package com.portlogger.ui;

/**
 * Created by max on 26.02.2017.
 */

import com.portlogger.comport.ComPort;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*; /*Подключаем библиотеку для создания апплетов и независимых
                                приложений с графическим интерфейсом.*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PortSettings  extends JFrame { /*Создаем подкласс WindowWork   класса Frame*/
    public PortSettings () /*Конструктор класса*/ {

        super("Настройки COM-порта");    /*Вызываем конструктор суперкласса и передаем ему параметр, в данном случае имя программы*/

        setSize(350, 350);  /*Метод суперкласса для установкиразмеров окна, в пикселях*/

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // При нажатии крестика окно закрывается

        Font font = new Font("Verdana", Font.PLAIN, 18); // Создаем шрифт для компонентов

        //Создаем объекты
        JPanel mainPanel = new JPanel();                                      // Создание панели, на которой все будет размещаться
        mainPanel.setLayout(new GridLayout(6,2,5,5)); // Способ размещения - сетка

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                String item = (String)box.getSelectedItem();
                //lablName.setText(item);
            }
        };

        final JLabel lablName = new JLabel("COM-port name");    // Создаем метку с именем "COM-port name"
        lablName.setAlignmentX(LEFT_ALIGNMENT);                      // Привязка к левому краю
        lablName.setFont(font);                                      // Задаем шрифт
        mainPanel.add(lablName);                                     // Размещаем метку на панели
        String[] itemsName = ComPort.getComPorts();
        JComboBox cbName = new JComboBox(itemsName);
        cbName.setFont(font);
        cbName.setAlignmentX(RIGHT_ALIGNMENT);
        cbName.addActionListener(actionListener);
        mainPanel.add(cbName);

        final JLabel lablBaudrate = new JLabel("Baudrate");
        lablBaudrate.setAlignmentX(LEFT_ALIGNMENT);
        lablBaudrate.setFont(font);
        mainPanel.add(lablBaudrate);


        String[] itemsBaudrate = {
                "110",
                "300",
                "600",
                "1200",
                "2400",
                "4800",
                "9600",
                "14400",
                "19200",
                "38400",
                "56000",
                "57600",
                "115200",
                "128000",
                "256000"
        };
        JComboBox cbBaudrate = new JComboBox(itemsBaudrate);
        cbBaudrate.setFont(font);
        cbBaudrate.setAlignmentX(RIGHT_ALIGNMENT);
        cbBaudrate.addActionListener(actionListener);
        mainPanel.add(cbBaudrate);

        final JLabel lablData = new JLabel("DataBits");
        lablData.setAlignmentX(LEFT_ALIGNMENT);
        lablData.setFont(font);
        mainPanel.add(lablData);
        String[] itemsData = {
                "5",
                "6",
                "7",
                "8"
        };
        JComboBox cbData = new JComboBox(itemsData);
        cbData.setFont(font);
        cbData.setAlignmentX(RIGHT_ALIGNMENT);
        cbData.addActionListener(actionListener);
        mainPanel.add(cbData);

        final JLabel lablParity = new JLabel("Parity");
        lablParity.setAlignmentX(LEFT_ALIGNMENT);
        lablParity.setFont(font);
        mainPanel.add(lablParity);
        String[] itemsParity = {
                "No",
                "Odd",
                "Even",
                "Mark",
                "Space"
        };
        JComboBox cbParity = new JComboBox(itemsParity);
        cbParity.setFont(font);
        cbParity.setAlignmentX(RIGHT_ALIGNMENT);
        cbParity.addActionListener(actionListener);
        mainPanel.add(cbParity);

        final JLabel lablStopbits = new JLabel("StopBits");
        lablStopbits.setAlignmentX(LEFT_ALIGNMENT);
        lablStopbits.setFont(font);
        mainPanel.add(lablStopbits);
        String[] itemsStopbits = {
                "1",
                "1,5",
                "2"
        };
        JComboBox cbStopbits = new JComboBox(itemsStopbits);
        cbStopbits.setFont(font);
        cbStopbits.setAlignmentX(RIGHT_ALIGNMENT);
        cbStopbits.addActionListener(actionListener);
        mainPanel.add(cbStopbits);

        final JLabel lablFlowcontrol = new JLabel("FlowControl");
        lablFlowcontrol.setAlignmentX(LEFT_ALIGNMENT);
        lablFlowcontrol.setFont(font);
        mainPanel.add(lablFlowcontrol);
        String[] itemsFlowcontrol = {
                "No",
                "RTS/CTS",
                "DTR/DSR"
        };
        JComboBox cbFlowcontrol = new JComboBox(itemsFlowcontrol);
        cbFlowcontrol.setFont(font);
        cbFlowcontrol.setAlignmentX(RIGHT_ALIGNMENT);
        cbFlowcontrol.addActionListener(actionListener);
        mainPanel.add(cbFlowcontrol);
        //mainPanel.add(eastPanel, BorderLayout.EAST);

        getContentPane().add(mainPanel);

        JButton bnOpen = new JButton("Open port");  /*Создаем кнопку и надпись на  ней*/
        add(bnOpen, BorderLayout.SOUTH); /*Кнопку в южной части*/
        bnOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ComPort.openPort((String)cbName.getSelectedItem());
                dispose();
            }
        });
    }
}
