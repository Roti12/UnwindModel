import java.lang.reflect.Array;
import java.util.ArrayList;

public class ModelCreator {


    private Model model;
    /*
    IMPORTANT NOTE!!!!
    MAYBE JUST VALIDATE FORMULAS IN BASIC EPISTEMIC MODELS???
     */
    public ModelCreator(Model model) {
        this.model = model;

        Agent a = new Agent("Ann");
        Agent b = new Agent("Bill");

        model.addAgents(a);
        model.addAgents(b);

        State state0 = new State("state-0", "p");
        State state1 = new State("state-1");
        State state2 = new State("state-2");

        ArrayList<AccessibilityRelation> relations = new ArrayList<>();

        AccessibilityRelation rel0 = new AccessibilityRelation(state0,a);
        AccessibilityRelation rel1 = new AccessibilityRelation(state1, b);
        AccessibilityRelation rel2 = new AccessibilityRelation(state2, a);
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

    public boolean checkAgentRelationForState(State state, String string) {
        String agentName = string.toLowerCase();
        for(int i = 0; i < state.getRelationsStates().size(); i++) {
            if(state.getRelations().get(i).getAgent().getName().equals(agentName)) return true;
        }

        return false;
    }

    public boolean checkModelForFormula(String string) {
        return false;
    }

    public static void main(String [] args) {
        Model model = new Model("Model-1");
        ModelCreator createModel = new ModelCreator(model);
        createModel.unwind();
        String str = "ANN KNOWS";
        System.out.println(str.substring(0, str.length() - 6));
    }
}
