/**
 * Created by Hugo on 4/10/2017.
 */
public class Main {
    public static void main(String [] args){
        Game currentGame = new Game();


        Game.Node node_0 = new Game.Node("$pg_five","you pack your wagon");
        node_0.addOption("$e", "head east");
        currentGame.addNodesToGame(node_0, "node_0");
        Game.Node node_1 = new Game.Node("$pg_one","you are in a library");
        node_1.addOption("$a", "do nothing");
        node_1.addOption("$b", "check pockets");
        currentGame.addNodesToGame(node_1, "node_1");
        Game.Node node_2 = new Game.Node("$pg_four","the library catches on fire");
        currentGame.addNodesToGame(node_2, "node_2");
        Game.Node node_3 = new Game.Node("$pg_six","you have caught dysentery");
        currentGame.addNodesToGame(node_3, "node_3");
        Game.Node node_4 = new Game.Node("$pg_two","there is a sound in the distance");
        node_4.addOption("$c", "head towards the sound");
        currentGame.addNodesToGame(node_4, "node_4");
        Game.Node node_5 = new Game.Node("$pg_three","you find a digital watch");
        node_5.addOption("$d", "check the time");
        currentGame.addNodesToGame(node_5, "node_5");
        currentGame.addLinkToGame("node_0", "$e", "node_3");
        currentGame.addLinkToGame("node_1", "$a", "node_4");
        currentGame.addLinkToGame("node_1", "$b", "node_5");
        currentGame.addLinkToGame("node_4", "$c", "node_2");
        currentGame.addLinkToGame("node_5", "$d", "node_2");

    }
}
