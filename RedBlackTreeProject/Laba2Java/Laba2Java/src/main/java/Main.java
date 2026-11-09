import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {

        // a. Створення екземпляра Person
        Person person = new Person("Козаченко", "Вікторія", 19);

        System.out.println("Початковий об'єкт:");
        System.out.println(person);

        // b. Конвертація об'єкта в JSON
        Gson gson = new Gson();
        String json = gson.toJson(person);

        System.out.println("\nОб'єкт у форматі JSON:");
        System.out.println(json);

        // c. Конвертація JSON назад в об'єкт
        Person restoredPerson = gson.fromJson(json, Person.class);

        System.out.println("\nВідновлений об'єкт:");
        System.out.println(restoredPerson);

        // d. Перевірка equals
        System.out.println("\nРезультат перевірки equals:");
        System.out.println("Об'єкти однакові: " + person.equals(restoredPerson));
    }
}