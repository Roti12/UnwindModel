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

    public void AddAgents(Agent a) {
        this.agents.add(a);
    }
}
