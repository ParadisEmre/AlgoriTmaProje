package org.example;

public class Main {

    //Inline func
    public static void pline(Object o){
        System.out.println(o.toString());
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        SVM svm = new SVM(1000, 0.001f, 0.01f);
        SmartRandom sr = new SmartRandom();
        sr.createPlane();
        sr.createRandomVector(12, 70, 100);
        sr.printPlaneEquation();
//        var b = sr.getDataSet();
//        svm.train(b);
//        var a = svm.predict(1f,2f,3f);
//        pline(a);

        //Test Cases
    }
}