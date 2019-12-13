package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


class LoginWindow extends JFrame implements ActionListener {

    //Для того, чтобы впоследствии обращаться к содержимому текстовых полей, надо сделать их членами класса окна
    JTextField loginField;
    JPasswordField passwordField;
    JButton ok;
    JButton cancel;
    PizzaSystem pzsys;


    LoginWindow() {
        super("Вход в систему");
        pzsys = new PizzaSystem();
        this.setVisible(true);
        // Настраиваем первую горизонтальную панель (для ввода логина)
        Box box1 = Box.createHorizontalBox();
        JLabel loginLabel = new JLabel("Логин:    ");
        loginField = new JTextField(15);
        box1.add(loginLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(loginField);

        // Настраиваем вторую горизонтальную панель (для ввода пароля)
        Box box2 = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("Пароль:");
        passwordField = new JPasswordField(15);
        box2.add(passwordLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(passwordField);

        // Настраиваем третью горизонтальную панель (с кнопками)
        Box box3 = Box.createHorizontalBox();
        ok = new JButton("OK");
        box3.add(Box.createHorizontalGlue());
        box3.add(ok);
        box3.add(Box.createHorizontalStrut(12));


        //Слушатели
        ok.addActionListener(this);


        // Уточняем размеры компонентов loginLabel.setPreferredSize(passwordLabel.getPreferredSize());
        // Размещаем три горизонтальные панели на одной вертикальной
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(box3);
        setContentPane(mainBox);
        pack();
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = "";
        if (e.getSource() == ok) {
            message = "";
            if (pzsys.findUser(loginField.getText(), passwordField.getText())){
                JOptionPane.showMessageDialog(null, "Вы вошли в систему как: "+ pzsys.getUser(), "", JOptionPane.PLAIN_MESSAGE);
                mainWindow.mapButton.setEnabled(true);
                mainWindow.mainButton.setEnabled(true);
                mainWindow.locationButton.setEnabled(true);

                mainWindow.label.setText("Здравствуйте, "+ pzsys.getLogin());
                mainWindow.label1.setText("Ваше местоположение: "+'\n'+pzsys.getCurrentUser().getPlace());
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "Неверный логин или пароль", "", JOptionPane.PLAIN_MESSAGE);
            }
        }
        if (e.getSource() == cancel) {

        }

    }
}
