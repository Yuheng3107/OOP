package oop;

public abstract class Role {
    String name;

    public abstract String getName();

    public void Logout()
    {
        System.out.println("Bye bye");
    }
}