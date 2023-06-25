import Enums.BikingType;
import Enums.SwimmingType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {

    private static final String PATH_TO_PERSON_IMPORT = "src/Resources/persons.csv";
    private static final String PATH_TO_STATISTICS = "src/Resources/statistics.csv";

    public static List<Person> readPersonsFromCsv() {

        List<Person> persons = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_PERSON_IMPORT))) {
                String line;

                br.readLine();

                while ((line = br.readLine()) != null){

                    final String[] row = line.split(",");
                    long id = Long.valueOf(row[0]);
                    String firstName = row[1];
                    String lastName = row[2];
                    int age = Integer.valueOf(row[3]);

                    persons.add(new Person(id, firstName, lastName, age));
                }

                return persons;

        }  catch (FileNotFoundException e) {
                throw new RuntimeException(e);

            } catch (IOException e){
                throw new RuntimeException(e);
            }

    }

    public static void writeStaticsToFile (List<Person> persons, List<Workout> workouts) throws FileNotFoundException {

        try(FileOutputStream fileOutputStream = new FileOutputStream(PATH_TO_STATISTICS)){

            int countPerson = 0;

            for (Person person: persons){
                countPerson++;
                fileOutputStream.write(String.format("\n--- Person %s---\n", countPerson).getBytes());
                fileOutputStream.write(String.format("Name: %s %s (%s)\n",
                        person.getFirstName(), person.getLastName(), person.getAge()).getBytes());
                fileOutputStream.write(String.format("Number of biking exercises: %s\n",
                        getNumberOfBikingWorkoutsByPerson(person.getId(), workouts)).getBytes());
                fileOutputStream.write(String.format("Number of swimming exercises: %s\n",
                        getNumberOfSwimmingWorkoutsByPerson(person.getId(),workouts)).getBytes());
                fileOutputStream.write(String.format("Average duration: %.0f min\n",
                        getAverageDurationOfWorkoutByPerson(person.getId(),workouts)).getBytes());
            }

            List<Workout> bikingWorkout = workouts.stream().filter(workout -> workout instanceof BikingWorkout).toList();
            List<BikingWorkout> bikingWorkoutCast = (List<BikingWorkout>) ((List<?>)bikingWorkout);

            fileOutputStream.write(String.format("\n--- Biking ---\n").getBytes());
            fileOutputStream.write(String.format("Average distance: %.0f m\n", getAverageDistance(bikingWorkout)).getBytes());
            fileOutputStream.write(String.format("Average duration: %.0f min\n", getAverageDuration(bikingWorkout)).getBytes());
            fileOutputStream.write(String.format("# mountain: %s\n",
                    getNumberOfBikingWorkoutsByType(bikingWorkoutCast, BikingType.MOUNTAIN)).getBytes());
            fileOutputStream.write(String.format("# road: %s\n",
                    getNumberOfBikingWorkoutsByType(bikingWorkoutCast, BikingType.ROAD)).getBytes());

            List<Workout> swimmingWorkout = workouts.stream().filter(workout -> workout instanceof SwimmingWorkout).toList();
            List<SwimmingWorkout> swimmingWorkoutCast = (List<SwimmingWorkout>) ((List<?>)swimmingWorkout);
            fileOutputStream.write(String.format("\n--- Swimming ---\n").getBytes());
            fileOutputStream.write(String.format("Average distance: %.0f m\n", getAverageDistance(swimmingWorkout)).getBytes());
            fileOutputStream.write(String.format("Average duration: %.0f min\n", getAverageDuration(swimmingWorkout)).getBytes());
            fileOutputStream.write(String.format("# backstroke: %s\n",
                    getNumberOfSwimmingWorkoutsByType(swimmingWorkoutCast, SwimmingType.BACKSTROKE)).getBytes());
            fileOutputStream.write(String.format("# butterfly: %s\n",
                    getNumberOfSwimmingWorkoutsByType(swimmingWorkoutCast, SwimmingType.BUTTERFLY)).getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static long getNumberOfSwimmingWorkoutsByType (List<SwimmingWorkout> workouts, SwimmingType type){
        return workouts.stream().filter(swimmingWorkout -> swimmingWorkout.getSwimmingType().equals(type)).count();
    }

    private static long getNumberOfBikingWorkoutsByType (List<BikingWorkout> workouts, BikingType type){
        return workouts.stream().filter(bikingWorkout -> bikingWorkout.getBikingType().equals(type)).count();
    }

    private static double getAverageDistance (List<? extends Workout> workouts){
        return workouts.stream().mapToDouble(workout -> workout.getDistance()).sum()/workouts.size();
    }

    private static double getAverageDuration (List<? extends Workout> workouts){
        return workouts.stream().mapToDouble(workout -> workout.getDuration()).sum()/workouts.size();
    }

    private static double getAverageDurationOfWorkoutByPerson (Long personId, List<Workout> workoutList){
        List<Workout> workoutsByPerson = workoutList.stream().filter(workout -> personId == workout.getPersId()).toList();
        return workoutsByPerson.stream().mapToDouble(workout -> workout.getDuration()).sum()/workoutsByPerson.size();
    }

    private static int getNumberOfBikingWorkoutsByPerson (Long personId, List<Workout> workoutList){
        List<Workout> bikingWorkoutByPerson = workoutList.stream().filter(workout -> personId == workout.getPersId()).toList();
        return (int) bikingWorkoutByPerson.stream().filter(workout -> workout instanceof BikingWorkout).count();
    }

    private static int getNumberOfSwimmingWorkoutsByPerson (Long personId, List<Workout> workoutList){
        List<Workout> swimmingWorkoutByPerson = workoutList.stream().filter(workout -> personId == workout.getPersId()).toList();
        return (int) swimmingWorkoutByPerson.stream().filter(workout -> workout instanceof SwimmingWorkout).count();
    }


}


