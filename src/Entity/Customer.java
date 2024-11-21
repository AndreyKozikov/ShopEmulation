package Entity;


public class Customer {
    private String fullName;
    private int age;
    private String phone;
    private Gender gender;

    public Customer(String fullName, int age, String phone, Gender gender) {
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // Перечисление для гендеров
    public enum Gender {
        MALE("Мужчина"),
        FEMALE("Женщина");

        private final String description;

        Gender(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}

