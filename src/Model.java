import java.lang.reflect.Array;
import java.util.ArrayList;

public class Model {

    private int numberOfStates = 0;
    private String name;
    private ArrayList<State> statesInModel = new ArrayList<>();
    private ArrayList<Agent> agents = new ArrayList<>();

    public Model(String name) {
        this.name = name;
    }

    public void addState(State state){
        statesInModel.add(state);
    };

    public int getNumberOfStates() {
        return statesInModel.size();
    }

    public ArrayList<State> getStatesInModel() {
        return statesInModel;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void addAgents(Agent a) {
        this.agents.add(a);
    }

    public ArrayList<State> checkAgentRelationForStateByString(State state, String agent) {
        ArrayList<State> statesWithAgentRelations = new ArrayList<>();
        Agent a = findAgentInModel(agent);
        if(a == null) return null;
        if((state.getRelationsStates() == null) || (state.getRelationsStates().isEmpty())) return null;
        String agentName = a.getName().toLowerCase();

        for(int i = 0; i < state.getRelations().size(); i++) {
            if(state.getRelations().get(i).getAgent().getName().toLowerCase().equals(agentName)) {
                statesWithAgentRelations.add(state.getRelations().get(i).getState());
            }
        }
        return statesWithAgentRelations;
    }

    public ArrayList<State> checkAgentRelationForStateByObject(State stateToCheck, Agent agent) {
        String agentName = agent.getName().toLowerCase();
        ArrayList<State> statesWithAgentRelations = new ArrayList<>();
        if((stateToCheck.getRelationsStates() == null) || (stateToCheck.getRelationsStates().isEmpty())) return null;

        for(int i = 0; i < stateToCheck.getRelations().size(); i++) {
            if(stateToCheck.getRelations().get(i).getAgent().getName().toLowerCase().equals(agentName)){
                statesWithAgentRelations.add(stateToCheck.getRelations().get(i).getState());
            }
        }

        return statesWithAgentRelations;
    }

    public Agent findAgentInModel(String string) {
        String agentSearch = string.toLowerCase();

        for(int i = 0; i < agents.size(); i++) {
            if(agents.get(i).getName().toLowerCase().equals(agentSearch)) return agents.get(i);
        }
        return null;
    }
}
