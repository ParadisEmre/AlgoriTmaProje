package org.example;

import java.util.List;

public class SVM {
    
    public float bias;
    public Vector3 weight;
    public int iterationCount;
    public float learningRate = 0.001f;
    public float lambda = 0.01f;
    
    public SVM(int iterationCount, float learningRate, float lambda) 
    {
       this.iterationCount=iterationCount;
       this.learningRate = learningRate;
       this.lambda = lambda;
    }
    
    public void train(List<DataEntry> dataSet){
        weight = Vector3.ZERO;

        for (int i = 0; i < iterationCount; i++) {
            for (int j = 0; j < dataSet.size(); j++) {
                DataEntry entry = dataSet.get(j);
                Boolean condition = entry.classification * (Vector3.dot(weight,entry.properties)-bias) >= 1;
                if (condition){
                    var toSub = Vector3.multiply(weight,2*lambda*learningRate);
                    weight  = Vector3.subtract(weight,toSub);
                }else{
                    // lr * (w * 2 * lmb - prop * class)
                    var toSub = Vector3.multiply(Vector3.subtract(Vector3.multiply(weight,2 * lambda),
                            Vector3.multiply(entry.properties,entry.classification)),learningRate);
                    weight = Vector3.subtract(weight,toSub);
                    bias -= learningRate * entry.classification;//Change bias for new svm point
                }
            }
        }
    }
    // abs (xi. weight)-bias 
    public float predict(Vector3 xi){
            return Math.signum(Vector3.dot(xi,weight)-bias);//hey
    }

    public float predict(float x,float y,float z){
        return Math.signum(Vector3.dot(new Vector3(x,y,z),weight)-bias);//hey
    }
}