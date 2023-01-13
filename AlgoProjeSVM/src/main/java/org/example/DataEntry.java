package org.example;

public class DataEntry {

    public Vector3 properties;
    public float classification;

    public DataEntry(Vector3 properties, float classification) {
        this.properties = properties;
        this.classification = classification;
    }
    public DataEntry(float x,float y,float z, float classification) {
        this.properties = new Vector3(x,y,z);
        this.classification = classification;
    }

    @Override
    public String toString() {
        return  (Math.signum(classification) > 0 ? "[+1] " : "[-1] ") + properties.toString();
    }
}
