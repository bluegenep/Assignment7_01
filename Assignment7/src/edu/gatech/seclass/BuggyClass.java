package edu.gatech.seclass;

/**
 * Created by Prakash on 10/31/16.
 */
public class BuggyClass {
    int result;

    /*
    buggyMethod1 that contains a division by zero fault such that
    (1) it is possible to create a test suite that achieves 100% statement coverage and does not reveal the fault, and
    (2) it is possible to create a test suite that achieves less than 50% statement coverage and reveals the fault.
    */

    public int buggyMethod1(int number) {
        if (number > 1) {
            number = (number - 2);
        }
        else if (number < 1){
            number = (number + 2);
        }
        else{
            number = number +1;
        }
        return 10/number;

    }

    /*
    buggyMethod2 that contains a division by zero fault such that
    (1) it is possible to create a test suite that achieves 100% statement coverage and does not reveal the fault, and
    (2) every test suite that achieves more than 50% branch coverage reveals the fault.

    */

    public int buggyMethod2(int number) {
        int x = 0;
        if( number >= 0){
            x = 5;
        }
        return number/x;
    }

    /*
    buggyMethod3 that contains a division by zero fault such that
    (1) it is possible to create a test suite that achieves 100% branch coverage and does not reveal the fault, and
    (2) it is possible to create a test suite that achieves 100% statement coverage, does not achieve 100% branch coverage, and reveals the fault.
    */

    public int buggyMethod3(int number) {
        if (number > 1) {
            result = 10 / (number - 2);
        } else if (number < 1) {
            result = 10 / (number + 2);
        }
        return result;
    }

    /*
    Method that contains a division by zero fault such that
    (1) every test suite that achieves 100% statement coverage reveals the fault, and
    (2) it is possible to create a test suite that achieves 100% branch coverage and does not reveal the fault.
    */

    public void buggyMethod4() {
        //empty Method.
        /*

        Reason - A 100% branch coverage will cover all the statement coverage. Hence if every test suite that
        achives 100% statement coverage reveals the fault, 100% branch coverage should reveal the fault as well.

        */
    }

    /*
    buggyMethod5 by completing the code skeleton provided below so that
    (1) it is possible to create a test suite that achieves 100% statement coverage, and
    (2) the division by zero fault at line 4 cannot be revealed by any test suite.
    */

    public void buggyMethod5() {
        //empty Method.
        /*
        Reason - A 100% statement coverage will execute all the lines of code in a method including the line - "x = i/10".
        If it does not touch a single line of code its not 100% statement coverage. Hence 100% statement will execute the
        line "x = i/10" and reveal the division by 0 fault.

        */
    }

}
