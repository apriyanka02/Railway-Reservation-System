package model;

public class Passenger {
    private String name;
    private int age;
    private Gender gender;
    private BerthPreference berthPreference;

    public Passenger(String name, int age, Gender gender,BerthPreference berthPreference) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
    }
    public Passenger(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BerthPreference getBerthPreference() {
        return berthPreference;
    }

    public void setBerthPreference(BerthPreference berthPreference) {
        this.berthPreference = berthPreference;
    }
}
