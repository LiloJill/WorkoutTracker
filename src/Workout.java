public abstract class Workout {
    public long id;
    public int duration;
    public int distance;
    public long persId;

    public Workout(long id, int duration, int distance, long persId) {
        this.id = id;
        this.duration = duration;
        this.distance = distance;
        this.persId = persId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public long getPersId() {
        return persId;
    }

    public void setPersId(long persId) {
        this.persId = persId;
    }
}
