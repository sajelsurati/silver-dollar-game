import java.util.Random;
import java.util.Scanner;

public class PlayGame {

    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Enter the number of coins");
        int numberOfCoins = scan.nextInt();
        CoinStrip coinStrip = new CoinStrip(numberOfCoins);
        //int[] positions = coinStrip.generateBoard(numberOfCoins);
        coinStrip.takingTurns();
    }
}