import java.util.Scanner;
import java.security.SecureRandom;

public class wordSearch {

    public static String[] wordList = new String[5]; // array for user-input words
    public static char[][] puzzle = new char[24][24]; // array for chars to put into puzzle

    public static void main(String[] args) {
        getWords(wordList);
        create(wordList, puzzle);

        System.out.println();

        //Prints out the puzzle w/o solution
        for (int y = 0; y < 24; y++) {
            for (int x = 0; x < 24; x++) {
                System.out.print(puzzle[x][y] + " ");
            }
            System.out.println();
        }

    } // end of main method
    
    //intrdouces the program to the user
    public static void intro() {
        System.out.println("Welcome to the Word Search Generator");
        System.out.println("This program will take some words and");
        System.out.println("turn it in to a 24x24 word search.");
    } // end of intro method

    //displays options of the program to the user
    public static void menu() {
        System.out.println("1) Create a word search puzzle");
        System.out.println("2) View the word search");
        System.out.println("3) View word search with solutions");
        System.out.println("4) Quit");
    } // end of menu method

    //asks the user for the words and returns wordList array
    public static String[] getWords(String[] wordList) {
        Scanner input = new Scanner(System.in);

        System.out.println("In order for this program to create a word search");
        System.out.println("there are a couple of rules:");
        System.out.println("1. Input five(5) words");
        System.out.println("2. Each word must be at least 5 letters");
        System.out.println("3. Each word mus be no greater than 24 letters");

        System.out.println("Please enter your words: ");
        for (int i = 0; i < 5; i++) {
            String word = input.nextLine().toUpperCase();
            wordList[i] = word;
        } // end of for loop

        return wordList;
    } // end of getWords method

    //generates a randome letter
    public static char randomLetter() {
        SecureRandom random = new SecureRandom();
        int randNum = random.nextInt(26) + 65;
        char randLetter = (char) randNum;
        return randLetter;
    }

    // creates the word search puzzle stored in a 2d array
    public static char[][] create(String[] wordList, char[][] puzzle){
        int x = 0;
        int y = 0;
        for (int i = 0; i < 5; i ++) {
            for (int u = 0; u < wordList[i].length(); u++) {
                char letter = wordList[i].charAt(u);
                int number = (int)letter;
                if (number > 64 && number < 91) { // uses Dec value to test if char is letter
                    puzzle[x][y] = letter;
                    x++;
                } // end of if statement
            } // end of for loop
            while (x < 24) {
                puzzle[x][y] = randomLetter();
                x++;
            } // end of while loop
            x = 0;
            y++;
        } // end of for loop
        for (int i = 0; i < 19; i ++) {
            while (x < 24) {
                puzzle[x][y] = randomLetter();
                x++;
            } // end of while loop 
            x = 0;
            y++;
        } // end of for loop

        return puzzle;
    } // end of create method
    
} // end of wordSearch class
