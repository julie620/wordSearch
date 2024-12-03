//Name: Juliana Serrano
//Date: 10/22/2024
//Assignment: 1 - Word Search
//Description: Creates a word search from user input words

import java.util.Scanner;
import java.security.SecureRandom;

public class wordSearch {

    private static Scanner input = new Scanner(System.in);
    public static String[] wordList;
    public static char[][] puzzle = new char[20][20];
    public static char[][] solution = new char[20][20];
    public static void main(String[] args) {
        intro();

        Boolean searching = true;

        do {
            searching = choice();
        }
        while(searching); // end of do while loop

    } // end of main method
    
    // introduces the program to the user
    public static void intro() {
        System.out.println("Welcome to the Word Search Generator");
        System.out.println("This program will take some words and");
        System.out.println("turn it in to a 20x20 word search.\n");
    } // end of intro method

    // prints out menu options
    public static void menu() {
        System.out.println("\nChoose an option (1-4): ");
        System.out.println("1) Create a word search puzzle");
        System.out.println("2) View the word search");
        System.out.println("3) View word search with solutions");
        System.out.println("4) Quit");
    } // end of menu method

    // displays options of the program to the user
    public static Boolean choice() {
        menu();

        int response = input.nextInt();
        input.nextLine();
        switch (response) {
            case 1: // create a word search puzzle
                wordList = create();
                construct();
                break;
            case 2: // view the created word search puzzle
                view(puzzle);
                break;
            case 3:// view the solution of the word search puzzle
                view(solution);
                break;
            case 4: // exit the program
                return false;
            default:
                System.out.println("That is not a valid option. Try again.");
        } // end of swtich statement
        return true;
    } // end of menu method

    // checks if the length of a word is in the correct range
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

    // explains rules of creating puzzle and takes user input of words
    public static int createIntro () {
        System.out.println("\nRules for Creating Word Search:\n");
        System.out.println("1. Each word must be at least 5 letters");
        System.out.println("2. Each word must be no greater than 8 characters");
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
        wordList = new String[createIntro()]; // array for user-input words

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
    public static void garbageChar() {
        for(int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                    solution[x][y] = '*';
                    puzzle[x][y] = randomLetter();
            }// end of nested for loop
        } // end of for loop
    } // end of garabgeChar method

    //chooses what type of layout the word will get
    public static int layout() {
        SecureRandom random = new SecureRandom();
        // 0 = horizontal, 1 = vertical, and 2 = diagonal
        int randNum = random.nextInt(3);
        return randNum;
    } // end of layout method

    // decides increment of indexes after each letter in a word
    public static void afterLetter(int[] index) {
        if (index[2] == 0) {
            index[0]++;
        } // end of if statement
        else if (index[2] == 1) {
            index[1]++;
        } // end of else if statement
        else if (index[2] == 2) {
            index[0]++;
            index[1]++;
        } // end of else if statement
    } // end of afterLetter method

    // decides the starting indexes of the next word
    public static void startingIndex(int index[]) {
        SecureRandom random = new SecureRandom(); 
        index[0] = random.nextInt(11);
        // if the word has a horizontal layout the y index may range up to 19
        if (index[2] == 0) { 
            index[1] = random.nextInt(19);
        } // end of if statement
        // if the word has a diagonal or vertical layout its range is up to 11
        else if (index[2] == 1 || index[2] == 2) { 
            index[1] = random.nextInt(11);
        } // end of else if statement
    } // end of afterWord method

    // tracks the indexes that the words use
    public static void trackIndexes (int[] index, int[] usedIndexes, int k) {
        usedIndexes[k] = index[0] + 1; // x index
        k++;
        usedIndexes[k] = index[1] + 1; // y index 
    } // end of trackIndexes method

    // records potential index postions of a word
    public static int[] tempPlace (int[] index, String word) {
        int k = 0;
        int wordIndexes = word.length() * 2;
        int x = index[0] + 1;
        int y = index[1] + 1;
        int[] testIndexes = new int[wordIndexes];
        // goes through word and assigns the letters indexes
        for (int j = 0; j < word.length(); j ++) { 
            testIndexes[k] = x;
            k++;
            testIndexes[k] = y;
            k++;
            if (index[2] == 0) {
                x++;
            } // end of if statement
            else if (index[2] == 1) {
                y++;
            } // end of else if statement
            else if (index[2] == 2) {
                x++;
                y++;
            } // end of else if statement
        }
        return testIndexes;
    } // end of tempPlace method

    //checks if test indexes conflict with already used indexes
    public static Boolean checkPlace (int[] testIndexes, int[] usedIndexes) {
        Boolean badPlace = false;
        for (int i = 0; i < testIndexes.length; i+= 2) {
            for (int u = 0; u < usedIndexes.length; u += 2) {
                // if a test indexe is the same as an used indexe it will return badPlace as true
                if (testIndexes[i] == usedIndexes[u] && testIndexes[i + 1] == usedIndexes[u + 1]) {
                    badPlace = true;
                    break;
                }
            }
        }
        return badPlace;
    } // end of checkPlace method

    // takes just the letters out of the user's word
    public static String onlyLetters(int i) {
        String theLetters = " ";
        for (int u = 0; u < wordList[i].length(); u++) {
            char letter = wordList[i].charAt(u);
            int number = (int)letter;
            if (number > 64 && number < 91) { // uses Dec value to test if char is letter
                theLetters = theLetters.concat(Character.toString(letter));
            } // end of if statement
        }
        return theLetters;
    } // end of onlyLetters method

    // creates the word search puzzle stored in a two 2d array: puzzle and solution
    public static void construct(){
        int[] index = {0, 0, 0}; // x of 2d arrays, y of 2d arrays, layout type
        int[] usedIndexes = new int[wordList.length * 16];
        int k = 0; // tracks index for usedIndexes
        garbageChar();
        for (int i = 0; i < wordList.length; i ++) {
            index[2] = layout();
            startingIndex(index);
            String word = onlyLetters(i).trim();
            while (true) {
                if (checkPlace(tempPlace(index, word), usedIndexes)) {
                   startingIndex(index);
                }
                else{
                    break;
                }
            }
            for (int u = 0; u < word.length(); u++) {
                char letter = word.charAt(u);
                puzzle[index[0]][index[1]] = letter;
                solution[index[0]][index[1]] = letter;
                trackIndexes(index, usedIndexes, k);
                k += 2;
                afterLetter(index);
            } // end of nested for loop
            startingIndex(index);
        } // end of for loop
    } // end of construct method

    //Prints out the puzzle or solution
    public static void view(char[][] type) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                System.out.print(type[x][y] + " ");
            } // end of nested for loop
            System.out.println();
        } // end of for loop
    } // end of view method

} // end of wordSearch class