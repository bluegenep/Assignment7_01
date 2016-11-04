package edu.gatech.seclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BuggyClassTestBC2 {


    // Test suites that achieve more than 50% branch coverage of buggyMethod2 and reveal the fault therein.

    // if n < 0
    @Test
    public void testBuggyMethod2() {
        BuggyClass tester = new BuggyClass();
        tester.buggyMethod2(-5);
    }

    //if n >=0
    @Test
    public void testBuggymethod1() {
        BuggyClass tester = new BuggyClass();
        assertEquals(2,tester.buggyMethod2(10));

    }

}
