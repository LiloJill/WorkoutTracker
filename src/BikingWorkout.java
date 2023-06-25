import Enums.BikingType;

public class BikingWorkout extends Workout {

    public BikingType type;

    public BikingWorkout(long id, int duration, int distance, long persId, BikingType type) {
        super(id, duration, distance, persId);
        this.type = type;
    }

    public BikingType getBikingType() {
        return type;
    }
}
