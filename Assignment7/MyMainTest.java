package edu.gatech.seclass.replace;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by Prakash on 11/9/16.
 */
public class MyMainTest {


    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;



    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // creating TemporaryFolder Rule that allows creation of files and folders
    // that should be deleted when the test method finishes (whether it passes or fails).

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    // Utilities Class

    // Method to create a file

    public File newFile() throws IOException {
        File newFile = folder.newFile();
        newFile.deleteOnExit();
        return newFile;
    }

    //Creating file 1

    public File createFileA() throws Exception {
        File fileA = newFile();
        FileWriter writeOnFile = new FileWriter(fileA);

        writeOnFile.write("Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!");

        writeOnFile.close();
        return fileA;
    }

    //Creating file 2

    public File createFileB() throws Exception {
        File fileB = newFile();
        FileWriter writeOnFile = new FileWriter(fileB);

        writeOnFile.write("\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!");

        writeOnFile.close();
        return fileB;
    }

    //Creating file 3

    public File createFileC() throws Exception {
        File fileC = newFile();
        FileWriter writeOnFile = new FileWriter(fileC);

        writeOnFile.write("\"Test File:3\"\n" +
                "This is a test file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!");

        writeOnFile.close();
        return fileC;
    }

    //Creating file 4
    public File createFileD() throws Exception {
        File fileD = newFile();
        FileWriter writeOnFile = new FileWriter(fileD);

        writeOnFile.write("This is a very simple file that composes of special characters !!! @@. let's do it ");

        writeOnFile.close();
        return fileD;
    }

    //Creating file 5
    public File createFileE() throws Exception {
        File fileE = newFile();
        FileWriter writeOnFile = new FileWriter(fileE);

        writeOnFile.write("\"Test File:4\"\n" +
                "This is a test file 4 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!");

        writeOnFile.close();
        return fileE;
    }

    //Creating file 6
    public File createFileEmpty() throws Exception {
        File fileEmpty = newFile();
        FileWriter writeOnFile = new FileWriter(fileEmpty);

        writeOnFile.write("");

        writeOnFile.close();
        return fileEmpty;
    }


    // Getting content of the file
    private String getFileContent(String file) {
        String contentOfFile = null;
        try {
            contentOfFile = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentOfFile;
    }


    //------------------------------
    // Category Partition Test Cases
    //------------------------------

    @Test
    public void myMainTest1() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one file with one replace combination(-i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here all
        // the matching strings(case insensitive) are replaced.
        //TestCase Number : 12

        /*Test Case 12 		(Key = 2.2.2.4.1.4.1.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters*/

        File inputFile1 = createFileA();
        String args[] = {"-i", "is", "are", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Testing File :\n" +
                "Replace utility are used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "thare file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" are awesome!!";

        Main testClass = new Main();
        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + "_backUP")));
    }


    @Test
    public void myMainTest2() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with one replace combination(-l) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the last occurrence of string.
        //TestCase Number : 13

        /*

        Test Case 13 		(Key = 2.2.2.4.1.4.2.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        String args[] = {"-l", "is", "!@#$", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" !@#$ awesome!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!",  expected, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + "_backUP")));
    }


    @Test
    public void myMainTest3() throws Exception, IOException {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with one replace combination(-f) where
        //FromStringContent = special characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first occurrence of string
        //TestCase Number : 15

        /*


        Test Case 15 		(Key = 2.2.2.4.2.4.1.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters

        */

        File inputFile1 = createFileA();
        String args[] = {"-f", "!!", "OOOO", "--", inputFile1.getPath()};
        Main.main(args);

