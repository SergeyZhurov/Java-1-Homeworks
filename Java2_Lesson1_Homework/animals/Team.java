package animals;
import java.util.*;
/**
 * Class represents a team of Animal. Stores and gives back results of Course run.
 *
 * @author Sergey Zhurov
 * @version dated Dec 23, 2017
 * @link https://github.com/
 */

public class Team {                                             // Task 2
    private String name;                                        // Team name
    private HashMap<Animal, Boolean> results = new HashMap<Animal, Boolean>();
    private Animal[] team = new Animal[4];                      // 4 members of the team

    public Team (String name, Animal member1, Animal member2, Animal member3, Animal member4) { // constructor
        this.name = name;
        this.team[0] = member1;
        this.team[1] = member2;
        this.team[2] = member3;
        this.team[3] = member4;
    }

    public Animal[] getMembers() {
        return team;
    }

    public void clearResults() {
        results.clear();
    }

    public void addResult (Animal animal, Boolean result) {
        results.put(animal, result);
    }

    public void showWinners() {                                 // method showing all team members who made it
        System.out.println();
        System.out.println("Following members of " + name + " team made it to finish: ");
        System.out.println();
        for (HashMap.Entry<Animal, Boolean> pair : results.entrySet())
            if (pair.getValue() == true) 
                System.out.println(pair.getKey().getName() +  " " + "did it!");
    }

    public void showTeamMembers() {                            // method showing information about the team
        System.out.println();
        System.out.println("Team: " + name);
        System.out.println();
        for (HashMap.Entry<Animal, Boolean> pair : results.entrySet()) {
            System.out.println(pair.getKey() + " say: " + pair.getKey().voice());
            if (pair.getValue() == true) 
                System.out.println(pair.getKey().getName() +  " " + "finished the course last time!");
            else System.out.println(pair.getKey().getName() +  " " + "didnt finish the course last time!");
            System.out.println();
        }
    }
}

































