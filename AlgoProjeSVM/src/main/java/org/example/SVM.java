package org.example;

import java.util.List;

public class SVM {
    public SVM(int iterationCount) {
       this.iterationCount=iterationCount;
    }
    public double bias;
    public Vector3 weight;
    public int iterationCount;
    public void train(List<DataEntry> dataSet){
        //TODO
    }
    // abs (xi. weight)-bias 
    public float predict(Vector3 xi){
            
            return (float) Math.signum(Vector3.dot(xi,weight)-bias);//hey
    }
}