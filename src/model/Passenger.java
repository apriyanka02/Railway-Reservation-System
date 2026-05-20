package model;

public class Passenger {
    private String name;
    private int age;
    private Gender gender;
    private BerthPreference berthPreference;

    public Passenger(String ticketId, String name, int age, Gender gender, boolean berthPrefered) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
    }

    public Passenger(String name, int age, Gender gender, BerthPreference berthPrefered) {

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
