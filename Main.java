
import java.io.IOException;

import gameUtils.Game;
import utils.Title;


/**
 * main class for running the game
 *
 *  <p> used to initialize the game, title screen. </p>
 */

public class Main {
    public static void main(String[] args) throws IOException, Exception{


        //call the title class to print it to the commandline
        new Title();


        System.out.print("\u001b[2J\u001b[H");
        // initialize the game method and class and call game class method run
        Game game = new Game(15, 25);
        game.run();

        /*
         * #TODO
         *
         * - make better documentation
         *
         */


    }
}
