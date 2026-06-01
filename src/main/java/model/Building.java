package model;
/**
 * Representa un edificio u obstáculo en el mundo.
 */
public class Building {
    private double x;
    private double z;
    private double width;
    private double height;

    public Building(double x, double z, double width, double height) {
        this.x = x;
        this.z = z;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Building{" +
                "x=" + x +
                ", z=" + z +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
