import java.util.Random;

public class AccessibilityRelation {

    private State state;
    private long id;

    public AccessibilityRelation(State state) {
        this.state = state;
        this.id = new Random().nextLong();
    }

    public State getState() {
        return state;
    }
}
