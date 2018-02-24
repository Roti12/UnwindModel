import java.lang.reflect.Array;
import java.util.ArrayList;

public class State {

    private String name;
    private String atom;
    private ArrayList<AccessibilityRelation> relations = new ArrayList<>();
    private boolean label = false;

    public State(String name) {
        this.name = name;
    }

    public State(String name, String atom) {
        this.name = name;
        this.atom = atom;
    }

    public State(String name, ArrayList<AccessibilityRelation> relations) {
        this.name = name;
        addAccessibilityRelationsByList(relations);
    }

    public boolean hasRelationTo(State state) {
        ArrayList<State> relations = getRelationsStates();

        for(int i = 0; i < relations.size(); i++) {
            if(relations.get(i) == state) return true;
        }
        return false;
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

    public boolean checkAtomTrueInState(String string) {
        if(this.getName().toLowerCase().equals(string)) return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<AccessibilityRelation> getRelations() {
        return relations;
    }
}
