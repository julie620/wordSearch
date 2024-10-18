import java.util.Scanner;
import java.security.SecureRandom;

public class wordSearch {

    private static Scanner input = new Scanner(System.in);
    public static String[] wordList;
    public static char[][] puzzle = new char[20][20];
    public static void main(String[] args) {
        intro();

        Boolean searching = true;

        do {
            searching = choice();
        }
        while(searching); // end of do while loop

    } // end of main method
    
    //intrdouces the program to the user
    public static void intro() {
        System.out.println("Welcome to the Word Search Generator");
        System.out.println("This program will take some words and");
        System.out.println("turn it in to a 20x20 word search.\n");
    } // end of intro method

    public static void menu() {
        System.out.println("\nChoose an option (1-4): ");
        System.out.println("1) Create a word search puzzle");
        System.out.println("2) View the word search");
        System.out.println("3) View word search with solutions");
        System.out.println("4) Quit");
    }

    //displays options of the program to the user
    public static Boolean choice() {
        menu();

        int response = input.nextInt();
        input.nextLine();
        switch (response) {
            case 1: 
                wordList = create();
                break;
            case 2:
                construct(false);
                view(puzzle);
                break;
            case 3:
                construct(true);
                view(puzzle);
                break;
            case 4:
                return false;
            default:
                System.out.println("That is not a valid option. Try again.");
        } // end of swtich statement
        return true;
    } // end of menu method

    //checks if the length of a word is in the correct range
    public static Boolean lengthChecker(String word) {
        Boolean goodLength = true;
        String justLetters = " ";

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int number = (int)letter;
            if (number > 64 && number < 91) { // uses Dec value to test if char is letter
                String single = String.valueOf(letter);
                justLetters = justLetters.concat(single);
            } // end of if statement
        } // end of for loop

        if (justLetters.length() < 6 || justLetters.length() > 9) {
            goodLength = false;
        } // end of if statement

        return goodLength;
    } // end of lengthChecker method

    public static int createIntro () {
        System.out.println("\nRules for Creating Word Search:\n");
        System.out.println("1. Each word must be at least 5 letters");
        System.out.println("2. Each word must be no greater than 8 letters");
        System.out.println("3. Special characters, numbers, and spaces will not be included");
        System.out.println("4. You can not use more than 8 words\n");
        System.out.print("How many words would you like to use? ");

        int wordNum; 
        while (true) {
            wordNum = input.nextInt();
            input.nextLine();
            if (wordNum > 0 && wordNum< 9) {
                break;
            } // end of if statement
            else {
                System.out.println("Please try again with a number between 1-8");
            } // end of else statement
        } // end of while loop

        return wordNum;
    } // end of createIntro method

    //asks the user for the words and returns wordList array
    public static String[] create() {
        String[] wordList = new String[createIntro()]; // array for user-input words

        System.out.println("Please enter your words: ");
        for (int i = 0; i < wordList.length; i++) {
            String word;
            do {
                word = input.nextLine().toUpperCase();
                if (lengthChecker(word)) {
                    break;
                } // end of if statement
                else {
                    System.out.println("Your word did not meet length requirments.");
                    System.out.println("Please input another word: ");
                } // end of else statement
            }
            while (true);// end of do while loop
            wordList[i] = word;
        } // end of for loop

        return wordList;
    } // end of getWords method

    //generates a random letter
    public static char randomLetter() {
        SecureRandom random = new SecureRandom();
        int randNum = random.nextInt(26) + 65;
        char randLetter = (char) randNum;
        return randLetter;
    } // end of randomLetter method

    //prints garbage characters in appropriate spaces
    public static void garbageChar(int x, int y, Boolean solution) {
        while (x < 20) {
            if (solution == true) {
                puzzle[x][y] = '*';
            } // end of if statement
            else {
                puzzle[x][y] = randomLetter();
            } // end of else statement
            x++;
        } // end of while loop
    }

    // creates the word search puzzle stored in a 2d array
    public static void construct(Boolean solution){
        int x = 0;
        int y = 0;
        for (int i = 0; i < wordList.length; i ++) {
            for (int u = 0; u < wordList[i].length(); u++) {
                char letter = wordList[i].charAt(u);
                int number = (int)letter;
                if (number > 64 && number < 91) { // uses Dec value to test if char is letter
                    puzzle[x][y] = letter;
                    x++;
                } // end of if statement
            } // end of nested for loop
            garbageChar(x, y, solution);
            x = 0;
            y++;
        } // end of for loop
        for (int i = 0; i < 20 - wordList.length; i ++) {
            garbageChar(x, y, solution);
            x = 0;
            y++;
        } // end of for loop
    } // end of create method

    //Prints out the puzzle w/o solution
    public static void view(char[][] puzzle) {
        
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                System.out.print(puzzle[x][y] + " ");
            } // end of nested for loop
            System.out.println();
        } // end of for loop
    } // end of view method

} // end of wordSearch class