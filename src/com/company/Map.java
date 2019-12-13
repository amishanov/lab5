package com.company;

import java.awt.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.HashMap;

public class Map implements Serializable{
    //Вложенный класс путь
      static public class Path  implements Serializable{
        private String [] points;
        private int time;
        Path(String points[],int time){
            this.points=points;
            this.time=time;
        }
        public String showPath(){
            return (points[0]+" "+points[1]+" "+time);
        }
        public String[] getPoints(){
            return points;
        }
        public String getPlace1(){
            return points[0];
        }
        public String getPlace2(){
            return points[1];
        }
        public int getTime() {
            return time;
        }
    }

    private ArrayList<Path> paths;
    private ArrayList points;
    private int ways[][];
     Map(){
         paths=new ArrayList<>(); //массив путей
         points=new ArrayList(); //массив мест
         ways=new int[100][100]; //матрица смежности путей. Отражает, есть ли путь(ребро) между двумя точками. Если путя нет, то равняется бесконечности (в рамках программы - Max_int)
         for (int i=0;i<100;i++)
             for(int j=0;j<100;j++)
                 ways[i][j]=Integer.MAX_VALUE;
     }
    public void addPath(String place1,String place2,int time){
        String[] arr=new String[2];
        arr[0]=place1; arr[1]=place2;
        Path path=new Path(arr,time);
        paths.add(path);

    }
     public void setStuff(){
         String[] tempStringArr = new String[2];
         for (Path p : paths
              ) {
             tempStringArr = p.getPoints();
             //Проверяем наличие точки в массиве мест, если этого места нет, то добавляем его
             if (!points.contains(tempStringArr[0]))
                 points.add(tempStringArr[0]);
             if (!points.contains(tempStringArr[1]))
                 points.add(tempStringArr[1]);
             //Добавляем путь в матрицу смежности
             ways[points.indexOf(tempStringArr[0])][points.indexOf(tempStringArr[1])]=p.getTime();
             ways[points.indexOf(tempStringArr[1])][points.indexOf(tempStringArr[0])]=p.getTime();

         }
     }

    public ArrayList<Path> getPaths(){
        return paths;
    }

    public ArrayList genShortWay(String place1, String place2){

        int MAX=Integer.MAX_VALUE; //Бесконечность. Нужна для проверки наличия ребра
        int pointCount=points.size(); //Количетсво мест
        int D[]=new int[pointCount]; //массив меток мест
        int P[]=new int[pointCount]; //массив точек
        int startPoint=points.indexOf(place1); //стартовая точка
        boolean []visited=new boolean[pointCount]; //массив посещенных точек (индекс соответствует индексу точки в массиве points)
        //Заполняем D, P и visited значениями по умолчанияю
        Arrays.fill(D,MAX); Arrays.fill(P,-1);
        Arrays.fill(visited,false);
        D[startPoint]=0; //устанавливаем метку стартовой точки за 0

        for (int i=0;i<pointCount;i++)
            D[i]=ways[startPoint][i]; //устанавливаем метки на все остальные точки, на основе наличия расстояния до начальной метки  (если прямого пути(ребра) нет, то бесконечность)
        int index=0, u=0, min; //index - индекс точки, метка которой имеет минимальное значение

        //алгоритм Дейсктры
        for (int i=0;i<pointCount;i++){
            min=MAX;
            for (int j=0;j<pointCount;j++){
                if (!visited[j] && D[j]<min){
                    min=D[j]; index=j;
                }
            }
            u=index;
            visited[u]=true;
            for (int j=0;j<pointCount;j++){
                if (!visited[j] && ways[u][j]!=MAX && D[u]!=MAX && (D[u]+ways[u][j]<D[j])){
                    D[j]=D[u]+ways[u][j];
                    P[j]=u;
                }
            }
        }
        //расложение маршрута
        ArrayList stack=new ArrayList();
        int target=points.indexOf(place2);
        ArrayList res= new ArrayList();
        if (D[points.indexOf(place2)] < Integer.MAX_VALUE) {
            for (int v = target; v != -1; v = P[v])
                stack.add(0, v);
            ArrayList sp = new ArrayList(stack.size());
            for (int i = 0; i < stack.size(); i++)
                sp.add(stack.get(i));

            res.add(place1);
            for (int i = 0; i < sp.size(); i++)
                res.add(points.get((Integer) sp.get(i)));
        }
        return res;
    }
    public String show(){
         String res="";
         for (int i=0; i<paths.size();i++){
             res+=paths.get(i).showPath()+"\n";
         }
         return res;
    }
    public void loadPaths(File f) {
        try{
            FileReader fr = new FileReader(f);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            String p1,p2;
            int time;
            while ((line = reader.readLine())!= null){
                p1 = new String(line);
                line = reader.readLine();
                p2 = new String(line);
                line = reader.readLine();
                time = Integer.parseInt(line);
                String p[] = {p1,p2};
                paths.add(new Path(p, time));
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public ArrayList getPoints() {
        return points;
    }

    public void savePaths(File f){
         try{
             FileWriter fr = new FileWriter (f);
             BufferedWriter writer = new BufferedWriter(fr);
             String line;
             String p1,p2;
             int time;
             for (Path p: paths
             ) {
                 writer.write(p.getPlace1()+'\n');
                 writer.write(p.getPlace2()+'\n');
                 Integer costil = p.getTime();
                 writer.write(costil.toString()+'\n');
             }
             writer.close();
         }catch (Exception e){
             System.out.println(e.toString());
         }
    }
}
