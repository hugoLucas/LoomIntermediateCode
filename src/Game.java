import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    /* String = "node_n" where n is the number of the node, identifier in intermediate code
    *  Node = reference to node that has this identifier to intermediate code */
    private HashMap<String, Node> gameNodes;

    /* A history of nodes traversed thus far */
    private GameHistory currentGamePath;

    private String setStartSection;

    public Game(){
        gameNodes = new HashMap<>();
        currentGamePath = new GameHistory();
    }

    public void addNodesToGame(Node newNode, String nodeIdentifier){
        gameNodes.put(nodeIdentifier, newNode);
    }

    public void addLinkToGame(String nodeIntermediateIdentifier, String nodeOptionIdentifier,
                              String nodeSourceIntermediateIdentifier){
        gameNodes.get(nodeIntermediateIdentifier).addLink(nodeOptionIdentifier, nodeSourceIntermediateIdentifier);
    }

    public String trackHistory(String id, int op){
        String optionId = this.getNodeByID(id).nThOption(op);
        this.currentGamePath.add(id, optionId);
        return optionId;
    }

    public void connectSections(String nodeIntermediateIdentifier, String nodeSourceIntermediateIdentifier){
        gameNodes.get(nodeIntermediateIdentifier).connectToOtherSection(nodeSourceIntermediateIdentifier);
    }

    public void setStart(String id){
        this.setStartSection = id;
    }

    public String getStart(){
        return this.setStartSection;
    }

    public String getNodeContents(String nodeName){
        Node currentNode = this.getNodeByID(nodeName);
        return currentNode.getNodeTextPrompt() + "\n" +
                currentNode.getNodeOptions() + "\n";
    }

    public int getCurrentNodeNumberOfOptions(String nodeName){
        return this.getNodeByID(nodeName).getNumberOfOptions();
    }

    public Node getNodeByID(String id){
        for(Node n: gameNodes.values())
            if(id.equals(n.getNodeIdentifier()))
                return n;
        return null;
    }

    public String nextNode(String nodeName, String optionSelected){
        Node currentNode = this.getNodeByID(nodeName);
        return this.gameNodes.get(currentNode.traverseLink(optionSelected)).getNodeIdentifier();
    }

    public static class Node{
        private String nodeIdentifier;
        private String nodeTextPrompt;
        private String transferLink;

        /* <OptionIdentifier, OptionTextPrompt> */
        private HashMap<String, String> optionMap;

        /* <OptionIdentifier, NodeIdentifier> */
        private HashMap<String, String> nodeLinks;

        public Node(String id, String text){
            this.nodeIdentifier = id;
            this.nodeTextPrompt = text;

            this.transferLink = null;
            this.optionMap = new HashMap<>();
            this.nodeLinks = new HashMap<>();
        }

        public void addOption(String id, String text){
            this.optionMap.put(id, text);
        }

        public void addLink(String opId, String nodeId){
            this.nodeLinks.put(opId, nodeId);
        }

        public String traverseLink(String opId){
            return this.nodeLinks.get(opId);
        }

        public void connectToOtherSection(String sectionStart){
            this.transferLink = sectionStart;
        }

        public String getNodeTextPrompt(){
            return this.nodeTextPrompt;
        }

        public String getNodeOptions(){
            StringBuilder gen = new StringBuilder();

            int index = 1;
            for(String optionText : this.optionMap.values()) {
                String header = "(" + index + ") ";
                gen.append(header).append(optionText).append("\n");
                index ++;
            }

            return gen.toString();
        }

        public String getNodeIdentifier(){
            return this.nodeIdentifier;
        }

        public int getNumberOfOptions(){
            return this.optionMap.size();
        }

        public String nThOption(int n){
            int i = 1;
            for(String key: this.optionMap.keySet()){
                if(i == n)
                    return key;
            }

            return null;
        }
    }

    public static class GameHistory{
        private ArrayList<String> listOfNodeIdentifiers;
        private ArrayList<String> optionSelected;

        public GameHistory(){
            this.listOfNodeIdentifiers = new ArrayList<>();
            this.optionSelected = new ArrayList<>();
        }

        public void add(String id, String op){
            this.listOfNodeIdentifiers.add(id);
            this.optionSelected.add(op);
        }
    }
}
