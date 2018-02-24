import java.util.Random;

public class AccessibilityRelation {

    private State state;
    private long id;
    private Agent agent;

    public AccessibilityRelation(State state, Agent agent) {
        this.state = state;
        this.id = new Random().nextLong();
        this.agent = agent;
    }

    public State getState() {
        return state;
    }

    public Agent getAgent() {
        return agent;
    }
}
