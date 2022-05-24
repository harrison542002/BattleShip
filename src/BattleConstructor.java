import java.util.ArrayList;
import java.util.Scanner;

public class BattleConstructor {
    private static int xrow = 10;
    private static int yrow = 10;
    private static ArrayList<String> playEnteredCoordinate = new ArrayList<String>(10);
    private static ArrayList<String> computerEnteredCoordinate = new ArrayList<String>(10);
    public static int playerShips;
    public static int computerShips;
    public static char[][] ocean = new char[yrow][xrow];
    private static char[][] computerDeployedShips = new char[yrow][xrow];

    static Scanner input = new Scanner(System.in);


    public static void displayBoard(){ //Build Game Board
        System.out.print("  ");

        for(int i = 0; i < ocean.length; i++ ){
            System.out.print(i);
        }
        System.out.println();

        //Start of nested loop
        for (int row = 0; row < ocean.length; row++) {
            for (int col = 0; col < ocean[row].length; col++) {

                if(col == 0){
                    System.out.print( row + "|" + ocean[row][col]);
                } else if (col == ocean[row].length-1) {
                    System.out.print( ocean[row][col] + "|" + row);
                } else {
                    System.out.print(ocean[row][col]);
                }
            }
            System.out.println();
        }//End of nested loop

        System.out.print("  ");
        for(int i = 0; i < ocean.length; i++ ){
            System.out.print(i);
        }
        System.out.println();
    }//End of Game Board

    public static void deployPlayerShips(){ //Deploy player ships
        System.out.println("Deploy your ships:");
        playerShips = 0;
        while(playerShips < 5){
            //Ask for ship positions
            System.out.print("Enter X coordinate for " + (playerShips+1) + " ship: ");
            int xCoordinate = input.nextInt();
            System.out.print("Enter Y coordinate for " + (playerShips+1) + " ship: ");
            int yCoordinate = input.nextInt();

            if((xCoordinate >= 0 && xCoordinate < xrow) && (yCoordinate >= 0 && yCoordinate < yrow) && ocean[yCoordinate][xCoordinate] != '@'){
                ocean[yCoordinate][xCoordinate] = '@';
                playerShips++;
            } else if (xCoordinate >= xrow || yCoordinate >= yrow || yCoordinate < 0 || xCoordinate < 0) {
                System.out.println("You can only place your ship in " + xrow + " by " +yrow + " grid.");
            } else if (ocean[yCoordinate][xCoordinate] == '@') {
                System.out.println("You can't place two ships at the same location.");
            }
        }
    }// End of Deployment

    public static void delopyComputerShips(){ //Deploy Computer ships
        System.out.println("Computer is delopying ships");
        computerShips = 0;
        while(computerShips < 5){

            int xCoordinate, yCoordinate;
            do {
                xCoordinate = (int)(Math.random()*xrow);
                yCoordinate = (int)(Math.random()*yrow);
            }while (ocean[yCoordinate][xCoordinate] != ' ');

            computerDeployedShips[yCoordinate][xCoordinate] = '^';
            System.out.println((computerShips+1) + "." + " ship DEPLOYED");
            computerShips++;
        }
        for (int i = 0; i < 30; i++) {
            System.out.print('-');
        }
        System.out.println();
    }// End of Deployment

    public static void playerTurn(){ //Start of player turn
        int xCoordinate, yCoordinate;
        System.out.println("YOUR TURN");
        do {
            System.out.print("Enter X coordinate :");
            xCoordinate = input.nextInt();
            System.out.print("Enter Y coordinate :");
            yCoordinate = input.nextInt();
            if(playEnteredCoordinate.contains(xCoordinate + " " + yCoordinate)){
                System.out.println("Note that only unduplicated value from range 0-9 is acceptable.");
            }
            //If the number is already entered by player or typed number is invalid, reprompt the statement for player to input.
        }while (xCoordinate < 0 || xCoordinate > ocean.length || yCoordinate < 0 || yCoordinate > ocean.length || playEnteredCoordinate.contains(xCoordinate + " " + yCoordinate));
        //Add new validated coordinated into entered-ArrayList.
        playEnteredCoordinate.add(xCoordinate + " " + yCoordinate);

        if(computerDeployedShips[yCoordinate][xCoordinate] == '^'){
            //If computer's ship get sunk, replace that index with "!"
            ocean[yCoordinate][xCoordinate] = '!';
            computerDeployedShips[yCoordinate][xCoordinate] = ' ';
            System.out.println("Boom! You sunk the ship!");
            computerShips--;
        } else if (ocean[yCoordinate][xCoordinate] == '@') {
            //If player's ship get sunk, replace that index with "x"
            ocean[yCoordinate][xCoordinate] = 'x';
            System.out.println("Oh no, you sunk your own ship :(");
            playerShips--;
        } else{
            //If no ship get sunk, replace that index with "-"
            ocean[yCoordinate][xCoordinate] = '-';
            System.out.println("Sorry, you missed");
        }
    }//End of player turn

    public static void computerTurn(){ //Start of computer turn
        int xCoordinate, yCoordinate;
        System.out.println("COMPUTER'S TURN");
        do {
            //If the number is already entered by computer, regenerate random number for coordinates.
            xCoordinate = (int)(Math.random()*xrow);
            yCoordinate = (int)(Math.random()*yrow);
        }while (computerEnteredCoordinate.contains(xCoordinate + " " + yCoordinate));
        //Add new validated coordinated into entered-ArrayList.
        computerEnteredCoordinate.add(xCoordinate + " " + yCoordinate);

        if(computerDeployedShips[yCoordinate][xCoordinate] == '^'){
            //If computer's ship get sunk, that index replace with "!"
            ocean[yCoordinate][xCoordinate] = '!';
            System.out.println("The Computer sunk one of its own ships");
            computerShips--;
        } else if (ocean[yCoordinate][xCoordinate] == '@') {
            //If player's ship get sunk, that index replace with "x"
            ocean[yCoordinate][xCoordinate] = 'x';
            System.out.println("The Computer sunk one of your ships!");
            playerShips--;
        } else{
            //If no ship get sunk, print the below statement.
            System.out.println("Computer missed");
        }
    }//End of computer turn
}
