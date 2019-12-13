package com.company;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzaSystem {
    private static Map map; //= new Map();
    private static ArrayList<User> users; //= new ArrayList<>();
    private static User currentUser; //= new User();
    private static String currentLocation; //= new String();
    PizzaSystem(){
       // map = new Map();
        //currentUser = new User();
        //currentLocation = new String();
        //users = new ArrayList<>();
    }
    public static void main(String[] args)throws IOException, ClassNotFoundException {
        users = new ArrayList<>();
        map = new Map();
        currentUser = new User();
        currentLocation = "";
        //User b = new User("Алексей", "A", "1234");
        //User c = new User("Иван", "I", "1234");
        //Client a = new Client("Саша", "C", "1234", "Москва");
        //Client d = new Client("Вова","V", "1234","Подольск");


        //map.addPath("B", "A", 2);
        //currentLocation = "Подольск";
        //currentUser = a;
    }

    public void setCurrentLocation(String currentLocation) {
        PizzaSystem.currentLocation = currentLocation;
    }

    public static ArrayList<User> addUser(ArrayList<User> users, String name, String login, String password, String rep) {
        users.add(new User(name, login, password));
        return (users);
    }

    public static ArrayList<User> addClient(ArrayList<User> users, String name, String login, String password, String place) {
        users.add(new Client(name, login, password, place));
        return (users);
    }
    public String getCurrentLocation(){
        return currentLocation;
    }
    public static void addPath(String p1, String p2, int time){
        map.addPath(p1,p2,time);
    }
    public  void addUser(User u){
        try {
            userLoad();
            users.add(u);
            userSave();
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.toString()+ " Ой-ёй, что-то пошло не так");
        }
    }
    public String getUser(){
        return currentUser.getLogin()+" Ваше местоположение: "+currentUser.getPlace();
    }
    public String getLogin(){
        return currentUser.getLogin();
    }

    public boolean findUser(String login, String password) {
            for (User i : users
            ) {
                String s1 = i.getLogin();
                String s2 = i.getPassword();
                if ((s1.equals(login)) && (s2.equals(password))) {
                    currentUser = i;
                    return true;
                }
            }
            return false;
    }
    public String generate(String yourPlace, String courierPlace){
        map.setStuff();
        ArrayList res = map.genShortWay(yourPlace,courierPlace);
        String s = "Маршрут: ";
        for (int i=0; i<res.size(); i++)
            s += " - " + res.get(i);
        return s;
    }
    public static void save()throws IOException {
        File f = new File("map.txt");
        f.createNewFile();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("map.txt"));
        out.writeObject(map);

    }
    public static void load() throws IOException, ClassNotFoundException{
        try {
        File f = new File("map.txt");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("map.txt"));
        map = (Map) in.readObject();
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.toString() + " Что-то пошло не так");
        }
    }
    public void userLoad() throws IOException, ClassNotFoundException{
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.txt"));
            users = (ArrayList<User>) in.readObject();
            in.close();;
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.toString() + " Что-то пошло не так");
        }
    }
    public void userSave() throws IOException{
        //File f = new File("users.txt");
        //PrintWriter writer = new PrintWriter(f); writer.print(""); writer.close();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("users.txt"));
        out.writeObject(users);
        out.close();
    }
    public Map getMap() {
        return map;
    }
    public ArrayList<Map.Path> getPaths(){
        return(map.getPaths());
    }
    public void loadPaths(File f){
        map.loadPaths(f);
    }
    public void setMap(Map m){
        map = m;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
