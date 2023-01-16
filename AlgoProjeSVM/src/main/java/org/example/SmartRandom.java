package org.example;

import javax.swing.plaf.synth.Region;
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

    Random rand = new Random();

    private List<Vector3> randomVectors = new ArrayList<>();//Random vectors that are on the plane and then shifted randomly

    private List<DataEntry> rndDataSet = new ArrayList<>();
    private List<Vector3> planeCreationPoints = new ArrayList<>();//Plane is created by these points
    private List<Vector3> unitVectors = new ArrayList<>();//Unit vectors after various math operations

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

        //Used for progress not for logic
        float stepAmount = 1f / amount;
        float totalStep = 0;

        if (weightedZoneLower < lowerBound || weightedZoneUpper > upperBound || weightedZoneUpper < weightedZoneLower) {//Check if the parameters are fine
            System.out.println("Incorrect weighted limits.");
            return;
        }

        System.out.println("Generating "+amount+" points for plane:");
        printPlaneEquation();

        while (counter < amount) {
            int weight = rand.nextInt(0, 100);
            if (weight <= CHANCE) {//Point is created in the weighted zone or not. Chance decides that
                if (weightedZoneLower < 0) {//For the sake of rand.nextFloat() we have to check the negativity like this
                    randomX = rand.nextFloat(0, weightedZoneUpper - weightedZoneLower) + weightedZoneLower;
                    randomY = rand.nextFloat(0, weightedZoneUpper - weightedZoneLower) + weightedZoneLower;
                } else {
                    randomX = rand.nextFloat(weightedZoneLower, weightedZoneUpper);
                    randomY = rand.nextFloat(weightedZoneLower, weightedZoneUpper);
                }
            } else {
                randomX = rand.nextFloat(0, upperBound - lowerBound) + lowerBound;
                randomY = rand.nextFloat(0, upperBound - lowerBound) + lowerBound;
            }
            randomZ = (planeEquationEquality - (planeEquationCoefficients.x * randomX + planeEquationCoefficients.y * randomY)) / planeEquationCoefficients.z;

            if (!randomVectors.contains(new Vector3(randomX, randomY, randomZ)) && !planeCreationPoints.contains(new Vector3(randomX, randomY, randomZ))) {//OBJEYE BAKIYOMUŞ BU YAA VECTORE EŞİTLİK FONKU YAZ CHECK İÇİN
                if (checkBoundsForVector(new Vector3(randomX, randomY, randomZ))) {
                    randomVectors.add(new Vector3(randomX, randomY, randomZ));//Point is added to randomVectors after various checks
                    counter++;

                    //USED FOR PRINTING PROGRESS
                    totalStep += stepAmount;
                    while (totalStep > 0.1f){
                        totalStep -= 0.1f;
                        System.out.print("#");
                    }
                    if(counter == amount) System.out.println("");
                }
            }
        }
        
        float unit = Vector3.magnitude(planeEquationCoefficients);//Calculating its unit magnitude

        //printRandomVectors();

        for (Vector3 v : randomVectors) {
            unitVectors.add(Vector3.multiply(v, 1 / unit));//Calculating unit vectors of each vector
        }
        float randomNum = 0;
        for (int i = 0; i < randomVectors.size(); i++) {

            randomNum = rand.nextFloat(0, 100) - corrector;//Shifting every point so that they do not stay in the plane
            while (!checkBoundsForVector(Vector3.subtract(randomVectors.get(i), Vector3.multiply(unitVectors.get(i), randomNum * 40)))) {
                randomNum = rand.nextFloat(0, 100) - corrector;
            }
            var vec = Vector3.subtract(randomVectors.get(i), Vector3.multiply(unitVectors.get(i), randomNum * 40));
            randomVectors.set(i, vec);
            var entry = new DataEntry(vec, -Math.signum(randomNum));
            rndDataSet.add(entry);
        }
    }
    //This code creates a plane using 3 randomly generated points and sets the coefficients and equality for the plane equation using vector math operations. 
    //The setPlane methods allow for manual input of coefficients and equality.
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

    public void setPlane(float x,float y,float z,float constant){
        planeEquationCoefficients = new Vector3(x,y,z);
        planeEquationEquality = constant;
    }
    // This method sets the coefficients and constant for the plane equation in the form of ax + by + cz = d, 
    //where planeEquationCoefficients = <x,y,z> and planeEquationEquality = d.

    public void setPlane(Vector3 vec,float constant){
        planeEquationCoefficients = vec;
        planeEquationEquality = constant;
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
    public boolean checkBoundsForVector(Vector3 v) {
        if (v.x > upperBound || v.x < lowerBound || v.y > upperBound || v.y < lowerBound || v.z > upperBound || v.z < lowerBound) {
            return false;
        }
        return true;
    }
    // This method prints the equation of a plane in the form of "ax + by + cz + d = 0" using the coefficients stored in the planeEquationCoefficients object 
    //and the constant stored in planeEquationEquality.

    public void printPlaneEquation() {
        System.out.println(planeEquationCoefficients.x + "x +" + planeEquationCoefficients.y + "y + " + planeEquationCoefficients.z + "z + " + -planeEquationEquality + " = 0");
    }
    // This method returns a list of DataEntry objects, representing the current dataset being used.
    public List<DataEntry> getDataSet() {
        return rndDataSet;
    }
}
