package org.example;

public class Vector3 {
    public final int ZERO = 0;
    public float x,y,z;

    public Vector3(){
        x = ZERO;
        y = ZERO;
        z = ZERO;
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
}
