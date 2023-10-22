/**
 * A file that plays rock paper scissors. Contains a main method that lets the user play
 * against the computer a set number of times.
 * Name:
 * ID:
 * Email:
 * Sources used: Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * 2-4 sentence file description here
 */

import java.util.Scanner;

/**
 * Class that handles some RPS methods and has a main method to play the game with.
 */
public class RPS extends RPSAbstract {
    
    /**
     * Constructs a RPS game based on a input string array of possible moves
     * and a default number of maximum games
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove)
    {
        int playerMoveIndex = -2;
        for(int i = 0; i < this.possibleMoves.length; i++)
        {
            if(playerMove.equalsIgnoreCase(this.possibleMoves[i]))
            {
                playerMoveIndex = i;
                break;
            }
        }
        int cpuMoveIndex = -2;
        for(int i = 0; i < this.possibleMoves.length; i++)
        {
            if(cpuMove.equalsIgnoreCase(this.possibleMoves[i]))
            {
                cpuMoveIndex = i;
                break;
            }
        }
        if(playerMoveIndex == -2 || cpuMoveIndex == -2)
        {
            return INVALID_INPUT_OUTCOME;
        }
        if((playerMoveIndex == this.possibleMoves.length - 1 && cpuMoveIndex == 0)
            || playerMoveIndex == cpuMoveIndex - 1)
        {
            return PLAYER_WIN_OUTCOME;
        }
        if((cpuMoveIndex == this.possibleMoves.length - 1 && playerMoveIndex == 0)
            || cpuMoveIndex == playerMoveIndex - 1)
        {
            return CPU_WIN_OUTCOME;
        }
                
        return TIE_OUTCOME;
    }

    /**
     * Main method that reads user input, generates cpu move, and plays game
     * 
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game
        System.out.println(PROMPT_MOVE);
        String input = in.nextLine();
        while(!input.equalsIgnoreCase("q"))
        {
            game.play(input, game.genCPUMove());
            System.out.println(PROMPT_MOVE);
            input = in.nextLine();
        }
        game.end();
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written 
        // to do most of the work!


        in.close();
    }
}
