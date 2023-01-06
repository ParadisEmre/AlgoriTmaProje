/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ZenBook
 */
public class SmartRandom {

    int upperBound = 100;//Bounds of the room
    int lowerBound = -100;
    int corrector = 50;//Corrector for shifting
    
    public Random rand = new Random();

    public List<Vector3> randomVectors = new ArrayList<>();//Random vectors that are on the plane and then shifted randomly

    List<Vector3> planeCreationPoints = new ArrayList<>();//Plane is created by these points
    List<Vector3> unitVectors = new ArrayList<>();//Unit vectors after various math operations
    
    float planeEquationEquality = 0;//Plane equartions right side
    Vector3 planeEquationCoefficients = new Vector3();//Plane equartions left side
    //-10                      60

    public void createRandomVector(int amount, int weightedZoneLower, int weightedZoneUpper) {//Amount of vectors that are going to be created is the first parameter
                                                                                              //weightedZoneLower is the weighted zones beginning
                                                                                              //weightedZoneUpper is the weighted zones upperbound
        int counter = 0;
        int CHANCE = 70;//Chance of its being in the weighted zone.
        float randomX = 0;//These three are the points that are created on the plane at the beginning.
        float randomY = 0;
        float randomZ = 0;

        if (weightedZoneLower < lowerBound || weightedZoneUpper > upperBound || weightedZoneUpper < weightedZoneLower) {//Check if the parameters are fine
            System.out.println("Incorrect weighted limits.");
            return;
        }

        while (counter < amount) {
            System.out.println("Calculating the random vector...");
            while (planeEquationCoefficients.x * randomX + planeEquationCoefficients.y * randomY + planeEquationCoefficients.z * randomZ != planeEquationEquality) {
                int weight = rand.nextInt(0, 100);
                if (weight <= CHANCE) {//Point is created in the weighted zone or not. Chance decides that
                    if (weightedZoneLower < 0) {//For the sake of rand.nextFloat() we have to check the negativity like this
                        randomX = rand.nextFloat(0, weightedZoneUpper - weightedZoneLower) + weightedZoneLower;
                        randomY = rand.nextFloat(0, weightedZoneUpper - weightedZoneLower) + weightedZoneLower;
                        randomZ = rand.nextFloat(0, weightedZoneUpper - weightedZoneLower) + weightedZoneLower;
                    } else {
                        randomX = rand.nextFloat(weightedZoneLower, weightedZoneUpper);
                        randomY = rand.nextFloat(weightedZoneLower, weightedZoneUpper);
                        randomZ = rand.nextFloat(weightedZoneLower, weightedZoneUpper);
                    }
                } else {
                    randomX = rand.nextFloat(0, upperBound - lowerBound) + lowerBound;
                    randomY = rand.nextFloat(0, upperBound - lowerBound) + lowerBound;
                    randomZ = rand.nextFloat(0, upperBound - lowerBound) + lowerBound;
                }
            }
            if (!randomVectors.contains(new Vector3(randomX, randomY, randomZ)) && !planeCreationPoints.contains(new Vector3(randomX, randomY, randomZ))) {//OBJEYE BAKIYOMUŞ BU YAA VECTORE EŞİTLİK FONKU YAZ CHECK İÇİN
                randomVectors.add(new Vector3(randomX, randomY, randomZ));//Point is added to randomVectors after various checks
                counter++;
                System.out.println(counter + ". random vector is set.");
            }
            randomX = rand.nextFloat(0, upperBound - lowerBound) + lowerBound;
            randomY = rand.nextFloat(0, upperBound - lowerBound) + lowerBound;
            randomZ = rand.nextFloat(0, upperBound - lowerBound) + lowerBound;
        }
        float unit = Vector3.magnitude(planeEquationCoefficients);//Calculating its unit magnitude

        printRandomVectors();

        for (Vector3 v : randomVectors) {
            unitVectors.add(Vector3.multiply(v, 1 / unit));//Calculating unit vectors of each vector
        }
        float randomNum = 0;
        for (int i = 0; i < randomVectors.size(); i++) {
            
            randomNum = rand.nextFloat(0, 100) - corrector;//Shifting every point so that they do not stay in the plane
            while(!checkBoundsForVector(Vector3.subtract(randomVectors.get(i), Vector3.multiply(unitVectors.get(i), randomNum * 10)))){
                randomNum = rand.nextFloat(0, 100) - corrector;
            }
            randomVectors.set(i, Vector3.subtract(randomVectors.get(i), Vector3.multiply(unitVectors.get(i), randomNum * 10)));
        }
        System.out.println("AFTER SHIFT");
        printRandomVectors();

    }

    public void createPlane() {
        for (int i = 0; i < 3; i++) {//Create 3 points that will create the plane
            Vector3 point = new Vector3(rand.nextFloat(0, upperBound - lowerBound) + lowerBound, rand.nextFloat(0, upperBound - lowerBound) + lowerBound, rand.nextFloat(0, upperBound - lowerBound) + lowerBound);
            if (!planeCreationPoints.contains(point)) {
                planeCreationPoints.add(point);
            }
        }
        //A B C

        printPlaneVectors();

        Vector3 AB = Vector3.subtract(planeCreationPoints.get(1), planeCreationPoints.get(0));
        Vector3 AC = Vector3.subtract(planeCreationPoints.get(2), planeCreationPoints.get(0));
        
        //Equations informations are set with various math operations
        planeEquationCoefficients = Vector3.cross(AB, AC);

        planeEquationEquality = -(Vector3.dot(planeEquationCoefficients, new Vector3(-planeCreationPoints.get(2).x, -planeCreationPoints.get(2).y, -planeCreationPoints.get(2).z)));//Denklemin diğer tarafı
    }
    //Prints.
    public void printPlaneVectors() {
        System.out.println("Plane Creation Vectors:");
        for (Vector3 p : planeCreationPoints) {
            System.out.println(p);
        }
        System.out.println("");
    }
    //Prints.
    public void printRandomVectors() {
        System.out.println("Random Vectors:");
        for (Vector3 p : randomVectors) {
            System.out.println(p);
        }
        System.out.println("");
    }
    //Checks if the vector is still in the room that we created with bounds.   
    public boolean checkBoundsForVector(Vector3 v){
        if(v.x > upperBound || v.x < lowerBound || v.y > upperBound || v.y < lowerBound || v.z > upperBound || v.z < lowerBound){
            return false;
        }
        return true;
    }

}
