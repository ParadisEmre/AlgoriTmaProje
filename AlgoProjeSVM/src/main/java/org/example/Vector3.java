package org.example;

public class Vector3 {

    public static final Vector3 ZERO = new Vector3();
    public float x, y, z;

    public Vector3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    // Vector3 methods
    public static Vector3 cross(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);
    }
    public static float dot(Vector3 v1, Vector3 v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }
    public static Vector3 subtract(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }
    public static Vector3 multiply(Vector3 v, float k) {
        return new Vector3(v.x * k, v.y * k, v.z * k);
    }
    public static float magnitude(Vector3 v){
        return (float) Math.sqrt(dot(v, v));
    }
    //function overriding
    @Override
    public boolean equals(Object o) {//To override the contains method of java
        if (o instanceof Vector3 vector) {
            return vector.x == this.x && vector.y == this.y && vector.z == this.z;
        }
        return false;
    }
    //function overriding
    @Override
    public String toString() {
        return String.format("X: %6.2f Y: %6.2f Z : %6.2f",x,y,z);
    }
}
