import java.util.ArrayList;

public class ModelCreator {


    private static Model model;

    public ModelCreator(Model model) {
        this.model = model;

        Agent a = new Agent("Ann");
        Agent b = new Agent("Bill");

        model.addAgents(a);
        model.addAgents(b);

        State state0 = new State("state-0", "p");
        State state1 = new State("state-1", "p");
        State state2 = new State("state-2", "q");

        ArrayList<AccessibilityRelation> relations = new ArrayList<>();

        AccessibilityRelation rel0 = new AccessibilityRelation(state0,a);
        AccessibilityRelation rel1 = new AccessibilityRelation(state1, b);
        AccessibilityRelation rel2 = new AccessibilityRelation(state2, a);
        AccessibilityRelation rel3 = new AccessibilityRelation(state0, b);
        AccessibilityRelation rel4 = new AccessibilityRelation(state1, a);

        relations.add(rel0);
        relations.add(rel1);
        relations.add(rel2);
        relations.add(rel3);
        relations.add(rel4);

        state0.addAccessibilityRelationsByList(relations);

        model.addState(state0);
        model.addState(state1);
        model.addState(state2);


}

    public void unwind() {
        ArrayList<State> unwindedModel = model.getStatesInModel();

        if(unwindedModel.isEmpty()) System.out.println("Model has no states");

        System.out.println("Updated model: ");
        System.out.println();

        for(int i = 0; i < 100; i++) {
            ArrayList<State> relations = unwindedModel.get(i).getRelationsStates();
            if(relations != null) {
                for(int j = 0; j < relations.size(); j++){
                    unwindedModel.add(relations.get(j));
                }
            }
        }

        for(int i = 0; i < unwindedModel.size(); i++) {
            System.out.println(unwindedModel.get(i).getName() + " number: " + i);
        }

    }

    public static boolean checkModelForFormula(State state, String string, boolean negation) {
        String formula = string.toLowerCase();

        if(state == null) return false;
        if(formula == null || formula.equals("")) return false;

        //Single atoms only
        if(!((formula.contains("or")) || (formula.contains("and")) || (formula.contains("knows")) ||(formula.contains("considers")))) {
            if(negation) return !(state.checkAtomTrueInState(string));
            return state.checkAtomTrueInState(string);
        }
        //ATOMS SEPERATED BY OR
        if(formula.contains("or")) {
            String leftAtom = formula.substring(0,1);
            String rightAtom = formula.substring(formula.length()-1);
            if(negation) {
                return !((state.checkAtomTrueInState(leftAtom)) || (state.checkAtomTrueInState(rightAtom)));
            }
            if((state.checkAtomTrueInState(leftAtom)) || (state.checkAtomTrueInState(rightAtom)))return true;

            return false;
        }
        //ATOMS SEPERATED BY AND
        if(formula.contains("and")) {
            String leftAtom = formula.substring(0,1);
            String rightAtom = formula.substring(formula.length() - 1);
            if(negation) {
                return ((!state.checkAtomTrueInState(leftAtom)) && !(state.checkAtomTrueInState(rightAtom)));
            }
            if((state.checkAtomTrueInState(leftAtom)) && (state.checkAtomTrueInState(rightAtom))) return true;

            return false;
        }

        if(formula.contains("implies")) {
            String leftAtom = formula.substring(0, 1);
            String rightAtom = formula.substring(formula.length() - 1);
            if(negation) {
                if((state.checkAtomTrueInState(leftAtom)) && !(state.checkAtomTrueInState(rightAtom))) return true;
            } else return false;
            if((state.checkAtomTrueInState(leftAtom)) && !(state.checkAtomTrueInState(rightAtom))) return false;
            return true;
        }

        //ANN KNOWS SOMETHING
        if(formula.contains("knows")) {
            String agent = formula.substring(0, formula.length() - 8);
            String atom = formula.substring(string.length() - 1);
            if(negation) return !checkAgentKnowledge(agent, state, atom);
            return checkAgentKnowledge(agent, state, atom);
        }

        //ANN CONSIDERS POSSIBLE
        if(formula.contains("considers")) {
            String agent = formula.substring(0, formula.length() - 12);
            String atom = formula.substring(string.length() - 1);
            if(negation) return !checkAgentConsidersPossibility(agent,state,atom);
            return checkAgentConsidersPossibility(agent, state, atom);
        }
        return false;
    }

    public static boolean checkAgentKnowledge(String agent, State state, String atom) {
        Agent a = model.findAgentInModel(agent);
        if(a == null) return false;
        ArrayList<State> statesToCheckForAgent = statesToCheckForAgents(state, a);

        for(int i = 0; i < statesToCheckForAgent.size(); i++) {
            if(!(statesToCheckForAgent.get(i).checkAtomTrueInState(atom))) return false;
        }

        for(int j = 0; j < statesToCheckForAgent.size(); j++) {
            System.out.println("STATES TO CHECK");
            System.out.println(statesToCheckForAgent.get(j).getName());
        }

        return true;
    }

    public static boolean checkAgentConsidersPossibility(String agent, State state, String atom) {
        Agent a = model.findAgentInModel(agent);
        if (a == null) return false;

        ArrayList<State> statesToCheckForAgent = statesToCheckForAgents(state, a);

        for(int i = 0; i < statesToCheckForAgent.size(); i++) {
            if(statesToCheckForAgent.get(i).checkAtomTrueInState(atom)) return true;
        }

        return false;
    }

    public static ArrayList<State> statesToCheckForAgents(State state, Agent agent) {
        ArrayList<State> statesToCheckForAgent = model.checkAgentRelationForStateByObject(state, agent);

        return statesToCheckForAgent;
    }

    public static void main(String [] args) {
        Model model = new Model("Model-1");
        ModelCreator createModel = new ModelCreator(model);
        createModel.unwind();
        //System.out.println(checkModelForFormula(model.getStatesInModel().get(0), "bill knows p", false));
        //System.out.println(checkModelForFormula(model.getStatesInModel().get(0), "p or q", true));
        //System.out.println(checkModelForFormula(model.getStatesInModel().get(0), "ann considers q", false));
        //System.out.println(model.getStatesInModel().get(1).getRelations());
    }
}
