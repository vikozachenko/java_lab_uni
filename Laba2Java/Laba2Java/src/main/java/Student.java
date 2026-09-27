import java.time.LocalDate;

public class Student {

    private String surname;
    private String name;
    private LocalDate birthDate;
    private String phone;
    private String street;
    private String house;
    private String apartment;

    public Student(
            String surname,
            String name,
            LocalDate birthDate,
            String phone,
            String street,
            String house,
            String apartment
    ) {
        this.surname = surname;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getApartment() {
        return apartment;
    }

    @Override
    public String toString() {
        return "Прізвище: " + surname +
                "\nІм'я: " + name +
                "\nДата народження: " + birthDate +
                "\nТелефон: " + phone +
                "\nАдреса: вул. " + street +
                ", буд. " + house +
                ", кв. " + apartment;
    }
}