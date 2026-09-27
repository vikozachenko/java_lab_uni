import controller.ShapeController;
import model.Circle;
import model.Shape;
import view.ShapeView;

public class Main {

    public static void main(String[] args) {

        ShapeController controller = new ShapeController();
        ShapeView view = new ShapeView();

        // Відображення початкового набору даних
        view.displayShapes(controller.getShapes());

        // Побудова фігур
        System.out.println("\n=== Побудова фігур ===");
        for (Shape shape : controller.getShapes()) {
            shape.draw();
        }

        // Сумарна площа всіх фігур
        double totalArea = controller.calculateTotalArea();
        view.displayTotalArea(totalArea);

        // Сумарна площа фігур заданого виду
        double circleArea = controller.calculateAreaByType(Circle.class);
        view.displayAreaByType("Circle", circleArea);

        // Сортування за площею
        controller.sortByArea();
        view.displaySortedByArea(controller.getShapes());

        // Сортування за кольором
        controller.sortByColor();
        view.displaySortedByColor(controller.getShapes());
    }
}