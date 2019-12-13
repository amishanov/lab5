package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapWindow extends JFrame {
    JLabel label;
    JButton generateButton;
    JButton pathButton;
    MapWindow(){
        super("");
        PizzaSystem pz = new PizzaSystem();
        this.setVisible(true);
        // Настраиваем первую горизонтальную панель
        Box box1 = Box.createHorizontalBox();
        label = new JLabel("Вы пока не сгенерировали путь");
        box1.add(label);
        box1.add(Box.createHorizontalStrut(6));

        // Настраиваем вторую горизонтальную панель
        Box box2 = Box.createHorizontalBox();
        generateButton = new JButton("Сгенерировать маршрут");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                if (pz.getCurrentLocation() == null){
                    JOptionPane.showMessageDialog(null, "Вы не уточнили положение курьера", "", JOptionPane.PLAIN_MESSAGE);
                }
                if (pz.getCurrentUser().getPlace() == null){
                    JOptionPane.showMessageDialog(null, "У вас нет местоположения", "", JOptionPane.PLAIN_MESSAGE);
                }
                if (!pz.getMap().getPoints().contains(pz.getCurrentLocation())){
                    JOptionPane.showMessageDialog(null, "вы ввели место курьера, отсутствующее на карте", "", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    label.setText(pz.generate(pz.getCurrentUser().getPlace(), pz.getCurrentLocation()));
                }
                }catch (Exception ex){
                    System.out.println(ex.toString());
                    String message = "Что-то пошло не так, возможно, маршрута несуществует";
                    JOptionPane.showMessageDialog(null, message, "", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        box2.add(generateButton);
        box2.add(Box.createHorizontalStrut(6));
        pathButton = new JButton("Настроить пути");
        pathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PathTableWindow tt = new PathTableWindow();
            }
        });
        box2.add(pathButton);

        // Уточняем размеры компонентов
        // Размещаем три горизонтальные панели на одной вертикальной
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        setContentPane(mainBox);
        pack();
        setResizable(false);

    }
}
