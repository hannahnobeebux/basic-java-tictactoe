import java.util.*;

public class Main {
//    DEFINING GLOBAL VARIABLES

//    THIS WILL CREATE A NEW ARRAY LIST THAT HOLDS INTEGERS
//    <Integer> IS THE TYPE PARAMETER - SPECIFIES THE DATA TYPE OF ELEMENTS HELD IN THIS LIST
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args) {

//        MAKING THE GAME BOARD USING A 2D ARRAY OF CHARACTERS (Using Char data type)
//        FIRST WE DEFINE THE ROWS AND INSIDE EACH ROW, WE HAVE A COLUMN - NEED 5 COLUMNS INCLUDING THE SYMBOLS TO PLAY
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };
        printGameBoard(gameBoard);


//        KEEP ASKING THE USER TO ENTER A NUMBER, LET THE COMPUTER PLAY UNTIL NO SPACES LEFT
        while (true) {
//        ASSIGNING A NUMBER TO EACH SQUARE SO IT CAN BE REFERENCED
//        ASKING THE USER FOR A NUMBER 1-9
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9): ");
            int playerPosition = scan.nextInt();
//            CHECKING THAT THE USER ISNT INPUTTING A PLACE THE COMPUTER HAS CHOSEN/IS TAKEN
            while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPositions)) {
                System.out.println("Position taken! Enter a correct position: ");
                playerPosition = scan.nextInt();
            }

            placePiece(gameBoard, playerPosition, "player");
//            CHECK THE RESULT AFTER PLAYER PUTS A PIECE DOWN TOO
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

//        GENERATING A RANDOM NUMBER WHICH WILL BE THE POSITION FOR THE CPU
            Random rand = new Random();

            int cpuPosition = rand.nextInt(9) + 1;
//            CHECKING THAT THE COMPUTER ISNT INPUTTING A PLACE THE USER HAS CHOSEN/IS TAKEN
            while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
//                System.out.println("Position taken! Enter a correct position: ");
                playerPosition = scan.nextInt();
                cpuPosition = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPosition, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

        }

    }

    public static void printGameBoard(char[][] gameBoard) {
//        USING A FOR LOOP TO OUTPUT THE GAME BOARD IN THE CONSOLE
//        OUTER LOOP - FOR EACH ROW INSIDE THE GAME BOARD
//        INNER LOOP - FOR EACH SYMBOL INSIDE EACH ROW
        for(char[] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

//    PASSING IN THE BOARD, POSITION CHOSEN BY THE USER, AND WHICH USER (PERSON OR COMPUTER)
    public static void placePiece(char[][] gameBoard, int position, String user) {
        char symbol = ' ';
        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }
//        THIS WILL PLACE THE USER'S TURN ON THEIR CHOSEN PLACEMENT
        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

//    CHECKING THE SYMBOLS TO SEE WHO HAS WON AND RETURN WINNING STATEMENT
    public static String checkWinner() {
//        WINNING COMBINATIONS - ROWS
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);

//        WINNING COMBINATIONS - COLUMNS
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

//        WINNING COMBINATIONS - DIAGONALS
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

//        CREATING A LIST OF ALL THE LISTS WE HAVE SPECIFYING THE WINNING COMBINATIONS
        List<List> winningCombinations = new ArrayList<List>();

        winningCombinations.add(topRow);
        winningCombinations.add(midRow);
        winningCombinations.add(bottomRow);
        winningCombinations.add(leftCol);
        winningCombinations.add(midCol);
        winningCombinations.add(rightCol);
        winningCombinations.add(cross1);
        winningCombinations.add(cross2);

//        OUTER LOOP - FOR EACH LIST INSIDE OF OUR LIST "winningCombinations"
//        CHECKING WHETHER EITHER THE USER OR THE COMPUTER HAS ACHIEVED ONE OF THE COMBINATIONS FIRST
        for(List l : winningCombinations) {
            if (playerPositions.containsAll(l)) {
                return "Congratulations, you won!";
            } else if (cpuPositions.containsAll(l)){
                return "The computer wins! Sorry :(";
            } else if (playerPositions.size() + cpuPositions.size() == 9){
                return "TIE!";
            }
        }
        return "";
    }

}