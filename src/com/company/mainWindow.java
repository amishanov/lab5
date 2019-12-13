package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class mainWindow extends JFrame{
    static JButton userButton = new JButton("Зарегистрироваться");
    static JButton mainButton = new JButton("Посмотреть зарегестрированных пользователей");
    static JButton loginButton = new JButton("Войти в систему");
    static JButton mapButton = new JButton("Настроить карту");
    static JButton locationButton = new JButton("Местоположение курьера");
    static JLabel label = new JLabel("Вы не авторизированы");
    static JLabel label1 = new JLabel("");
    static JFrame frame = new JFrame();
    public mainWindow(){
    }



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PizzaSystem ps = new PizzaSystem();
        try {
            ps.userLoad();
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Что-то пошло не так " + e.toString());
        }

        frame.setTitle("Главное окно");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,280,500);

        frame.setVisible(true);

        Container container = frame.getContentPane();
        container.setLayout(null);
        label.setLocation(10, 0);
        label.setSize(250,20);
        label1.setLocation(10, 15);
        label1.setSize(250,20);
        container.add(label);
        container.add(label1);
        loginButton.setSize(250,30);
        loginButton.setLocation(10,40);
        userButton.setSize(250,30);
        userButton.setLocation(10,80);
        mainButton.setEnabled(false);
        mainButton.setSize(250,30);
        mainButton.setLocation(10,120);
        mapButton.setEnabled(false);
        mapButton.setSize(250,30);
        mapButton.setLocation(10,160);
        locationButton.setEnabled(false);
        locationButton.setSize(250,30);
        locationButton.setLocation(10,200);
        container.add(loginButton);
        container.add(userButton);
        container.add(mainButton);
        container.add(mapButton);
        container.add(locationButton);
        container.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        loginButton.addActionListener(new UserButtonListener());
        userButton.addActionListener(new LoginButtonListener());
        locationButton.addActionListener(new LocationButtonListener());
        mapButton.addActionListener(new MapButtonListener());
        mainButton.addActionListener(new AdminButtonListener());

    }
    static class UserButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            LoginWindow loginWindow = new LoginWindow();
        }
    }
    static class LoginButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            UserSetWindow l = new UserSetWindow();
        }
    }
    static class LocationButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            LocationWindow lw = new LocationWindow();
        }
    }
    static class MapButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            MapWindow t = new MapWindow();
        }
    }
    static class AdminButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            AdminWindow aw = new AdminWindow();
        }
    }
}
