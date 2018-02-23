import java.lang.reflect.Array;
import java.util.ArrayList;

public class State {

    private String name;
    private String atom;
    private ArrayList<AccessibilityRelation> relations = new ArrayList<>();

    public State(String name) {
        this.name = name;
    }

    public State(String name, ArrayList<AccessibilityRelation> relations) {
        this.name = name;
        addAccessibilityRelationsByList(relations);
    }

    public ArrayList<State> getRelationsStates() {
        ArrayList<State> relationsForState = new ArrayList<>();

        if(relations.isEmpty()) {
            //System.out.println("The state: " + this.getName() + " Has no accessibility relations");
            return null;
        }

        for(int i = 0; i < relations.size(); i++) {
            relationsForState.add(relations.get(i).getState());
        }

        return relationsForState;
    };

    public void addAccessibilityRelation(AccessibilityRelation relation) {
        relations.add(relation);
    }

    public void addAccessibilityRelationsByList(ArrayList<AccessibilityRelation> relation) {
        for(int i = 0; i < relation.size(); i++) {
            relations.add(relation.get(i));
        }
    }

    public String getName() {
        return name;
    }
}
