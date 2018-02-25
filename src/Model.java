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

    public boolean checkAgentRelationForStateByString(State state, String agent) {
        String agentName = agent.toLowerCase();
        if(state.getRelations().isEmpty()) return false;
        for (int i = 0; i < state.getRelationsStates().size(); i++) {
            if (state.getRelations().get(i).getAgent().getName().toLowerCase().equals(agentName)) return true;
        }

        return false;
    }

    public boolean checkAgentRelationForStateByObject(State state, Agent agent) {
        String agentName = agent.getName().toLowerCase();
        if((state.getRelationsStates() == null) || (state.getRelationsStates().isEmpty())) return false;

        for(int i = 0; i < state.getRelationsStates().size(); i++) {
            if(state.getRelations().get(i).getAgent().getName().toLowerCase().equals(agentName)) return true;
        }

        return false;
    }

    public Agent findAgentInModel(String string) {
        String agentSearch = string.toLowerCase();

        for(int i = 0; i < agents.size(); i++) {
            if(agents.get(i).getName().toLowerCase().equals(agentSearch)) return agents.get(i);
        }
        return null;
    }
}
