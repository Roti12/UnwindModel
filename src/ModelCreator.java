import java.lang.reflect.Array;
import java.util.ArrayList;

public class ModelCreator {


    private Model model;

    public ModelCreator(Model model) {
        this.model = model;
        State state0 = new State("state-0");
        State state1 = new State("state-1");
        State state2 = new State("state-2");

        ArrayList<AccessibilityRelation> relations = new ArrayList<>();

        AccessibilityRelation rel0 = new AccessibilityRelation(state0);
        AccessibilityRelation rel1 = new AccessibilityRelation(state1);
        AccessibilityRelation rel2 = new AccessibilityRelation(state2);
        relations.add(rel0);
        relations.add(rel1);
        relations.add(rel2);

        state0.addAccessibilityRelationsByList(relations);

        model.addState(state0);
        model.addState(state1);
        model.addState(state2);


}

    public void unwind() {
        ArrayList<State> unwindedModel = model.getStatesInModel();

        if(unwindedModel.isEmpty()) System.out.println("Model has no states");

        for(int i = 0; i < 10; i++) {
            ArrayList<State> relations = unwindedModel.get(i).getRelationsStates();
            if(relations != null) {
                for(int j = 0; j < relations.size(); j++){
                    unwindedModel.add(relations.get(j));
                }
            }
        }

        for(int i = 0; i < unwindedModel.size(); i++) {
            System.out.println(unwindedModel.get(i).getName());
        }

    }

    public boolean checkModelForFormula() {
        return false;
    }

    public static void main(String [] args) {
        Model model = new Model("Model-1");
        ModelCreator createModel = new ModelCreator(model);
        createModel.unwind();
    }
}
