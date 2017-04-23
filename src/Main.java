/**
 * Created by Hugo on 4/10/2017.
 */
public class Main {
    public static void main(String [] args){
        Game currentGame = new Game();

        /**********************************************************************************************************
         ***********************************Intermediate Code Goes Here********************************************
         **********************************************************************************************************/

        Game.Node node_0 = new Game.Node("$escape_zero_ops","The key opens the lock. You are free and begin to walk");
        currentGame.addNodesToGame(node_0, "node_0");

        Game.Node node_1 = new Game.Node("$response_one_ops","You yell for help and hear your voice echo in the room. Wherever you are, it's large.");
        node_1.addOption("$a_response", "Follow the chain.");
        currentGame.addNodesToGame(node_1, "node_1");

        Game.Node node_2 = new Game.Node("$discovery_one_ops","You find none of your possessions. However, your back pocket contains a key.");
        node_2.addOption("$a_discovery", "Try to stand up.");
        currentGame.addNodesToGame(node_2, "node_2");

        Game.Node node_3 = new Game.Node("$chained_two_ops","While trying to stand up you realize your ankle has a manacle on it.");
        node_3.addOption("$a_chained", "Yell for help.");
        node_3.addOption("$b_chained", "Follow the chain.");
        currentGame.addNodesToGame(node_3, "node_3");

        Game.Node node_4 = new Game.Node("$woops_zero_ops","It turns out this was all a dream. :)");
        currentGame.addNodesToGame(node_4, "node_4");

        Game.Node node_5 = new Game.Node("$failure_one_ops","You cannot break the lock.");
        node_5.addIfOption("$a_failure", "Try out your key.", "$a_discovery", "$discovery_one_ops");
        currentGame.addNodesToGame(node_5, "node_5");

        Game.Node node_6 = new Game.Node("$door_two_ops","The chain ends at a wall. An old rusty lock is all that keeps in connect to the wall.");
        node_6.addOption("$a_door", "Try to break the lock.");
        node_6.addIfOption("$b_door", "Try out your key.", "$a_discovery", "$discovery_one_ops");
        currentGame.addNodesToGame(node_6, "node_6");

        Game.Node node_7 = new Game.Node("$salvation_one_ops","While stumbling around in dark you find a door.");
        node_7.addOption("$a_salvation", "Open the door.");
        currentGame.addNodesToGame(node_7, "node_7");

        Game.Node node_8 = new Game.Node("$death_zero_ops","With no way to break or open the lock, you eventually die alone in the darkness.");
        currentGame.addNodesToGame(node_8, "node_8");

        Game.Node node_9 = new Game.Node("$awaken_two_ops","You wake up on the floor of a dark room.");
        node_9.addOption("$a_awaken", "Try to stand up.");
        node_9.addOption("$b_awaken", "Check your pockets.");
        currentGame.addNodesToGame(node_9, "node_9");

        currentGame.addLinkToGame("node_7", "$a_salvation", "node_4");
        currentGame.addLinkToGame("node_9", "$a_awaken", "node_3");
        currentGame.addLinkToGame("node_9", "$b_awaken", "node_2");
        currentGame.addLinkToGame("node_2", "$a_discovery", "node_3");
        currentGame.addLinkToGame("node_3", "$a_chained", "node_1");
        currentGame.addLinkToGame("node_3", "$b_chained", "node_6");
        currentGame.addLinkToGame("node_1", "$a_response", "node_6");
        currentGame.addLinkToGame("node_6", "$a_door", "node_5");
        currentGame.addLinkToGame("node_6", "$b_door", "node_0");
        currentGame.addLinkToGame("node_5", "$a_failure", "node_8");
        currentGame.connectSections("node_0", "node_7");
        currentGame.setStart("$awaken_two_ops");

        /**********************************************************************************************************
         ***********************************Intermediate Code Goes Here********************************************
         **********************************************************************************************************/

        GameDriver driver = new GameDriver(currentGame, node_6);

        driver.start();
    }
}
