import jdk.internal.util.xml.impl.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by hugoj on 4/21/2017.
 */
public class GameDriver {

    private Game baseGame;
    private Game.Node testNode;

    private static final String DIVIDER = "---------------------------------------------------------------------------";

    public GameDriver (Game input, Game.Node testNode){
        this.baseGame = input;
        this.testNode = testNode;
    }

    public void start(){
        int stop = 10;

        Game.Node currentNode = baseGame.getStart();
        // Game.Node currentNode = this.testNode;
        while(currentNode != null){
            System.out.print(baseGame.getNodeContents(currentNode));

            String optionId = null;
            if(!currentNode.hasTransferLink() && currentNode.getNumberOfOptions() > 0) {
                int opNum = this.listenForResponse(baseGame.getCurrentNodeNumberOfOptions(currentNode));
                optionId = baseGame.trackHistory(currentNode, opNum);
            }else if(currentNode.hasTransferLink())
                System.out.println(DIVIDER + "\n" + DIVIDER + "\n");
            else
                System.out.println("END OF GAME");

            currentNode = baseGame.nextNode(currentNode, optionId);
        }
    }

    public int listenForResponse(int numOptions){
        boolean validInput = false;
        int optionSelected = -1;

        Scanner input = new Scanner(System.in);
        while(!validInput){
            try {
                System.out.print("Select Option: ");
                optionSelected = input.nextInt();
                validInput = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an option from 1 to " + numOptions);
                input = new Scanner(System.in);
            }
        }

        return optionSelected;
    }
}
