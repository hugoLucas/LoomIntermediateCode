import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    /* String = "node_n" where n is the number of the node, identifier in intermediate code
    *  Node = reference to node that has this identifier to intermediate code */
    private HashMap<String, Node> gameNodes;

    /* A history of nodes traversed thus far */
    private HashMap<String, String> currentGamePath;

    public Game(){
        gameNodes = new HashMap<>();
    }

    public void addNodesToGame(Node newNode, String nodeIdentifier){
        gameNodes.put(nodeIdentifier, newNode);
    }

    public void addLinkToGame(String nodeIntermediateIdentifier, String nodeOptionIdentifier,
                              String nodeSourceIntermediateIdentifier){
        gameNodes.get(nodeIntermediateIdentifier).addLink(nodeOptionIdentifier, nodeSourceIntermediateIdentifier);
    }

    public static class Node{
        private String nodeIdentifier;
        private String nodeTextPrompt;

        /* <OptionIdentifier, OptionTextPrompt> */
        private HashMap<String, String> optionMap;

        /* <OptionIdentifier, NodeIdentifier> */
        private HashMap<String, String> nodeLinks;

        public Node(String id, String text){
            this.nodeIdentifier = id;
            this.nodeTextPrompt = text;

            this.optionMap = new HashMap<>();
        }

        public void addOption(String id, String text){
            this.optionMap.put(id, text);
        }

        public void addLink(String opId, String nodeId){
            this.nodeLinks.put(opId, nodeId);
        }
    }
}
