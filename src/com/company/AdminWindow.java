package com.company;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class AdminWindow extends JFrame {
    PizzaSystem pz;
    ArrayList<User> users;
    JTable table;
    AdminWindow(){
        super("Таблица путей");
        setVisible(true);
        try {
            pz = new PizzaSystem();
            pz.userLoad();
        }catch (Exception e){
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Дальше живут драконы", "", JOptionPane.PLAIN_MESSAGE);
            this.dispose();
        }
        users = pz.getUsers();

        table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(950, 100));
        JScrollPane jscrlp = new JScrollPane(table);
        Box mainBox = Box.createVerticalBox();
        mainBox.add(jscrlp);
        setContentPane(mainBox);
        pack();
    }
    public class MyTableModel extends AbstractTableModel {
        @Override
        public int getRowCount() {
            return users.size();
        }

        @Override
        public Object getValueAt(int r, int c) {
            switch (c) {
                case 0:
                    return users.get(r).getLogin();
                case 1:
                    return users.get(r).getPassword();
                case 2:
                    return users.get(r).getPlace();
                default:
                    return "этого здесь быть не должно";
            }
        }
        @Override
        public String getColumnName(int c) {
            String result = "";
            switch (c) {
                case 0:
                    result = "Логин";
                    break;
                case 1:
                    result = "Пароль";
                    break;
                case 2:
                    result = "Место";
                    break;

            }
            return result;
        }
        @Override
        public int getColumnCount() {
            return 3;
        }
    }
}
