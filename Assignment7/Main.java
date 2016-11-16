package edu.gatech.seclass.replace;


import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        String replacedContent="";
        Main newMain = new Main();
        System.out.println("Arguments !!!! "+ args.length);

        //{"-b", "!!!", "!@#$", "--", inputFile1.getPath()}

        /*for(int i = 0; i <args.length; i ++){
            if (args[i].toString().equals("-f")){
                File oldFile = new File(args[4]);
                newMain.firstReplceDashF("!!", "OOOO",oldFile);

                //replacedCc= newMain.firstReplceDashF(args[4]).toString();
                *//*String fileContent =newMain.getFileContent(args[4]);
                File oldFile = new File(args[4]);
                replacedContent = newMain.firstReplceDashF("!!", "OOOO",oldFile);
                System.out.println("YOOooooo replaceing " + replacedContent.toString());
                //newMain. getFileContentAfterReplace();

                FileWriter newFile = new FileWriter(args[4], false);
                newFile.write(replacedContent);
                newFile.close();*//*

            }*/
        boolean firstReplace = false;
        boolean lastReplace = false;
        boolean allReplace = false;
        boolean backUp = false;
        int noOfFile= 0;
        int posFileStart = 0;
        String fromString = null;
        String toString = null;
        for(int i = 0; i <args.length; i ++){

            if (args[i].toString().equals("-f")){
                firstReplace = true;
            }
            else if (args[i].toString().equals("-b")){
                backUp = true;
            }
            else if (args[i].toString().equals("-l")){
                lastReplace = true;
            }
            else if (args[i].toString().equals("-i")){
                allReplace = true;
            }
            else if (args[i].toString().equals("--")){
                noOfFile = args.length - i;
                posFileStart = i + 1;
                fromString = args[i-2];
                toString = args[i-1];
                if ((i-2 ==0) && (i -1 ==1)){
                    allReplace = true;
                }

            }

        }
        File [] files = new File[noOfFile];
        for (int i = posFileStart; i < args.length; i ++){
            if (firstReplace == true){
                File oldFile = new File(args[i]);
                newMain.firstReplceDashF(fromString, toString,oldFile);

            }

            if (lastReplace == true){
                File oldFile = new File(args[i]);
                newMain.lastReplceDashL(fromString, toString,oldFile);

            }

            if (allReplace == true){
                File oldFile = new File(args[i]);
                newMain.allReplceDashI(fromString, toString,oldFile);

            }

            if (backUp == true){
                File oldFile = new File(args[i]);
                newMain.backUpOption(oldFile);

            }

        }



        //return replacedContent;
    }

    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private static void usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" );
    }

    private Charset charset = StandardCharsets.UTF_8;


    private File createTmpFile() throws IOException {
        temporaryFolder.create();
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private File createInputFile1() throws Exception {
        File testFile =  createTmpFile();
        FileWriter fileWriter = new FileWriter(testFile);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return testFile;
    }

    private int commitChangesToFile() throws Exception{
        return 0;
    }
    public String getFileContentAfterReplace() throws Exception {

        String value = "IMPLEMENTATION YET TO BE COMPLETED IN D2";
        //Back up tested
        //backUpOption(inputFile1);


        return value;

    }


    /*public String getFileContentAfterReplace() throws Exception {


        //File filename = new File("example.txt");
        File inputFile1 = createInputFile1();
        String actual = getFileContent(inputFile1.getPath());

        String value = "IMPLEMENTATION YET TO BE COMPLETED IN D2";
        //Back up tested
        //backUpOption(inputFile1);


        String testingContent = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure File it has at least a few lines\n" +
                "so that we can create some Files interesting test cases...\n" +
                "And let's files say  is say \"howdy bill\" again!";

        //Both tested successfully
        //firstReplceDashF(testingContent);
        //allReplceDashI(testingContent);
        lastReplceDashF(testingContent);

        return value;
    }*/

    public File backUpOption(File sourceFile) throws IOException , AssertionError{
        //temporaryFolder.create();
        String targetFileName = sourceFile.getName() + ".bck";
        File targetFile = new File(targetFileName);
        String sourcepathDir = sourceFile.toPath().toString()+".bck";
        Path pathtest = Paths.get(sourcepathDir);

        Path sourcePathagain = sourceFile.toPath();
        Path toFilePath = targetFile.toPath();
        Files.copy( sourceFile.toPath(), pathtest);
        //Files.copy( sourceFile.toPath(), targetFile.toPath());
        /*String targetFileName = sourceFile.getName() + ".bck";
        File targetFile = new File(targetFileName);
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(sourceFile).getChannel();
            destChannel = new FileOutputStream(targetFile).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }*/
        return targetFile;


    }

    /*public File backUpOption(File sourceFile) throws IOException {
        //temporaryFolder.create();
        String targetFileName = sourceFile.getName() + "_backUp";
        File targetFile = new File(targetFileName);
        Files.copy( sourceFile.toPath(), targetFile.toPath());

        System.out.println("Copied file name = " +targetFile.getName());
        String fileContent = getFileContent(targetFileName);
        System.out.println("File contents == " + fileContent);
        System.out.println("File path == " + targetFile.getAbsolutePath());
        return targetFile;
    }*/

    /*public String allReplceDashI(String originalContent){
        System.out.print("\n All replace \n");

        System.out.print("File content For Dash Changes " + originalContent);
        String replacedContent = originalContent.replaceAll("a", "Drive");
        System.out.print("File content after replace " + replacedContent);

        return  replacedContent;
    }*/

    public void allReplceDashI(String fromString, String toString, File fileToConvert) throws IOException {
        String fileContent = getFileContent(fileToConvert.toString());
        Pattern patternWord = Pattern.compile(fromString, Pattern.CASE_INSENSITIVE);
        Matcher matchingWord = patternWord.matcher(fileContent);
        String replacedContent = matchingWord.replaceAll(toString);
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContent);
        newFile.close();

    }

    public void firstReplceDashF(String fromString, String toString, File fileToConvert) throws IOException {

        String fileContent = getFileContent(fileToConvert.toString());
        String replacedContentTest = fileContent.replaceFirst(fromString, toString);
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContentTest);
        newFile.close();

    }
    /*public String firstReplceDashF(String originalContent){
        System.out.print("\n First replace \n");
        System.out.print("File content For Dash Changes " + originalContent);

        String replacedContent = originalContent.replaceFirst("a", "Drive");
        System.out.print("File content after replace " + replacedContent);


        return replacedContent;
    }*/

   /* public String lastReplceDashF(String originalContent){
        //Using StringUtils library from commons jar
        System.out.print("\n Last replace \n");
        System.out.print("File content For Dash Changes " + originalContent);

        String replaceString="say";
        int lastIndex =originalContent.lastIndexOf(replaceString);
        String beforeReverse = StringUtils.reverse(originalContent).replaceFirst(StringUtils.reverse("say"), StringUtils.reverse("Drive"));
        String replacedContent = StringUtils.reverse(beforeReverse);
        //String replacedContent = originalContent.replaceFirst("say", "Drive");
        System.out.print("File content after replace " + replacedContent);


        return replacedContent;
    }*/

    public void lastReplceDashL(String fromString, String toString, File fileToConvert) throws IOException {
        String replaceFromString=fromString;
        String replaceToString = toString;
        String fileContent = getFileContent(fileToConvert.toString());
        String replacedContent = fileContent;
        int lastIndex =fileContent.lastIndexOf(replaceFromString);
        if (lastIndex >=0){
            replacedContent = fileContent.substring(0,lastIndex) + replaceToString + fileContent.substring(lastIndex + replaceFromString.length());
        }
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContent);
        newFile.close();
    }

    /*public String lastReplceDashL(String originalContent){
        System.out.print("\n Last replace \n");
        System.out.print("File content For Dash Changes " + originalContent);

        String replaceFromString="say";
        String replaceToString = "Drive";
        String replacedContent = originalContent;
        int lastIndex =originalContent.lastIndexOf(replaceFromString);
        if (lastIndex >=0){
            replacedContent = originalContent.substring(0,lastIndex) + replaceToString + originalContent.substring(lastIndex + replaceFromString.length());
        }

        System.out.print("File content after replace " + replacedContent);


        return replacedContent;
    }*/
    private void testFileContent(){

    }
}
