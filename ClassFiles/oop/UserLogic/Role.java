package oop.UserLogic;
import oop.Gender;
public abstract class Role {
    public String name;
    public Gender gender;

    public String getName() {
        return name;
    }

    public Role(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getGender() {
        return gender.toString();
    }

    public void Logout()
    {
        System.out.println("Bye bye");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}