import java.util.Scanner;
import java.security.SecureRandom;

public class wordSearch {

    public static void main(String[] args) {
        char[][] puzzle = new char[20][20]; // array for chars to put into puzzle

        create(getWords(), puzzle);

        System.out.println();

        //Prints out the puzzle w/o solution
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                System.out.print(puzzle[x][y] + " ");
            }
            System.out.println();
        }
    } // end of main method
    
    //intrdouces the program to the user
    public static void intro() {
        System.out.println("Welcome to the Word Search Generator");
        System.out.println("This program will take some words and");
        System.out.println("turn it in to a 20x20 word search.");
    } // end of intro method

    //displays options of the program to the user
    public static void menu() {
        System.out.println("1) Create a word search puzzle");
        System.out.println("2) View the word search");
        System.out.println("3) View word search with solutions");
        System.out.println("4) Quit");
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
            }
        } // end of for loop

        if (justLetters.length() < 6 || justLetters.length() > 9) {
            goodLength = false;
        } // end of if statement

        return goodLength;
    } // end of lengthChecker method

    public static int createIntro () {
        Scanner input = new Scanner(System.in);

        System.out.println("Rules for Creating Word Search:\n");
        System.out.println("1. Each word must be at least 5 letters");
        System.out.println("2. Each word must be no greater than 8 letters");
        System.out.println("3. Special characters, numbers, and spaces will not be included");
        System.out.println("4. You can not use more than 8 words\n");
        System.out.print("How many words would you like to use? ");

        int wordNum;
        while (true) {
            wordNum = input.nextInt();
            input.nextLine();
            if (wordNum > 0 && wordNum < 9) {
                break;
            }
            else {
                System.out.println("Invalid number of words. Try again with a number from 1-8");
            } // end of if-else statment
        } // end of while loop

        return wordNum;
    } // end of createIntro method

    //asks the user for the words and returns wordList array
    public static String[] getWords() {
        Scanner input = new Scanner(System.in);

        String[] wordList = new String[createIntro()]; // array for user-input words

        System.out.println("Please enter your words: ");
        for (int i = 0; i < wordList.length; i++) {
            String word;
            while (true) {
                word = input.nextLine().toUpperCase();
                if (lengthChecker(word)) {
                    break;
                }
                else {
                    System.out.println("Your word did not meet length requirments.");
                    System.out.println("Please input another word: ");
                } // end of if-else statement
            } // end of while loop
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

    // creates the word search puzzle stored in a 2d array
    public static char[][] create(String[] wordList, char[][] puzzle){
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
            } // end of for loop
            while (x < 20) {
                puzzle[x][y] = randomLetter();
                x++;
            } // end of while loop
            x = 0;
            y++;
        } // end of for loop
        for (int i = 0; i < 20 - wordList.length; i ++) {
            while (x < 20) {
                puzzle[x][y] = randomLetter();
                x++;
            } // end of while loop 
            x = 0;
            y++;
        } // end of for loop

        return puzzle;
    } // end of create method
    
} // end of wordSearch class