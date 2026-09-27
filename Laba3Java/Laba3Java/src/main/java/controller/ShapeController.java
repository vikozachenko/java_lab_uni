package controller;

import model.Circle;
import model.Rectangle;
import model.Shape;
import model.Triangle;

import java.util.Arrays;
import java.util.Comparator;

public class ShapeController {

    private Shape[] shapes;

    public ShapeController() {
        shapes = new Shape[]{
                new Rectangle("Red", 5, 4),
                new Circle("Blue", 3),
                new Triangle("Green", 6, 4),
                new Rectangle("Yellow", 7, 3),
                new Circle("Red", 5),
                new Triangle("Blue", 8, 5),
                new Rectangle("Green", 6, 8),
                new Circle("Yellow", 4),
                new Triangle("Red", 10, 3),
                new Rectangle("Blue", 4, 9)
        };
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public void displayShapes() {
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    public double calculateTotalArea() {
        double totalArea = 0;

        for (Shape shape : shapes) {
            totalArea += shape.calcArea();
        }

        return totalArea;
    }

    public double calculateAreaByType(Class<?> shapeType) {
        double totalArea = 0;

        for (Shape shape : shapes) {
            if (shapeType.isInstance(shape)) {
                totalArea += shape.calcArea();
            }
        }

        return totalArea;
    }

    public void sortByArea() {
        Arrays.sort(shapes, Comparator.comparingDouble(Shape::calcArea));
    }

    public void sortByColor() {
        Arrays.sort(shapes, Comparator.comparing(Shape::getShapeColor));
    }
}