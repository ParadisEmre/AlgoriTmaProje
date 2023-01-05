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
        
        for (int i = 0 ; i < iterationCount ; i++)
        {
            // (weight.data) - bias => 1 condition
            // learningRate * ((weight * 2 * lambda) - (data * classification))
            // if condition == false change bias to new SWM point
        }
    }
    // abs (xi. weight)-bias 
    public float predict(Vector3 xi){
            return Math.signum(Vector3.dot(xi,weight)-bias);//hey
    }
}