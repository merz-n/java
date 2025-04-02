public class Employee {
    private final String nameFull;
    private final String position;
    private final String email;
    private final String phone;
    private final int age;


    public Employee(String nameFull, String position, String email, String phone, int age) {
        this.nameFull = nameFull;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }
    public String toString() {
        return String.format("Employee{fullName='%s', position='%s', email='%s', phone='%s', age=%d}",
                nameFull, position, email, phone, age);
    }
}
