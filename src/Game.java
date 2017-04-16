import java.util.ArrayList;
import java.util.HashMap;

public class Game {



    public class Node{
        private String nodeIdentifier;
        private String nodeTextPrompt;

        private HashMap<String, String> optionMap;
        private ArrayList<Node> nodeLinks;

        public Node(String id, String text){
            this.nodeIdentifier = id;
            this.nodeTextPrompt = text;

            this.optionMap = new HashMap<>();
            this.nodeLinks = new ArrayList<>();
        }

        public void addOption(String id, String text){
            this.optionMap.put(id, text);
        }
    }
}
