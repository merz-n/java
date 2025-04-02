public class Employee {
    private String nameFull;
    private String position;
    private String email;
    private String phone;
    private int age;


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
