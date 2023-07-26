package com.sayori.OOPS;
import java.util.*;
//class player with attributes of name and symbol that the player chooses
class Player {
    String name;
    char symbol;
}
public class TicTacToeGame {

    static void drawBoard (char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = Integer.toString(3 * i + j + 1).charAt(0);
            }
        }
    }
    static void displayBoard(char[][] b) {
        for (char[] row : b) {
            System.out.print("|---|---|---|\n| ");

            for (char c : row) {
                System.out.print(c+ " | ");
            }
            System.out.println();
        }
        System.out.println("|---|---|---|");
    }
    static void putSymbol (char[][] b, int n, char s) {

        b [(n-1)/3][(n-1)%3] = s;
    }
    static boolean isFilled (char[][] b, int n) {

        return (b[(n-1)/3][(n-1)%3] == 'X' || b [(n-1)/3][(n-1)%3] == 'O');
    }

    static boolean rowMatch(char[][] b, int n) {
        int col = (n - 1) % 3 ;
        return (b [0][col] == b[1][col] && b [0][col] == b[2][col]);
    }
    static boolean columnMatch(char[][] b, int n) {
        int row = (n - 1) / 3 ;
        return (b [row][0] == b[row][1] && b [row][0] == b[row][2]);
    }
    static boolean diagonalMatch (char[][] b) {
        return ((b[0][0]== b[1][1] && b[0][0] == b[2][2]) || (b[0][2]== b[1][1] && b[0][2] == b[2][0]));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //board of 3X3 cells
        char[][] board = new char[3][3];

        Player player1 = new Player();
        Player player2 = new Player();
        System.out.println("Press ENTER to Begin");
        while (true) {
            sc.nextLine();
            System.out.println("************ TIC - TAC - TOE GAME ************");
            System.out.print("Enter Name of Player 1 : ");
            player1.name = sc.nextLine();
            System.out.print("Enter Name of Player 2 : ");
            player2.name = sc.nextLine();
            Player first;
            Player second;
            double toss = Math.random();
            //since Math.random returns a float value between 0.0 and 1.0, for fair play we choose the point 0.5
            if (toss < 0.5) {
                System.out.println("It's " + player1.name + "'s turn first !\n");
                first = player1;
                second = player2;
            } else {
                System.out.println("It's " + player2.name + "'s turn first !");
                first = player2;
                second = player1;
            }
            char s; //variable for input
            System.out.println(first.name + ", enter your choice : X or O");
            s = sc.next().toUpperCase().charAt(0);
            if(s == 'X' || s == 'O') {
                first.symbol = s;
                if (s == 'X')
                    second.symbol = 'O';
                else
                    second.symbol = 'X';
                System.out.println (first.name + ", you have chosen : " + first.symbol);
                System.out.println (second.name +", your symbol is : " + second.symbol);
            }
            else {
                System.out.println("ERROR !!! Forbidden Symbol !!! Please Start Over");
                continue;
            }
            int count = 0, place; //tracking game progress
            Player current;
            drawBoard (board);
            while (count < 9) {
                if (count % 2 == 0)
                    current = first;
                else
                    current = second;
                System.out.println(current.name +", where do you want to put your symbol? " );
                place = sc.nextInt();
                if (place >= 1 && place <= 9 && ! isFilled(board, place)) {
                    putSymbol(board, place, current.symbol);
                    displayBoard(board);
                }
                else {
                    System.out.println("Not a valid place, try again !");
                    continue;
                }
                if (rowMatch(board, place) || columnMatch(board, place) || diagonalMatch(board)) {
                    System.out.println("****** CONGRATS!!!! " + current.name + " IS THE WINNER !!! ******");
                    break;
                }
                count++;
            }
            if (count == 9)
                System.out.println("Oh no ! It's a DRAW !! \nWELL PLAYED, BOTH OF YOU !!");
            System.out.println("Play Again? \nEnter 'Y' if yes, 'N' if no : ");
            char response = sc.next().toUpperCase().charAt(0);
            if (response != 'Y') {
                System.out.println ("Thank You for Playing Along!\nSee You Again :)");
                break;
            }
        }
    }
}