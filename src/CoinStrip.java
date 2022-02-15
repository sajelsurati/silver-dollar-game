/**
 * Silver Dollar Game
 * 
 * This class contains the mechanics for running the Silver Dollar Game
 * There are 5 methods being implemented
 *      1. Generate Board -- like a constructor class, but returns a list with coin positions
 *      2. Print Board -- Prints the board to the console
 *      3. Taking Turns -- Alternates between players 1 and 2, while checking if game is over
 *      4. Player Move -- Allows player to move and checks if the move is legal
 *      5. End Game Checker -- Checks to to see if the distances between all coins is 1
 * 
 * @author Sajel Surati
 * 
 */

import java.util.Random;
import java.util.Scanner;

public class CoinStrip {
    private static final int BEGINNING_MAXIMUM_SPACES = 6;
    private static final int NUMBER_OF_PLAYERS = 2;

    private static Scanner scan = new Scanner(System.in);
    private static Random random = new Random();

    private int boardLength;
    private int[] positionsOfCoins;
    private int numberOfCoins;
    private boolean gameOver;


    /**
     * Randomizes the positions of the coins
     * Distance between the coins varies from 0 spaces to 5
     * 
     * @param numberOfCoins The number of coins the user chooses
     * @return An array containing the starting positions of the coins
     */
    public CoinStrip(int coinNumber) {
        numberOfCoins = coinNumber;
        positionsOfCoins = new int[(numberOfCoins)];
        for (int i = 0; i < numberOfCoins; i++) {
            // Generates random number 0-5 for each coin
            int spaceInBetweenCoins = random.nextInt(BEGINNING_MAXIMUM_SPACES + 1);
            // If 0, the distance is calculated from position "0"
            if (i == 0) {
                positionsOfCoins[i] = spaceInBetweenCoins + 1;
            }
            // If > 0, the position is calculated by adding the random distance to the
            // position of
            // the coin before
            else {
                positionsOfCoins[i] = spaceInBetweenCoins + 1 + positionsOfCoins[i - 1];
            }
            // Calculates the length of the board by the position of the last coin
            boardLength = positionsOfCoins[(numberOfCoins - 1)];
        }
        printBoard();
        this.gameOver = false;
    }
    /**
     * Prints board to the console using numbers to represent coins and hyphens to represent
     * empty spaces
     * 
     */

    private void printBoard() {
        String board = "";
        String firstLine = "+";
        String secondLine = "|";
        // Checks for each position on the board whether there is a coin in that position
        for (int i = 1; i <= boardLength; i++) {
            boolean foundCoin = false;
            firstLine += " - - - +";
            for (int j = 0; j < numberOfCoins; j++) {
                // If there is a coin in the position, add the coin number to the string with
                // the actual coin being printed
                if (i == positionsOfCoins[j]) {
                    foundCoin = true;
                    secondLine += ("   " + j + "   |");
                }
            }
            // If there is no coin, add blank space to the string with the coins
            if (!foundCoin) {
                secondLine += "       |";
            }
        }
        // Makes board have two borders and the actual coin positions in the center
        board = firstLine + "\n\n" + secondLine + "\n\n" + firstLine;
        System.out.println(board);
    }

    /**
     * Alternates between players 1 and 2, using the playerMove method
     * 
     */

    public void takingTurns() {
        // Checks whether the game is over at the beginning
        int currentPlayer = 0;
        while (!gameOver) {
            for (int i = 1; i <= NUMBER_OF_PLAYERS; i++) {
                currentPlayer = i;
                // Runs the move for the current player
                playerMove(i);
                // Checks whether the move finished the game
                endGameChecker();
                printBoard();
                // Breaks the for loop if the game is finished
                if (gameOver) {
                    break;
                }
            }
        }
        System.out.println("Player " + currentPlayer + " wins!");

    }

    /**
     * Uses player imput to calculate whether the player can make the move entered
     * 
     * @param playerNumber Which player is moving
     */

    private void playerMove(int playerNumber) {
        boolean moveComplete = false;

        while (!moveComplete) {
            // Asks the player for their move
            System.out.println("Player " + playerNumber + ", enter your move: ");
            int movingPiece = scan.nextInt();
            int numberOfSpaces = scan.nextInt();
            // If the piece to move is > 0 and < the last coin

            if ((movingPiece >= 0 ) && (movingPiece < positionsOfCoins.length)) {
                int desiredPosition = positionsOfCoins[movingPiece] - numberOfSpaces;

                if (numberOfSpaces < 0) {
                    System.out.println("Cannot move piece less than 0 spaces");
                    moveComplete = false;
                }

                // If the piece moving is 0 and the desired position > the beginning of the strip
                else if ((movingPiece == 0 ) && (desiredPosition > 0)) {
                    positionsOfCoins[movingPiece] = desiredPosition;
                    moveComplete = true;
                }

                // If the piece moving is not 0 and the desired position is greater than that of
                // the coin before
                else if ((movingPiece > 0 ) && (desiredPosition > positionsOfCoins[(movingPiece - 1)])) {
                    positionsOfCoins[movingPiece] = desiredPosition;
                    moveComplete = true;
                }

                // The move is illegal because the desired position is illegal
                else {
                    System.out.println("Illegal move: Cannot move piece");
                    moveComplete = false;
                }
            }
            
            // The move is illegal because the piece is not one of the pieces on the board
            else {
                System.out.println("Illegal move: Unviable coin");
                moveComplete = false;
            }
        }
    }

    /**
     * Checks whether the game is finished by the distance between each coin
     */
    private void endGameChecker() {
        boolean endGameCoinDistance = false;
        for (int i = 1; i < positionsOfCoins.length; i++) {
            if ((positionsOfCoins[i] - positionsOfCoins[i-1]) == 1){
                endGameCoinDistance = true;
            }
            else {
                endGameCoinDistance = false;
                break;
            }
        }
        if ((positionsOfCoins[0]==1) && endGameCoinDistance) {
            gameOver = true;
        }
    }
        
}