/*
        writeOnFile.write("Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!");*/
        String expected = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test itOOOO!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        //Main testClass = new Main();
        //assertEquals("The files match!", Main.main(args), expected);

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!",  expected, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + "_backUP")));
    }


    @Test
    public void myMainTest4() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with one replace combination(-b) where
        //FromStringContent = special characters(>1) and toStringContent = special characters(>1). Here it creates a
        //backup of a file and does not replace anything.
        //TestCase Number : 16

        /*

        Test Case 16 		(Key = 2.2.2.4.2.4.2.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        String args[] = {"-b", "!!!", "!@#$", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        //assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest5() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with one replace combination(-i) where
        //FromStringContent = special characters(>1) and toStringContent = spaces(>1). Here it replaces all occurrence of
        //special characters with no case sensitivity.
        //TestCase Number : 17

        /*

        Test Case 17 		(Key = 2.2.2.4.2.4.3.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  spaces


        */
        File inputFile1 = createFileD();
        String args[] = {"-i", "@@", " .", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "This is a very simple file that composes of special characters !!!  .. let's do it ";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest6() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with two replace combination(-b -f) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here it backs up
        // the file and replaces the first occurrence of string
        //TestCase Number : 21

        /*

        Test Case 21 		(Key = 2.2.3.4.1.4.1.)
        Presence of a file(s) where replace functionality is applied :  One file
         Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters

        */
        File inputFile1 = createFileD();
        String args[] = {"-b", "-f", "is", "AA", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "ThAA is a very simple file that composes of special characters !!! @@. let's do it ";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest7() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with two replace combination(-f, -l) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it
        // replaces the first occurrence and last occurrence of string
        //TestCase Number : 22

        /*

        Test Case 22 		(Key = 2.2.3.4.1.4.2.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters
        */
        File inputFile1 = createFileD();
        String args[] = {"-f", "-l", "is", "AA", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "ThAA AA a very simple file that composes of special characters !!! @@. let's do it ";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest8() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with two replace combination(-b ,-i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = spaces(>1). Here it replaces
        //the all occurrences of string (case insensitive) and creates a backup of the file
        //TestCase Number : 23

        /*

        Test Case 23 		(Key = 2.2.3.4.1.4.3.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  spaces
        */
        File inputFile1 = createFileD();
        String args[] = {"-b", "-i", "!!", "a sa", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "This is a very simple file that composes of special characters a sa! @@. let's do it ";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest9() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with replace combinations(-f and -l) where
        //FromStringContent = special characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first and last occurrence of string
        //TestCase Number : 24

        /*

        Test Case 24 		(Key = 2.2.3.4.2.4.1.)
   Presence of a file(s) where replace functionality is applied :  One file
   Content of replace file                                      :  Non-empty
   Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
   FromStringLength                                             :  >1
   FromStringContent                                            :  special characters
   ToStringLength                                               :  >1
   ToStringContent                                              :  alphanumeric characters                                        :  special characters

        */
        File inputFile1 = createFileA();
        String args[] = {"-f", "-l", "!!", "as", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test itas!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesomeas";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest10() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with three replace combination(-b, -f, -l) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first and last occurrence of string and creates a backup of the file
        //TestCase Number : 30

        /*

        Test Case 30 		(Key = 2.2.4.4.1.4.1.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters


        */
        File inputFile1 = createFileA();
        String args[] = {"-b", "-f", "-l", "es", "ES", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "TESting File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awESome!!";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest11() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with three replace combination(-b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the first occurrence, all occurrence(case insensitive)  of string
        //TestCase Number : 31

        /*

        Test Case 31 		(Key = 2.2.4.4.1.4.2.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        String args[] = {"-b", "-f", "-i", "rep", "!@", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Testing File :\n" +
                "!@lace utility is used to test a file with !@lace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"!@lace Utility\" is awesome!!";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest12() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with three replace combination(-f, -l, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = spaces(>1). Here it replaces
        //the first, last occurrence of string and all occurrence (case insensitive).
        //TestCase Number : 32

        /*

        Test Case 32 		(Key = 2.2.4.4.1.4.3.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  spaces

        */
        File inputFile1 = createFileA();
        String args[] = {"-f", "-l", "-i", "Rep", "  s", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Testing File :\n" +
                "  slace utility is used to test a file with   slace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"  slace Utility\" is awesome!!";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest13() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with three replace combination(-l, -i, -b) where
        //FromStringContent = special characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the last occurrence of string, all occurrence of string (case insensitive) and creates a back up of the file
        //TestCase Number : 34

        /*

       Test Case 34 		(Key = 2.2.4.4.2.4.2.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        String args[] = {"-l", "-i", "-b", "!!", "@@", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it@@!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome@@";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest14() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with three replace combination(-l, -i, -f)) where
        //FromStringContent = spaces (>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first and last occurrence of string, all occurrence with no case insensitivity.
        //TestCase Number : 36

        /*

        Test Case 36 		(Key = 2.2.4.4.3.4.1.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  spaces
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters

        */
        File inputFile1 = createFileA();
        String args[] = {"-l", "-i", "-f", "s ", "@@@", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Testing File :\n" +
                "Replace utility i@@@used to test a file with replace options\n" +
                "now let'@@@test it!!!\n" +
                "thi@@@file ha@@@a bunch of codes...\n" +
                "We all know that \"Replace Utility\" i@@@awesome!!";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest15() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first, last occurrence of string and all occurrence (case insensitive) and creates a back up of the file as well.
        //TestCase Number : 39

        /*

        Test Case 39 		(Key = 2.2.5.4.1.4.1.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Four replace option combination (b & f & l & i )
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters
        */

        File inputFile1 = createFileA();
        String args[] = {"-l", "-b", "-f", "-i", "File", "drive", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Testing drive :\n" +
                "Replace utility is used to test a drive with replace options\n" +
                "now let's test it!!!\n" +
                "this drive has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest16() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the firs, last occurrence of string and all occurrence (case insensitive) and creates a back up of the file as well.
        //TestCase Number : 40

        /*

       Test Case 40 		(Key = 2.2.5.4.1.4.2.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Four replace option combination (b & f & l & i )
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters
        */
        File inputFile1 = createFileA();
        String args[] = {"-l", "-b", "-f", "-i", "File", "@@@/", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Testing @@@/ :\n" +
                "Replace utility is used to test a @@@/ with replace options\n" +
                "now let's test it!!!\n" +
                "this @@@/ has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    @Test
    public void myMainTest17() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on one non empty file with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = special characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the firs, last occurrence of string and all occurrence (case insensitive) and creates a back up of the file as well.
        //TestCase Number : 43

        /*

        Test Case 43 		(Key = 2.2.5.4.2.4.2.)
        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Four replace option combination (b & f & l & i )
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        String args[] = {"-l", "-b", "-f", "-i", "!!", " @##@ ", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it @##@ !\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome @##@ ";

        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //--------------------------------------
    // End of Category Partition Test Cases
    //--------------------------------------


    //---------------------------------
    // Beginning of Test for two  files
    //---------------------------------


    @Test
    public void myMainTest18() throws Exception {


        //Purpose of testcase: testing replace functionality on two files with one replace combination(-i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here all
        // the matching strings(case insensitive) are replaced.


        /*
        Presence of a file(s) where replace functionality is applied :  Two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters*/

        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-i", "is", "are", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility are used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "thare file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" are awesome!!";

        String expected2 = "\"Test File:2\"\n" +
                "Thare are a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always thare are going to be interesting and we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

    }


    @Test
    public void myMainTest19() throws Exception {


        //Purpose of testcase: testing replace functionality on two non empty file with one replace combination(-l) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the last occurrence of string

        /*


        Presence of a file(s) where replace functionality is applied :  Two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-l", "is", "!@#$", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" !@#$ awesome!!";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this !@#$ going to be interesting and we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest20() throws Exception {


        //Purpose of testcase: testing replace functionality on two non empty file with one replace combination(-f) where
        //FromStringContent = special characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first occurrence of string


        /*



        Presence of a file(s) where replace functionality is applied :  Two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters

        */

        File inputFile1 = createFileA();
        File inputFile2 = createFileB();

        String args[] = {"-f", "!!", "OOOO", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test itOOOO!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";
        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utilityOOOO\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest21() throws Exception {


        //Purpose of testcase: testing replace functionality on two non empty files with one replace combination(-b) where
        //FromStringContent = special characters(>1) and toStringContent = special characters(>1). Here it creates a
        //backup of a file and does not replace anything.


        /*
        Presence of a file(s) where replace functionality is applied :  Two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-b", "!!!", "!@#$", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest22() throws Exception {

        //Purpose of testcase: testing replace functionality on two non empty files with one replace combination(-i) where
        //FromStringContent = special characters(>1) and toStringContent = spaces(>1). Here it replaces all occurrence of
        //special characters with no case sensitivity.


        /*
        Presence of a file(s) where replace functionality is applied :  Twe file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  spaces


        */
        File inputFile1 = createFileD();
        File inputFile2 = createFileB();
        String args[] = {"-i", "@@", " .", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "This is a very simple file that composes of special characters !!!  .. let's do it ";
        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!!  . xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest23() throws Exception {

        //Purpose of testcase: testing replace functionality on two non empty files with two replace combination(-b -f) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here it backs up
        // the file and replaces the first occurrence of string

        /*

        Presence of a file(s) where replace functionality is applied :  Two file
         Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters

        */
        File inputFile1 = createFileD();
        File inputFile2 = createFileB();
        String args[] = {"-b", "-f", "is", "AA", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "ThAA is a very simple file that composes of special characters !!! @@. let's do it ";

        String expected2 = "\"Test File:2\"\n" +
                "ThAA is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest24() throws Exception {


        //Purpose of testcase: testing replace functionality on two non empty files with two replace combination(-f, -l) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it
        // replaces the first occurrence and last occurrence of string

        /*
        Presence of a file(s) where replace functionality is applied :  Two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters
        */
        File inputFile1 = createFileD();
        File inputFile2 = createFileB();
        String args[] = {"-f", "-l", "is", "AA", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "ThAA AA a very simple file that composes of special characters !!! @@. let's do it ";

        String expected2 = "\"Test File:2\"\n" +
                "ThAA is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this AA going to be interesting and we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest25() throws Exception {

        //Purpose of testcase: testing replace functionality on one non empty file with two replace combination(-b ,-i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = spaces(>1). Here it replaces
        //the all occurrences of string (case insensitive) and creates a backup of the file

        /*

        Presence of a file(s) where replace functionality is applied :  Two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  spaces
        */
        File inputFile1 = createFileD();
        File inputFile2 = createFileB();
        String args[] = {"-b", "-i", "!!", "a sa", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "This is a very simple file that composes of special characters a sa! @@. let's do it ";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utilitya sa\n" +
                "The replace can happen in multiple filesa sa @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know ita sa";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest26() throws Exception {

        //Purpose of testcase: testing replace functionality on two non empty files with replace combinations(-f and -l) where
        //FromStringContent = special characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first and last occurrence of string

        /*


   Presence of a file(s) where replace functionality is applied :  Two file
   Content of replace file                                      :  Non-empty
   Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
   FromStringLength                                             :  >1
   FromStringContent                                            :  special characters
   ToStringLength                                               :  >1
   ToStringContent                                              :  alphanumeric characters                                        :  special characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-f", "-l", "!!", "as", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test itas!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesomeas";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utilityas\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know itas";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest27() throws Exception {

        //Purpose of testcase: testing replace functionality on two non empty files with three replace combination(-b, -f, -l) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first and last occurrence of string and creates a backup of the file

        /*

        Presence of a file(s) where replace functionality is applied :  Two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters


        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-b", "-f", "-l", "es", "ES", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "TESting File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awESome!!";

        String expected2 = "\"TESt File:2\"\n" +
                "This is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interESting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest28() throws Exception {

        //Purpose of testcase: testing replace functionality on one non empty file with three replace combination(-b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the first occurrence, all occurrence(case insensitive)  of string


        /*


        Presence of a file(s) where replace functionality is applied :  Two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-b", "-f", "-i", "rep", "!@", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "!@lace utility is used to test a file with !@lace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"!@lace Utility\" is awesome!!";


        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the !@lace utility!!\n" +
                "The !@lace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest29() throws Exception {

        //Purpose of testcase: testing replace functionality on two non empty files with three replace combination(-f, -l, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = spaces(>1). Here it replaces
        //the first, last occurrence of string and all occurrence (case insensitive).

        /*

        Presence of a file(s) where replace functionality is applied :  One file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  spaces

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-f", "-l", "-i", "Rep", "  s", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "  slace utility is used to test a file with   slace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"  slace Utility\" is awesome!!";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the   slace utility!!\n" +
                "The   slace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest30() throws Exception {

        //Purpose of testcase: testing replace functionality on two non empty files with three replace combination(-l, -i, -b) where
        //FromStringContent = special characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the last occurrence of string, all occurrence of string (case insensitive) and creates a back up of the file


        /*

        Presence of a file(s) where replace functionality is applied :  Two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-l", "-i", "-b", "!!", "@@", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it@@!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome@@";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility@@\n" +
                "The replace can happen in multiple files@@ @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it@@";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest31() throws Exception {

        //Purpose of testcase: testing replace functionality on two non empty files with three replace combination(-l, -i, -f)) where
        //FromStringContent = spaces (>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first and last occurrence of string, all occurrence with no case insensitivity.


        /*

        Presence of a file(s) where replace functionality is applied :  Two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  spaces
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-l", "-i", "-f", "s ", "@@@", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility i@@@used to test a file with replace options\n" +
                "now let'@@@test it!!!\n" +
                "thi@@@file ha@@@a bunch of codes...\n" +
                "We all know that \"Replace Utility\" i@@@awesome!!";

        String expected2 = "\"Test File:2\"\n" +
                "Thi@@@i@@@a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let'@@@see how thing@@@change@@@here.... \n" +
                "A@@@alway@@@thi@@@i@@@going to be interesting and we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest32() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on two non empty files with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first, last occurrence of string and all occurrence (case insensitive) and creates a back up of the file as well.

        /*

        Presence of a file(s) where replace functionality is applied :  Two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Four replace option combination (b & f & l & i )
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters
        */

        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-l", "-b", "-f", "-i", "File", "drive", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing drive :\n" +
                "Replace utility is used to test a drive with replace options\n" +
                "now let's test it!!!\n" +
                "this drive has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String expected2 = "\"Test drive:2\"\n" +
                "This is a test drive 2 for the replace utility!!\n" +
                "The replace can happen in multiple drives!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest33() throws Exception {

        //Purpose of testcase: testing replace functionality on one non empty file with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the firs, last occurrence of string and all occurrence (case insensitive) and creates a back up of the file as well.

        /*

        Presence of a file(s) where replace functionality is applied :  two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Four replace option combination (b & f & l & i )
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters
        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-l", "-b", "-f", "-i", "File", "@@@/", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing @@@/ :\n" +
                "Replace utility is used to test a @@@/ with replace options\n" +
                "now let's test it!!!\n" +
                "this @@@/ has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String expected2 = "\"Test @@@/:2\"\n" +
                "This is a test @@@/ 2 for the replace utility!!\n" +
                "The replace can happen in multiple @@@/s!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }


    @Test
    public void myMainTest34() throws Exception {

        // TestCases for Category Partition
        //Purpose of testcase: testing replace functionality on two non empty files with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = special characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the firs, last occurrence of string and all occurrence (case insensitive) and creates a back up of the file as well.


        /*

        Presence of a file(s) where replace functionality is applied :  two file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Four replace option combination (b & f & l & i )
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        String args[] = {"-l", "-b", "-f", "-i", "!!", " @##@ ", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it @##@ !\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome @##@ ";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility @##@ \n" +
                "The replace can happen in multiple files @##@  @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it @##@ ";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

    }


    //-------------------------------------
    // This is tests for three or four files
    //-------------------------------


    @Test
    public void myMainTest35() throws Exception {


        //Purpose of testcase: testing replace functionality on three files with one replace combination(-i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here all
        // the matching strings are replaced with case insensitive.


        /*
        Presence of a file(s) where replace functionality is applied :  Three file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters*/

        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        String args[] = {"-i", "is", "are", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility are used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "thare file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" are awesome!!";

        String expected2 = "\"Test File:2\"\n" +
                "Thare are a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always thare are going to be interesting and we know it!!";

        String expected3 = "\"Test File:3\"\n" +
                "Thare are a test file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always thare are going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }


    @Test
    public void myMainTest36() throws Exception {


        //Purpose of testcase: testing replace functionality on three non empty file with one replace combination(-l) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the last occurrence of string

        /*


        Presence of a file(s) where replace functionality is applied :  Three file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        String args[] = {"-l", "is", "!@#$", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" !@#$ awesome!!";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this !@#$ going to be interesting and we know it!!";

        String expected3 = "\"Test File:3\"\n" +
                "This is a test file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this !@#$ going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }


    @Test
    public void myMainTest37() throws Exception {


        //Purpose of testcase: testing replace functionality on three non empty file with one replace combination(-f) where
        //FromStringContent = special characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first occurrence of string


        /*
        Presence of a file(s) where replace functionality is applied :  Three file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters

        */

        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();

        String args[] = {"-f", "!!", "OOOO", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test itOOOO!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";
        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utilityOOOO\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";
        String expected3 = "\"Test File:3\"\n" +
                "This is a test file 3 for the replace utilityOOOO\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }


    @Test
    public void myMainTest38() throws Exception {

        //Purpose of testcase: testing replace functionality on three non empty files with one replace combination(-b) where
        //FromStringContent = special characters(>1) and toStringContent = special characters(>1). Here it creates a
        //backup of a file and does not replace anything.

        /*


        Presence of a file(s) where replace functionality is applied :  Three file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        String args[] = {"-b", "!!!", "!@#$", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";
        String expected3 = "\"Test File:3\"\n" +
                "This is a test file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }


    @Test
    public void myMainTest39() throws Exception {


        //Purpose of testcase: testing replace functionality on three non empty files with one replace combination(-i) where
        //FromStringContent = special characters(>1) and toStringContent = spaces(>1). Here it replaces all occurrence of
        //special characters with no case sensitivity.

        /*


        Presence of a file(s) where replace functionality is applied :  Three file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  One replace option combination(b, f , l , i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  spaces


        */
        File inputFile1 = createFileD();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        String args[] = {"-i", "@@", " .", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "This is a very simple file that composes of special characters !!!  .. let's do it ";
        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!!  . xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";
        String expected3 = "\"Test File:3\"\n" +
                "This is a test file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!!  . xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }


    @Test
    public void myMainTest40() throws Exception {

        //Purpose of testcase: testing replace functionality on three non empty files with two replace combination(-b -f) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here it backs up
        // the file and replaces the first occurrence of string

        /*
        Presence of a file(s) where replace functionality is applied :  Three file
         Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters

        */
        File inputFile1 = createFileD();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        String args[] = {"-b", "-f", "is", "AA", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "ThAA is a very simple file that composes of special characters !!! @@. let's do it ";

        String expected2 = "\"Test File:2\"\n" +
                "ThAA is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";
        String expected3 = "\"Test File:3\"\n" +
                "ThAA is a test file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }


    @Test
    public void myMainTest41() throws Exception {

        //Purpose of testcase: testing replace functionality on three non empty files with two replace combination(-f, -l) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it
        // replaces the first occurrence and last occurrence of string

        /*


        Presence of a file(s) where replace functionality is applied :  Three file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters
        */
        File inputFile1 = createFileD();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        String args[] = {"-f", "-l", "is", "AA", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "ThAA AA a very simple file that composes of special characters !!! @@. let's do it ";

        String expected2 = "\"Test File:2\"\n" +
                "ThAA is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this AA going to be interesting and we know it!!";
        String expected3 = "\"Test File:3\"\n" +
                "ThAA is a test file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this AA going to be interesting and we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }


    @Test
    public void myMainTest42() throws Exception {


        //Purpose of testcase: testing replace functionality on three non empty file with two replace combination(-b ,-i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = spaces(>1). Here it replaces
        //the all occurrences of string (case insensitive) and creates a backup of the file


        /*


        Presence of a file(s) where replace functionality is applied :  Three file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  spaces
        */
        File inputFile1 = createFileD();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        String args[] = {"-b", "-i", "!!", "a sa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "This is a very simple file that composes of special characters a sa! @@. let's do it ";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utilitya sa\n" +
                "The replace can happen in multiple filesa sa @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know ita sa";
        String expected3 = "\"Test File:3\"\n" +
                "This is a test file 3 for the replace utilitya sa\n" +
                "The replace can happen in multiple filesa sa @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know ita sa";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }


    @Test
    public void myMainTest43() throws Exception {


        //Purpose of testcase: testing replace functionality on three non empty files with replace combinations(-f and -l) where
        //FromStringContent = special characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first and last occurrence of string


        /*
        Presence of a file(s) where replace functionality is applied :  Three file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Two replace options combination(b & f, b & l, b & i, f & l, f & i, l & i)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        String args[] = {"-f", "-l", "!!", "as", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test itas!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesomeas";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utilityas\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know itas";

        String expected3 = "\"Test File:3\"\n" +
                "This is a test file 3 for the replace utilityas\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know itas";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }


    @Test
    public void myMainTest44() throws Exception {

        //Purpose of testcase: testing replace functionality on four non empty files with three replace combination(-b, -f, -l) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first and last occurrence of string and creates a backup of the file

        /*
        Presence of a file(s) where replace functionality is applied :  four file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters


        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-b", "-f", "-l", "es", "ES", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile4.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "TESting File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awESome!!";

        String expected2 = "\"TESt File:2\"\n" +
                "This is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interESting and we know it!!";

        String expected3 = "\"TESt File:3\"\n" +
                "This is a test file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interESting and we know it!!";

        String expected4 = "\"TESt File:4\"\n" +
                "This is a test file 4 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interESting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertTrue(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));

    }


    @Test
    public void myMainTest45() throws Exception {

        //Purpose of testcase: testing replace functionality on four non empty file with three replace combination(-b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the first occurrence, all occurrence(case insensitive)  of string

        /*

        Presence of a file(s) where replace functionality is applied :  four file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-b", "-f", "-i", "rep", "!@", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile4.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "!@lace utility is used to test a file with !@lace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"!@lace Utility\" is awesome!!";


        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the !@lace utility!!\n" +
                "The !@lace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String expected3 = "\"Test File:3\"\n" +
                "This is a test file 3 for the !@lace utility!!\n" +
                "The !@lace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";
        String expected4 = "\"Test File:4\"\n" +
                "This is a test file 4 for the !@lace utility!!\n" +
                "The !@lace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertTrue(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));

    }


    @Test
    public void myMainTest46() throws Exception {


        //Purpose of testcase: testing replace functionality on four non empty files with three replace combination(-f, -l, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = spaces(>1). Here it replaces
        //the first, last occurrence of string and all occurrence (case insensitive).

        /*
        Presence of a file(s) where replace functionality is applied :  Four file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  spaces

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-f", "-l", "-i", "Rep", "  s", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath(), inputFile4.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "  slace utility is used to test a file with   slace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"  slace Utility\" is awesome!!";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the   slace utility!!\n" +
                "The   slace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";
        String expected3 = "\"Test File:3\"\n" +
                "This is a test file 3 for the   slace utility!!\n" +
                "The   slace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";
        String expected4 = "\"Test File:4\"\n" +
                "This is a test file 4 for the   slace utility!!\n" +
                "The   slace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertFalse(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));
    }


    @Test
    public void myMainTest47() throws Exception {
        //Purpose of testcase: testing replace functionality on four non empty files with three replace combination(-l, -i, -b) where
        //FromStringContent = special characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the last occurrence of string, all occurrence of string (case insensitive) and creates a back up of the file

        /*

        Presence of a file(s) where replace functionality is applied :  Four file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-l", "-i", "-b", "!!", "@@", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath(), inputFile4.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it@@!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome@@";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility@@\n" +
                "The replace can happen in multiple files@@ @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it@@";

        String expected3 = "\"Test File:3\"\n" +
                "This is a test file 3 for the replace utility@@\n" +
                "The replace can happen in multiple files@@ @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it@@";

        String expected4 = "\"Test File:4\"\n" +
                "This is a test file 4 for the replace utility@@\n" +
                "The replace can happen in multiple files@@ @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it@@";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertTrue(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));
    }


    @Test
    public void myMainTest48() throws Exception {
        //Purpose of testcase: testing replace functionality on four non empty files with three replace combination(-l, -i, -f)) where
        //FromStringContent = spaces (>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first and last occurrence of string, all occurrence with no case insensitivity.


        /*

        Presence of a file(s) where replace functionality is applied :  Four file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Three replace options combination (b&f&l, b&f&i, f&l&i, l&i&b, l&i&f)
        FromStringLength                                             :  >1
        FromStringContent                                            :  spaces
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-l", "-i", "-f", "s ", "@@@", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath(), inputFile4.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility i@@@used to test a file with replace options\n" +
                "now let'@@@test it!!!\n" +
                "thi@@@file ha@@@a bunch of codes...\n" +
                "We all know that \"Replace Utility\" i@@@awesome!!";

        String expected2 = "\"Test File:2\"\n" +
                "Thi@@@i@@@a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let'@@@see how thing@@@change@@@here.... \n" +
                "A@@@alway@@@thi@@@i@@@going to be interesting and we know it!!";

        String expected3 = "\"Test File:3\"\n" +
                "Thi@@@i@@@a test file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let'@@@see how thing@@@change@@@here.... \n" +
                "A@@@alway@@@thi@@@i@@@going to be interesting and we know it!!";

        String expected4 = "\"Test File:4\"\n" +
                "Thi@@@i@@@a test file 4 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let'@@@see how thing@@@change@@@here.... \n" +
                "A@@@alway@@@thi@@@i@@@going to be interesting and we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertFalse(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));
    }


    @Test
    public void myMainTest49() throws Exception {


        //Purpose of testcase: testing replace functionality on four non empty files with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here it replaces
        //the first, last occurrence of string and all occurrence (case insensitive) and creates a back up of the file as well.


        /*

        Presence of a file(s) where replace functionality is applied :  Four file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Four replace option combination (b & f & l & i )
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  alphanumeric characters
        */

        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-l", "-b", "-f", "-i", "File", "drive", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath(), inputFile4.getPath()};
        Main.main(args);

        String expected1 = "Testing drive :\n" +
                "Replace utility is used to test a drive with replace options\n" +
                "now let's test it!!!\n" +
                "this drive has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String expected2 = "\"Test drive:2\"\n" +
                "This is a test drive 2 for the replace utility!!\n" +
                "The replace can happen in multiple drives!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String expected3 = "\"Test drive:3\"\n" +
                "This is a test drive 3 for the replace utility!!\n" +
                "The replace can happen in multiple drives!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String expected4 = "\"Test drive:4\"\n" +
                "This is a test drive 4 for the replace utility!!\n" +
                "The replace can happen in multiple drives!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertTrue(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));
    }


    @Test
    public void myMainTest50() throws Exception {

        //Purpose of testcase: testing replace functionality on four non empty file with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the firs, last occurrence of string and all occurrence (case insensitive) and creates a back up of the file as well.

        /*


        Presence of a file(s) where replace functionality is applied :  four file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Four replace option combination (b & f & l & i )
        FromStringLength                                             :  >1
        FromStringContent                                            :  alphanumeric characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters
        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-l", "-b", "-f", "-i", "File", "@@@/", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath(), inputFile4.getPath()};
        Main.main(args);

        String expected1 = "Testing @@@/ :\n" +
                "Replace utility is used to test a @@@/ with replace options\n" +
                "now let's test it!!!\n" +
                "this @@@/ has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String expected2 = "\"Test @@@/:2\"\n" +
                "This is a test @@@/ 2 for the replace utility!!\n" +
                "The replace can happen in multiple @@@/s!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String expected3 = "\"Test @@@/:3\"\n" +
                "This is a test @@@/ 3 for the replace utility!!\n" +
                "The replace can happen in multiple @@@/s!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String expected4 = "\"Test @@@/:4\"\n" +
                "This is a test @@@/ 4 for the replace utility!!\n" +
                "The replace can happen in multiple @@@/s!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertTrue(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));
    }


    @Test
    public void myMainTest51() throws Exception {

        //Purpose of testcase: testing replace functionality on four non empty files with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = special characters(>1) and toStringContent = special characters(>1). Here it replaces
        //the firs, last occurrence of string and all occurrence (case insensitive) and creates a back up of the file as well.

        /*
        Presence of a file(s) where replace functionality is applied :  Four file
        Content of replace file                                      :  Non-empty
        Options For Replace                                          :  Four replace option combination (b & f & l & i )
        FromStringLength                                             :  >1
        FromStringContent                                            :  special characters
        ToStringLength                                               :  >1
        ToStringContent                                              :  special characters

        */
        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-l", "-b", "-f", "-i", "!!", " @##@ ", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath(), inputFile4.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it @##@ !\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome @##@ ";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility @##@ \n" +
                "The replace can happen in multiple files @##@  @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it @##@ ";

        String expected3 = "\"Test File:3\"\n" +
                "This is a test file 3 for the replace utility @##@ \n" +
                "The replace can happen in multiple files @##@  @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it @##@ ";

        String expected4 = "\"Test File:4\"\n" +
                "This is a test file 4 for the replace utility @##@ \n" +
                "The replace can happen in multiple files @##@  @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it @##@ ";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertTrue(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));

    }


    //------------------------
    // This is for Empty files
    //------------------------


    @Test
    public void myMainTest52() throws Exception {

        //Purpose of testcase: testing replace functionality on one empty file with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here the emptyfile is not changed and
        //a backup of empty file is created.


        File inputFile1 = createFileEmpty();
        String args[] = {"-l", "-b", "-f", "-i", "abc", "XYZ", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }


    @Test
    public void myMainTest53() throws Exception {

        //Purpose of testcase: testing replace functionality on three empty file with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here the emptyfiles are not changed and
        //a backup of each empty files are created.


        File inputFile1 = createFileEmpty();
        File inputFile2 = createFileEmpty();
        File inputFile3 = createFileEmpty();
        String args[] = {"-l", "-b", "-f", "-i", "abc", "XYZ", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);

        String expected1 = "";
        String expected2 = "";
        String expected3 = "";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }


    @Test
    public void myMainTest54() throws Exception {


        //Purpose of testcase: testing replace functionality on one file with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = empty. Here all
        // the matching strings are replaced with case insensitive. A backup for the file is also created


        File inputFile1 = createFileA();
        String args[] = {"-l", "-b", "-f", "-i", "File", "", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Testing  :\n" +
                "Replace utility is used to test a  with replace options\n" +
                "now let's test it!!!\n" +
                "this  has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    @Test
    public void myMainTest55() throws Exception {

        //Purpose of testcase: testing replace functionality on four non empty files with four replace combination(-l, -b, -f, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = empty. Here it replaces
        //the firs, last occurrence of string and all occurrence (case insensitive) and creates a back up of the file as well.


        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-l", "-b", "-f", "-i", "!!", "", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath(), inputFile4.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome";

        String expected2 = "\"Test File:2\"\n" +
                "This is a test file 2 for the replace utility\n" +
                "The replace can happen in multiple files @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it";

        String expected3 = "\"Test File:3\"\n" +
                "This is a test file 3 for the replace utility\n" +
                "The replace can happen in multiple files @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it";

        String expected4 = "\"Test File:4\"\n" +
                "This is a test file 4 for the replace utility\n" +
                "The replace can happen in multiple files @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertTrue(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));

    }

    @Test
    public void myMainTest56() throws Exception {


        //Purpose of testcase: testing replace functionality on one file with four same replace combination(-i, -i, -i, -i) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here all
        // the matching strings(case insensitive) are replaced case insensitive. A backup for the file is not created


        File inputFile1 = createFileA();
        String args[] = {"-i", "-i", "-i", "-i", "File", "files", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Testing files :\n" +
                "Replace utility is used to test a files with replace options\n" +
                "now let's test it!!!\n" +
                "this files has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }


    //Review
    @Test
    public void myMainTest57() throws Exception {


        //Purpose of testcase: testing replace functionality on one file with four same replace combination(-f, -f, -f, -f) where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here only
        // the first occurrence strings are replaced. A backup for the file is not created


        File inputFile1 = createFileA();
        String args[] = {"-f", "-f", "-f", "-f", "File", "files", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Testing files :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));


    }
    //FIX IT
    @Test
    public void myMainTest58() throws Exception {


        //Purpose of testcase: testing replace functionality on one file with 0 replace combination() where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here it
        // follows its default behavior, all case sensitive matching instances replaced, and no backup


        File inputFile1 = createFileA();
        String args[] = {"File", "files", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Testing files :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

    }

    @Test
    public void myMainTest59() throws Exception {

        //Purpose of testcase: testing replace functionality on four non empty files with zero replace combination() where
        //FromStringContent = alphanumeric characters(>1) and toStringContent = alphanumeric characters(>1). Here it
        // follows its default behavior, all case sensitive matching instances replaced, and no backup


        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"File", "files", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath(), inputFile4.getPath()};
        Main.main(args);

        String expected1 = "Testing files :\n" +
                "Replace utility is used to test a file with replace options\n" +
                "now let's test it!!!\n" +
                "this file has are bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String expected2 = "\"Test files:2\"\n" +
                "This is a test file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String expected3 = "\"Test files:3\"\n" +
                "This is a test file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String expected4 = "\"Test files:4\"\n" +
                "This is a test file 4 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertFalse(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));
    }

    @Test
    public void myMainTest60() throws Exception {

        //Purpose of testcase: testing replace functionality when the sufficient parameters are not entered. Here it
        // throw as usage error.
        String args[] = {"replace"};
        Main.main(args);
        assertEquals("Usage: replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    @Test
    public void myMainTest61() throws Exception {

        //Purpose of testcase: testing replace functionality when the sufficient parameters are not entered. Here it
        // throw as usage error.
        File inputFile1 = createFileA();
        String args[] = {"replace", "-i", "-i", "-i", "-i", "File", "files", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString());
    }


    //Creating file 6
    public File createFilePosixA() throws Exception {
        File fileEmpty = newFile();
        FileWriter writeOnFile = new FileWriter(fileEmpty);

        writeOnFile.write("This is a test App -i app is good -ii app is bad, -iii app is even better");

        writeOnFile.close();
        return fileEmpty;
    }

    //Creating file 7
    public File createFilePosixB() throws Exception {
        File fileEmpty = newFile();
        FileWriter writeOnFile = new FileWriter(fileEmpty);

        writeOnFile.write("This is a test App rating. -i app is 5, -ii app is 6, -I app is 7");

        writeOnFile.close();
        return fileEmpty;
    }

    @Test
    public void myMainTest62() throws Exception {

        //Purpose of testcase: testing replace functionality if it follows POSIXS utility argument syntax i.e .
        //The first -- argument that is not an option-argument should be accepted as a delimiter indicating the
        // end of options. Any following arguments should be treated as operands, even if they begin with the '-' character.
        // Here: -b creates a back up of the file and "-f" replaces the first string but after "--"
        // the other parameters are not executed. Here "-i" is being
        //replaced by 1 and "-i" is not passed as a parameter.


        File inputFile1 = createFilePosixA();
        String args[] = {"-b", "-f", "--", "-i", "1.", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "This is a test App 1. app is good -ii app is bad, -iii app is even better";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));


    }

    @Test
    public void myMainTest63() throws Exception {

        //Purpose of testcase: testing replace functionality if it follows POSIXS utility argument syntax i.e .
        //The first -- argument that is not an option-argument should be accepted as a delimiter indicating the
        // end of options. Any following arguments should be treated as operands, even if they begin with the '-' character.
        // Here: -b creates a back up of the file and "-i" replaces all string case insensitive but after "--"
        // the other parameters are not executed. Here "-i" is being
        //replaced by 1 and "-i" is not passed as a parameter. Action performed in multiple files


        File inputFile1 = createFilePosixA();
        File inputFile2 = createFilePosixB();
        String args[] = {"-b", "-i", "--", "-i", "1.", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "This is a test App 1. app is good -ii app is bad, -iii app is even better";
        String expected2 = "This is a test App rating. 1. app is 5, -ii app is 6, 1. app is 7";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));



    }

    @Test
    public void myMainTest64() throws Exception {

        //Purpose of testcase: testing replace functionality for a single character with options -l, -f, -i and -b. It should
        //replace each and every character (case insensitive) and should create a back up. This should do for all the files.


        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-f", "-l", "-b", "-i", "--", "a", "A", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath(), inputFile4.getPath()};
        Main.main(args);

        String expected1 = "Testing File :\n" +
                "ReplAce utility is used to test A file with replAce options\n" +
                "now let's test it!!!\n" +
                "this file hAs Are bunch of codes...\n" +
                "We All know thAt \"ReplAce Utility\" is Awesome!!";

        String expected2 = "\"Test File:2\"\n" +
                "This is A test file 2 for the replAce utility!!\n" +
                "The replAce cAn hAppen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things chAnges here.... \n" +
                "As AlwAys this is going to be interesting And we know it!!";

        String expected3 = "\"Test File:3\"\n" +
                "This is A test file 3 for the replAce utility!!\n" +
                "The replAce cAn hAppen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things chAnges here.... \n" +
                "As AlwAys this is going to be interesting And we know it!!";

        String expected4 = "\"Test File:4\"\n" +
                "This is A test file 4 for the replAce utility!!\n" +
                "The replAce cAn hAppen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things chAnges here.... \n" +
                "As AlwAys this is going to be interesting And we know it!!";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertTrue(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));

    }

    //EDGE CASES

    @Test
    public void myMainTest65() throws Exception {

        //Purpose of testcase: testing replace functionality with special characters with options -i and -b. This should
        //enter a new line every time it finds the replace string (case insensitive)


        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-b", "-i", "--", "Test", "\n", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath(), inputFile4.getPath()};
        Main.main(args);

        String expected1 = "\n" +
                "ing File :\n" +
                "Replace utility is used to \n" +
                " a file with replace options\n" +
                "now let's \n" +
                " it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String expected2 = "\n" +
                "\" File:2\"\n" +
                "This is a \n" +
                " file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String expected3 = "\n" +
                "\" File:3\"\n" +
                "This is a \n" +
                " file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String expected4 = "\n" +
                "\" File:4\"\n" +
                "This is a \n" +
                " file 4 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertTrue(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));

    }

    @Test
    public void myMainTest66() throws Exception {

        //Purpose of testcase: testing replace functionality with special characters with options -i and -b. It should
        //replace each and every character (case insensitive) and should create a back up. This should do for all the files.


        File inputFile1 = createFileA();
        File inputFile2 = createFileB();
        File inputFile3 = createFileC();
        File inputFile4 = createFileE();
        String args[] = {"-b", "-i", "--", "Test", "\"Test\"", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath(), inputFile4.getPath()};
        Main.main(args);
        String expected1 = "\"Test\"ing File :\n" +
                "Replace utility is used to \"Test\" a file with replace options\n" +
                "now let's \"Test\" it!!!\n" +
                "this file has a bunch of codes...\n" +
                "We all know that \"Replace Utility\" is awesome!!";

        String expected2 = "\"\"Test\" File:2\"\n" +
                "This is a \"Test\" file 2 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String expected3 = "\"\"Test\" File:3\"\n" +
                "This is a \"Test\" file 3 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";

        String expected4 = "\"\"Test\" File:4\"\n" +
                "This is a \"Test\" file 4 for the replace utility!!\n" +
                "The replace can happen in multiple files!! @@ xx yy zz\n" +
                "Let's see how things changes here.... \n" +
                "As always this is going to be interesting and we know it!!";


        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files match!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files match!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

        String actual3 = getFileContent(inputFile3.getPath());
        assertEquals("The files match!", expected3, actual3);
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

        String actual4 = getFileContent(inputFile4.getPath());
        assertEquals("The files match!", expected4, actual4);
        assertTrue(Files.exists(Paths.get(inputFile4.getPath() + ".bck")));

    }


}