package org.example;

public class Vector3 {
    public static final Vector3 ZERO = new Vector3();
    public float x,y,z;

    public Vector3(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    
    public Vector3(float x, float y, float z){
        this.x = x;
        this.y = y; 
        this.z = z;
    }
    
    public static float dot(Vector3 a,Vector3 b){
        return  a.x * b.x + a.y * b.y + a.z * b.z;
    }
    
    public static Vector3 subtract(Vector3 a,Vector3 b){
        return new Vector3(a.x-b.x, a.y-b.y, a.z-b.z);
    }

    public static Vector3 multiply(Vector3 a,float b){
        return new Vector3(a.x*b, a.y*b, a.z*b);
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y + " z: " + z + "\n";
    }
    
    
}
