import java.util.*;
public class BattleShip {

    //Main function
    public static void main(String[] args) {
        for (int row = 0; row < BattleConstructor.ocean.length; row++) {
            for (int col = 0; col < BattleConstructor.ocean[row].length; col++) {
                BattleConstructor.ocean[row][col] = ' ';
            }
        }
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, the sea is empty");
        BattleConstructor.displayBoard();
        BattleConstructor.deployPlayerShips();
        BattleConstructor.displayBoard();
        BattleConstructor.delopyComputerShips();

        while (BattleConstructor.playerShips > 0 && BattleConstructor.computerShips > 0) {
            BattleConstructor.playerTurn();
            System.out.println();
            BattleConstructor.computerTurn();
            System.out.println();
            BattleConstructor.displayBoard();
            System.out.println();
            System.out.print("Your ships : " + BattleConstructor.playerShips + " | " + "Computer ships : " + BattleConstructor.computerShips);
            System.out.println();
            for (int i = 0; i < 30; i++) {
                System.out.print('-');
            }
            System.out.println();
        }
        if(BattleConstructor.playerShips == 0){
            System.out.println("Nice Try but Defeat");
        } else if (BattleConstructor.computerShips == 0) {
            System.out.println("Your Win!");
        }
    }
}


