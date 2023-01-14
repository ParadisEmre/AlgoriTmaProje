package org.example;

import java.text.Format;

public class Main {

    //Inline func
    public static void pline(Object o){
        System.out.println(o.toString());
    }
    public static void testPoint(SVM svm,float x,float y,float z,int expected){
        int result = Math.round(svm.predict(x,y,z));
        String answer = (expected == result) ? "CORRECT" : "WRONG";
        pline(String.format("For point [%6.2f %6.2f %6.2f] EXPECTED %+1d PREDICTED %+1d [%s]",x,y,z,expected,result,answer));
    }

    public static void main(String[] args) {
        SVM svm = new SVM(1000, 0.001f, 0.01f);
        SmartRandom smartRandom = new SmartRandom();

        //Test Case1
        pline("====================================================================");
        pline("TEST CASE 1");
        pline("Plane [z = 1]");
        smartRandom.setPlane(0,0,1,1);//z = 1
        smartRandom.createRandomVector(10000,0,100);
        var data = smartRandom.getDataSet();
        pline("Training SVM");
        svm.train(data);
        testPoint(svm,0,0,4,1);
        testPoint(svm,1,5,12,1);
        testPoint(svm,-56,32.1f,10,1);
        testPoint(svm,0,0,-9,-1);
        testPoint(svm,5,-9,-21.9f,-1);
        testPoint(svm,-29.13f,0.1f,-11.11f,-1);

        //RESET ALL
        svm = new SVM(1000, 0.001f, 0.01f);
        smartRandom = new SmartRandom();
        //


        //Test Case2
        pline("====================================================================");
        pline("TEST CASE 2");
        pline("Plane [x + z = 10]");
        smartRandom.setPlane(1,0,1,10);//x + z = 10
        smartRandom.createRandomVector(1000,0,50);
        data = smartRandom.getDataSet();
        pline("Training SVM");
        svm.train(data);
        testPoint(svm,30,0,60,1);
        testPoint(svm,15,-10,51.2f,1);
        testPoint(svm,-5,-52.1f,88.32f,1);
        testPoint(svm,0,0,0,-1);
        testPoint(svm,-10,-21.5f,5.1f,-1);
        testPoint(svm,54.19f,23,-80,-1);

        //RESET ALL
        svm = new SVM(1000, 0.001f, 0.01f);
        smartRandom = new SmartRandom();
        //
        
        //Test Case3
        pline("====================================================================");
        pline("TEST CASE 3");
        pline("Plane [x + y + z = 25]");
        smartRandom.setPlane(1,1,1,25);//x + z = 10
        smartRandom.createRandomVector(10000,0,50);
        data = smartRandom.getDataSet();
        pline("Training SVM");
        svm.train(data);
        testPoint(svm,36,4,8.1f,1);
        testPoint(svm,2.4f,-5,16,-1);
        testPoint(svm,12,18.2f,5,1);
        testPoint(svm,4,5.8f,28,1);
        testPoint(svm,-16,3.7f,-7,-1);
        testPoint(svm,0,39.2f,-7.2f,1);
        // 1 üstünde -1 altında
    }
}