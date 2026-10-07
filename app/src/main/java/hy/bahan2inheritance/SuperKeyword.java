package hy.bahan2inheritance;

class Parent {

    private int parentValue;

    public Parent(int parentValue) {
        this.parentValue = parentValue;
    }

    public void display() {
        System.out.println("Parent Value: " + this.parentValue);
    }
}

class Child extends Parent {

    private String childValue;

    public Child(int parentValue, String childValue) {
        // calling the parent constructor
        super(parentValue);
        this.childValue = childValue;
    }

    @Override
    public void display() {
        // calling the parent's method
        super.display();
        System.out.println("Child is calling parent method " + childValue);
    }
}

public class SuperKeyword {}
