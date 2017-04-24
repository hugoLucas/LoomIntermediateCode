import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Using a Game object, this class will handle the output to the terminal as well as the user's input.
 *
 * Created by hugoj on 4/21/2017.
 */
class GameDriver {

    /* Game object to run */
    private Game baseGame;

    GameDriver(Game input){
        this.baseGame = input;
    }

    /**
     * Method continuously loops until the game provided to GameDriver is over. Game is over when current Game.Node
     * has no valid out-going links.
     */
    void start(){
        /* Reference to start node of Game object */
        Game.Node currentNode = baseGame.getStart();

        while(currentNode != null){
            /* Displays the nodes text prompt as well as possible options */
            System.out.print("\n" + baseGame.getNodeContents(currentNode));

            /* If the node has options, listen for user input. Else if this node points to another
             * section, print the divider to separate sections. Else, the end of the game has been reached. */
            String optionId = null;
            if(!currentNode.hasTransferLink() && currentNode.getNumberOfOptions() > 0) {
                int opNum = this.listenForResponse(baseGame.getCurrentNodeNumberOfOptions(currentNode));
                optionId = baseGame.trackHistory(currentNode, opNum);
            }else if(currentNode.hasTransferLink())
                System.out.println(DIVIDER + "\n" + DIVIDER + "\n");
            else
                System.out.println("END OF GAME");

            /* Finds the next node in the game if there is one */
            currentNode = baseGame.nextNode(currentNode, optionId);
        }
    }

    /**
     * Listens for user input, makes sure user input is a valid option.
     *
     * @param numOptions    the total amount of options this node has
     * @return              the number of the option selected by the user
     */
    private int listenForResponse(int numOptions){
        boolean validInput = false;
        int optionSelected = -1;

        Scanner input = new Scanner(System.in);
        while(!validInput){
            try {
                System.out.print("Select Option: ");
                optionSelected = input.nextInt();
                if(optionSelected <= numOptions)
                    validInput = true;
                else
                    System.out.println("Please enter an option from 1 to " + numOptions);
            }catch(InputMismatchException e){
                System.out.println("Please enter an option from 1 to " + numOptions);
                input = new Scanner(System.in);
            }
        }

        return optionSelected;
    }

    /* String constant used to divide chapters */
    private static final String DIVIDER = "---------------------------------------------------------------------------";
}
