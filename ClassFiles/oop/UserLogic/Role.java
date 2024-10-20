package oop.UserLogic;

public abstract class Role {
    String name;

    public abstract String getName();

    public void Logout()
    {
        System.out.println("Bye bye");
    }
}