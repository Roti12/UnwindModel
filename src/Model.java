import java.util.ArrayList;

public class Model {

    private int numberOfStates = 0;
    private String name;
    private ArrayList<State> statesInModel = new ArrayList<>();

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
}
