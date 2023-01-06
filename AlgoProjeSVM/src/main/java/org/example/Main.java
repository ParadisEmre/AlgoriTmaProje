package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SVM svm = new SVM(1000, 0.001f, 0.01f);
        SmartRandom sr = new SmartRandom();
        sr.createPlane();
        sr.createRandomVector(2, 80, 70);
        sr.printPlaneEquation();
        
        
        
        
//        int i = 0;
//        for(Vector3 v:dt.planePoints){
//            if(i++ == 5){
//                System.out.println("\n");
//                i = 0;
//            }
//            System.out.print(v.toString()+"  ");
//        }  
//        System.out.println(dt.planePoints.size());
        
    }
}