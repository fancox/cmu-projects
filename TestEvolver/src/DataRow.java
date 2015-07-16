/* Generated by Together */
import java.util.*;

/** Holds a single row of data, consisting of one or more X values and a single Y value.
* An ArrayList holds the (double) values of the independent variables ("X values")
* and a double holds the corresponding Y value. */
public class DataRow {
    private double yVal;
    private ArrayList<Double> xVals;

    DataRow() {
        xVals = new ArrayList<Double>();
    }
    public void setY(double y) {
        yVal = y;
    }
    /** Adds a new X value.  Be careful to add X values in the correct order
    * (i.e., X0 first, then X1, and so forth). */
    public void addX(double x) {
        xVals.add(new Double(x));
    }
    double getY() {
        return yVal;
    }
    ArrayList<Double> getXVals() {
        return xVals;
    }
}
