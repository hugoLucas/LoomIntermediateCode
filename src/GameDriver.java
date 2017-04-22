import jdk.internal.util.xml.impl.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by hugoj on 4/21/2017.
 */
public class GameDriver {

    private Game baseGame;

    public GameDriver (Game input){
        this.baseGame = input;
    }

    public void start(){
        int stop = 10;
        String currentSection = baseGame.getStart();
        while(stop > 0){
            System.out.print(baseGame.getNodeContents(currentSection));
            int opNum = this.listenForResponse(baseGame.getCurrentNodeNumberOfOptions(currentSection));
            String opId = baseGame.trackHistory(currentSection, opNum);
            currentSection = baseGame.nextNode(currentSection, opId);
            stop --;
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
