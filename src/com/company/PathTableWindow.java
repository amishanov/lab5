package com.company;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class PathTableWindow extends JFrame {
    JTextField f1;
    JTextField f2;
    JTextField f3;
    JTable table;

    JButton loadButton;
    JButton saveButton;
    JButton addButton;
    JFileChooser fileChooser;
    PizzaSystem pz;
    Map map;
    ArrayList<Map.Path> paths;
    File file;
    PathTableWindow(){
        super("Таблица путей");
        fileChooser = new JFileChooser();
        pz = new PizzaSystem();
        paths = new ArrayList<>();
        map = new Map();
        setVisible(true);



        Box box1 = Box.createHorizontalBox();
        loadButton = new JButton("Загрузить пути из файла");
        loadButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор файла для загрузки");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showDialog(null, "Выбрать");
                if (result == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    map.loadPaths(file);
                    paths = map.getPaths();
                    map.setStuff();
                    pz.setMap(map);
                    saveButton.setEnabled(true);
                    ((MyTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });
        Box box3 = Box.createHorizontalBox();
        f1 = new JTextField("Место 1");
        f2 = new JTextField("Место 2");
        f3 = new JTextField("Время");
        box3.add(f1);
        box3.add(f2);
        box3.add(f3);

        box1.add(loadButton);
        box1.add(Box.createHorizontalStrut(6));
        saveButton = new JButton("Сохранить пути в файл");
        saveButton.setEnabled(false);
        saveButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Сохранение файла");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION){
                    try {
                        File file = fileChooser.getSelectedFile();
                        map.savePaths(file);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Что-то пошло не так...", "", JOptionPane.PLAIN_MESSAGE);
                        System.out.println(ex.toString());
                    }
                }
            }
        });
        box1.add(saveButton);

        addButton = new JButton("Добавить путь");
        addButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String p[] = {f1.getText(), f2.getText()};
                    int time = Integer.parseInt(f3.getText());
                    paths.add(new Map.Path(p,time));
                    map.setPaths(paths);
                    map.setStuff();
                    pz.setMap(map);
                    saveButton.setEnabled(true);
                    ((MyTableModel) table.getModel()).fireTableDataChanged();
                }catch (Exception ex){
                    System.out.println(ex.toString());
                    JOptionPane.showMessageDialog(null, "Что-то пошло не так...(Возможно вы выбили строку в поле времени)", "", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        box1.add(Box.createHorizontalStrut(6));
        box1.add(addButton);

        table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(950, 100));

        JScrollPane jscrlp = new JScrollPane(table);
        Box mainBox = Box.createVerticalBox();
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box3);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(jscrlp);
        setContentPane(mainBox);
        pack();
    }
    public class MyTableModel extends AbstractTableModel {
        @Override
        public int getRowCount() {
            return paths.size();
        }

        @Override
        public Object getValueAt(int r, int c) {
            switch (c) {
                case 0:
                    return paths.get(r).getPlace1();
                case 1:
                    return paths.get(r).getPlace2();
                case 2:
                    return paths.get(r).getTime();
                default:
                    return "";
            }
        }
        @Override
        public String getColumnName(int c) {
            String result = "";
            switch (c) {
                case 0:
                    result = "Место 1";
                    break;
                case 1:
                    result = "Место 2";
                    break;
                case 2:
                    result = "Время";
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
