import Enums.BikingType;
import Enums.SwimmingType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutTracker {

    public static void main(String[] args) throws FileNotFoundException {

        List<Workout> workoutList = new ArrayList<>();
        workoutList.add(new SwimmingWorkout(0, 67, 89, 0, SwimmingType.BACKSTROKE));
        workoutList.add(new SwimmingWorkout(1, 45, 110, 0, SwimmingType.BUTTERFLY));
        workoutList.add(new BikingWorkout(2, 30, 240, 0, BikingType.ROAD));
        workoutList.add(new BikingWorkout(3, 120, 97, 0, BikingType.MOUNTAIN));
        workoutList.add(new SwimmingWorkout(4, 55, 90, 0, SwimmingType.BUTTERFLY));

        workoutList.add(new BikingWorkout(5, 70, 300, 1, BikingType.MOUNTAIN));
        workoutList.add(new BikingWorkout(6, 25, 278, 1, BikingType.MOUNTAIN));
        workoutList.add(new BikingWorkout(7, 170, 106, 1, BikingType.ROAD));
        workoutList.add(new SwimmingWorkout(8, 89, 70, 1, SwimmingType.BUTTERFLY));
        workoutList.add(new SwimmingWorkout(9, 90, 68, 1, SwimmingType.BUTTERFLY));

        workoutList.add(new SwimmingWorkout(10, 100, 250, 2, SwimmingType.BUTTERFLY));
        workoutList.add(new SwimmingWorkout(11, 45, 80, 2, SwimmingType.BACKSTROKE));
        workoutList.add(new BikingWorkout(12, 50, 90, 2, BikingType.ROAD));
        workoutList.add(new BikingWorkout(13, 75, 130, 2, BikingType.MOUNTAIN));
        workoutList.add(new SwimmingWorkout(14, 32, 460, 2, SwimmingType.BUTTERFLY));

        FileUtils.writeStaticsToFile(FileUtils.readPersonsFromCsv(), workoutList);

    }
}
