
import java.io.IOException;

import gameUtils.Game;
import utils.Title;


public class Main {
    public static void main(String[] args) throws IOException, Exception{

        System.out.print("\u001b[2J\u001b[H");
        new Title();


        System.out.print("\u001b[2J\u001b[H");

        Game game = new Game(15, 25);

        game.run();




        //game.Render();

        /*
         * #TODO
         *
         * - make documentation
         *
         */


    }
}
