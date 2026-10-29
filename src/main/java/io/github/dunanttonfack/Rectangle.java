package io.github.dunanttonfack;

public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (height + width);
    }

    public boolean isSquare() {
        return width == height;
    }

    @Override
    public String toString() {
        return "Width: " + width + " | Height: " + height;
    }
}
