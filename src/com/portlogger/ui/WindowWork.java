package com.portlogger.ui;

import javax.swing.*;
/*import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
*/
/**
 * Created by max on 26.02.2017.
 */
import com.portlogger.comport.ComPort;

import java.awt.*; /*Подключаем библиотеку для создания апплетов и независимых
                                приложений с графическим интерфейсом.*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowWork extends JFrame{ /*Создаем подкласс WindowWork   класса Frame*/
    private static final String TITLE = "Logger";
    private ComPort port = new ComPort("COM3", 9600, 8, 0, 1, 0);
    private static Label myLabel;

    public WindowWork() /*Конструктор класса*/
    {
        super (TITLE);    /*Вызываем конструктор суперкласса и передаем ему параметр, в данном случае имя программы*/
        setSize (1280,650);  /*Метод суперкласса для установкиразмеров окна, в пикселях*/
        //Создаем объекты
        Font font = new Font("Verdana", Font.PLAIN, 11);

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menu.setFont(font);

        JMenuItem openItem = new JMenuItem("Open COM-port");
        openItem.setFont(font);
        menu.add(openItem);

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PortSettings settings = new PortSettings();
                settings.setVisible(true);
            }
        });

        JMenuItem closeItem = new JMenuItem("Close COM-port");
        closeItem.setFont(font);
        menu.add(closeItem);

        closeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ComPort.closePort();
            }
        });

        menu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        menu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ComPort.closePort();
                System.exit(0);
            }
        });

        menuBar.add(menu);
        setJMenuBar(menuBar);


        Button myButton = new Button ("Мониторинг");  /*Создаем кнопку и надпись на  ней*/
        myLabel = new Label ("Данные логгера"); /* Создаем текстовое поле и надпись в нем*/

        add (myLabel, BorderLayout.NORTH); /* С помощью менеджера размещения, располагаем текстовое поле в северной части окна*/
        add (myButton, BorderLayout.SOUTH); /*Кнопку в южной части*/
        myButton.addActionListener (new ActionListener () {    /*Для кнопки выбираем событие слушателя, и создаем новое событие в скобках.*/
            public void actionPerformed (ActionEvent e) {
                myLabel.setText ("Мониторинг"); /*Выполняется действие, т.е. при нажатии на кнопку в поле выводится сообщение  «Мониторинг» */
                PortSettings settings = new PortSettings();
                settings.setVisible(true);
            }
        });
    }

    public static void logOut(String data)
    {
        myLabel.setText(data);
    }


    public static void main (String[] args) {      //Точка входа программы
        WindowWork window = new WindowWork ();      //Создаем объект класса
        window.setVisible (true);                                //Устанавливаем видимость окна
           /*Наше окно запускается и отображается, при нажатии на кнопку меняется надпись в текстовом поле. Что бы закрыть окно необходимо добавить код обработки события, который работает следующим образом: мы вызываем для объекта log метод addWindowListener для того, чтобы назначить слушателя оконных событий. В качестве параметра создаем объект абстрактного класса WindowAdapter, в котором создаем класс и переопределяем метод для обработки события закрытия окна -  dispose.*/
        window.addWindowListener (new WindowAdapter () {
            public void windowClosing (WindowEvent e) {    // в качестве аргумента передаем событие
                e.getWindow ().dispose ();                               // уничтожает объект Frame
            }
        });
    }
}