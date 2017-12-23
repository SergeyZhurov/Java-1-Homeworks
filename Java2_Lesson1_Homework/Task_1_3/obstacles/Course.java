package obstacles;
import java.util.*;
import animals.*;
/**
 * Class for making a course out of obstacles
 *
 * @author Sergey Zhurov
 * @version dated Dec 23, 2017
 * @link https://github.com/
 */

public class Course {                                               // Task 3
    private List<Obstacle> obstacles = new ArrayList<Obstacle>();   // Array of obstacles (ArrayList)

    public Course (ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt (Team team) {                                  // method for making the team run course
        int counter;
        team.clearResults();
        for (Animal animal : team.getMembers()) {
            counter = 0;
            for (Obstacle obstacle : obstacles)
                if (obstacle.doIt(animal)) counter++;
            if (counter == obstacles.size()) team.addResult(animal, true);
            else team.addResult(animal, false);
        }
    }
}