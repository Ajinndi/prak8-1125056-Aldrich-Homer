package hy.bahan2inheritance;

import hy.bahan2inheritance.testpackage.TestClass;

class OtherTester extends TestClass {

    public OtherTester() {
        TestClass sample = new TestClass();
        this.publicValue = 1;
        this.protectedValue = 2;
        // this.defaultAccessValue = 3;
        // this.privateValue = 4;

        sample.publicValue = 1;
        // sample.protectedValue = 2;
        // sample.defaultAccessValue = 3;
        // sample.privateValue = 4;
    }
}
