package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationWindow extends JFrame {
    JButton button = new JButton("Локация: ");
    JLabel label = new JLabel();
    JTextField tf = new JTextField(12);
     LocationWindow() {
         super("Выбор локации");
         PizzaSystem pz = new PizzaSystem();
         setSize(250,200);

         JPanel pane = new JPanel();
         add(pane);

         button.setSize(50, 10);
         button.setFont(new Font("Times New Roman", Font.PLAIN, 14));
         tf.setSize(50, 10);

         label.setText("Текущая локация курьера: " + pz.getCurrentLocation());
         Box box1 = Box.createHorizontalBox();
         box1.add(button);
         box1.add(Box.createHorizontalGlue());
         box1.add(tf);
         box1.add(Box.createHorizontalStrut(12));
         Box box2 = Box.createHorizontalBox();
         box2.add(label);
         box2.add(Box.createHorizontalGlue());
         box2.add(Box.createHorizontalStrut(12));
         pane.add(box2);
         pane.add(box1);
         setVisible(true);
         button.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if (!tf.getText().equals("")){
                     pz.setCurrentLocation(tf.getText());
                     label.setText("Текущая локация курьера: " + pz.getCurrentLocation());
                 }
                 else{
                     JOptionPane.showMessageDialog(null, "Поле не заполнено", "", JOptionPane.PLAIN_MESSAGE);
                 }
             }
         });
     }
}
