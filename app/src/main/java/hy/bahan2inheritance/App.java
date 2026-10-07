package hy.bahan2inheritance;

import hy.bahan2inheritance.testpackage.*;

class Parent {

    public int a = 10;

    public void printA() {
        System.out.println("Parent property: " + a);
    }
}

// child get everything the parent has
class Child extends Parent {

    public String b = "Child";

    public void printB() {
        System.out.println("Child property: " + b);
    }
}

public class App {

    public static void main(String[] args) {
        Child child = new Child();
        child.printA(); // Inherited method from Parent
        child.printB(); // Method defined in Child

        Parent parent = new Parent();
        parent.printA(); // Parent's own method
        // parent.printB(); // Parent can't access child

        TestClass test = new TestClass();
        test.publicValue = 1;
        // test.protectedValue = 2;
        // test.defaultAccessValue = 3;
        // test.privateValue = 4;

        // SampleAbstractClass sample = new SampleAbstractClass();
        SampleAbstractClass sub = new SampleSubClass();
    }
}

class SegiEmpat {

    private int panjang;
    private int lebar;

    public SegiEmpat(int panjang, int lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    public SegiEmpat(int sisi) {
        this.panjang = sisi;
        this.lebar = sisi;
    }

    public void gambar() {
        gambar("*");
    }

    public void gambar(String karakter) {
        for (int i = 0; i < panjang; i++) {
            for (int j = 0; j < lebar; j++) {
                System.out.print(karakter);
            }
            System.out.println();
        }
    }
}
