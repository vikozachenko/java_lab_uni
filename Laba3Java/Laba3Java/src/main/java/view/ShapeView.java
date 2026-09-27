package view;

import model.Circle;
import model.Shape;

public class ShapeView {

    public void displayShapes(Shape[] shapes) {
        System.out.println("=== Набір фігур ===");

        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    public void displayTotalArea(double area) {
        System.out.printf("%nСумарна площа всіх фігур: %.2f%n", area);
    }

    public void displayAreaByType(String type, double area) {
        System.out.printf(
                "Сумарна площа фігур типу %s: %.2f%n",
                type,
                area
        );
    }

    public void displaySortedByArea(Shape[] shapes) {
        System.out.println("\n=== Фігури, впорядковані за площею ===");

        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    public void displaySortedByColor(Shape[] shapes) {
        System.out.println("\n=== Фігури, впорядковані за кольором ===");

        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }
}