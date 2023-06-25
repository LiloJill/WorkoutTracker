import Enums.SwimmingType;

public class SwimmingWorkout  extends  Workout {

    public SwimmingType type;

    public SwimmingWorkout(long id, int duration, int distance, long persId, SwimmingType type) {
        super(id, duration, distance, persId);
        this.type = type;
    }

    public SwimmingType getSwimmingType() {
        return type;
    }
}
