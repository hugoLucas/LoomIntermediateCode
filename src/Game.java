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

    public String trackHistory(Node currentNode, int op){
        String optionId = currentNode.nThOption(op);
        this.currentGamePath.add(currentNode, optionId);
        return optionId;
    }

    public void connectSections(String nodeIntermediateIdentifier, String nodeSourceIntermediateIdentifier){
        gameNodes.get(nodeIntermediateIdentifier).connectToOtherSection(nodeSourceIntermediateIdentifier);
    }

    public void setStart(String id){
        this.setStartSection = id;
    }

    public Node getStart(){
        return this.getNodeByID(this.setStartSection);
    }

    public String getNodeContents(Node currentNode){
        return currentNode.getNodeTextPrompt() + "\n" +
                currentNode.getNodeOptions(currentGamePath) + "\n";
    }

    public int getCurrentNodeNumberOfOptions(Node currentNode){
        return currentNode.getNumberOfOptions();
    }

    public Node getNodeByID(String id){
        for(Node n: gameNodes.values())
            if(id.equals(n.getNodeIdentifier()))
                return n;
        return null;
    }

    public Node nextNode(Node currentNode, String optionSelected){
        if(optionSelected != null)
            return this.gameNodes.get(currentNode.traverseLink(optionSelected));
        return this.gameNodes.get(currentNode.getTransferLink());
    }

    public static class Node{
        private String nodeIdentifier;
        private String nodeTextPrompt;
        private String transferLink;

        /* <OptionIdentifier, OptionTextPrompt> */
        private HashMap<String, String> optionMap;

        /* <OptionIdentifier, NodeIdentifier> */
        private HashMap<String, String> nodeLinks;

        private HashMap<String, String> ifOption;

        public Node(String id, String text){
            this.nodeIdentifier = id;
            this.nodeTextPrompt = text;

            this.transferLink = null;
            this.optionMap = new HashMap<>();
            this.nodeLinks = new HashMap<>();
            this.ifOption = new HashMap<>();
        }

        public void addOption(String id, String text){
            this.optionMap.put(id, text);
            this.ifOption.put(id, null);
        }

        public void addIfOption(String id, String text, String conditionalOption, String conditionalOptionSource){
            this.addOption(id, text);
            this.ifOption.put(id, conditionalOption + "," + conditionalOptionSource);
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

        public String getNodeOptions(GameHistory currentGamePath){
            StringBuilder gen = new StringBuilder();

            int index = 1;
            for(String optionKey : this.optionMap.keySet()) {
                String optionText = this.optionMap.get(optionKey);
                String conditionals = this.ifOption.get(optionKey);
                if(conditionals == null) {
                    String header = "(" + index + ") ";
                    gen.append(header).append(optionText).append("\n");
                    index++;
                }else{
                    String args [] = conditionals.split(",");
                    if(currentGamePath.inHistory(args[1], args[0])){
                        String header = "(" + index + ") ";
                        gen.append(header).append(optionText).append("\n");
                        index++;
                    }
                }
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
                i++;
            }

            return null;
        }

        public boolean hasTransferLink(){
            return this.transferLink != null;
        }

        public String getTransferLink(){
            return this.transferLink;
        }
    }

    public static class GameHistory{
        private ArrayList<String> listOfNodeIdentifiers;
        private ArrayList<String> optionSelected;

        public GameHistory(){
            this.listOfNodeIdentifiers = new ArrayList<>();
            this.optionSelected = new ArrayList<>();
        }

        public void add(Node currentNode, String op){
            this.listOfNodeIdentifiers.add(currentNode.getNodeIdentifier());
            this.optionSelected.add(op);
        }

        public boolean inHistory(String id, String op){
            return this.listOfNodeIdentifiers.contains(id) && this.optionSelected.contains(op);
        }
    }
}
