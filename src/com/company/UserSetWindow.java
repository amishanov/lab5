package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserSetWindow extends JFrame{
    private PizzaSystem pzsys;
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    public UserSetWindow() {
        super("Добавить пользователя");
        pzsys = new PizzaSystem();

        setSize(250,200);
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();
        JPanel pane = (JPanel) getContentPane();
        pane.setLayout(null);
        JButton b1 = new JButton("Добавить");
        JLabel label1 = new JLabel("Имя: ");
        JLabel label2 = new JLabel("Логин: ");
        JLabel label3 = new JLabel("Пароль: ");
        JLabel label4 = new JLabel("Место: ");

        box1.add(label1);
        box1.add(t1);
        box2.add(label2);
        box2.add(t2);
        box3.add(label3);
        box3.add(t3);
        box4.add(label4);
        box4.add(t4);
        box5.add(b1);
        pane.add(box1);
        pane.add(box2);
        pane.add(box3);
        pane.add(box4);
        pane.add(box5);

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        //mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(box2);
        //mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(box3);
        //mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(box4);
        //mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(box5);

        setContentPane(mainBox);
        setVisible(true);
        setResizable(false);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //тут бы вхреначить проперку на существование пары логин-пароль, но мне влом
                if (!t1.getText().equals("") && !t2.getText().equals("") && !t3.getText().equals("") && !t4.getText().equals("")) {
                    pzsys.addUser(new Client(t1.getText(), t2.getText(), t3.getText(), t4.getText()));
                    try{
                        pzsys.userSave();
                        dispose();
                    }catch (IOException ex){
                        System.out.println("Что-то пошло не так" + ex.toString());
                    }

                }
                else {
                    JOptionPane.showMessageDialog(null, "Не все поля заполнены", "", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

}
