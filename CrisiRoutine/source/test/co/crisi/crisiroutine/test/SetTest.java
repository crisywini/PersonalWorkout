package co.crisi.crisiroutine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import co.crisi.crisiroutine.model.DailyRoutine;
import co.crisi.crisiroutine.model.Exercise;
import co.crisi.crisiroutine.model.Mode;
import co.crisi.crisiroutine.model.MuscleGroup;
import co.crisi.crisiroutine.model.Priority;
import co.crisi.crisiroutine.model.Routine;
import co.crisi.crisiroutine.model.Set;
import co.crisi.crisiroutine.model.Super;
import co.crisi.crisiroutine.model.WeeklyRoutine;

public class SetTest {

	private Set set;
	private Exercise exercise;

	private void setUpStage1() {
		exercise = new Exercise();
		exercise.setPriority(Priority.HIGH);
		set = new Set(exercise);
	}

	private void setUpStage2() {
		Routine routine = new Routine(Mode.SHREDDING);
		WeeklyRoutine weeklyRoutine = new WeeklyRoutine("FIRST", routine);
		DailyRoutine dailyRoutine = new DailyRoutine("FIRST", weeklyRoutine);
		dailyRoutine.getExercises().put("1",
				new Exercise("1", Priority.HIGH, MuscleGroup.ABS, Super.NOT, dailyRoutine));
		set = dailyRoutine.getExercises().get("1").getSets();
	}

	@Test
	public void testInit() {
		setUpStage1();
		set.init();
		int reps = set.getReps();
		int sets = set.getSets();
		boolean condition = ((sets >= 3 && sets < 5) && (reps >= 6 && reps < 11));
//		System.out.println("FIRST BULKING: reps:" + reps + " sets: " + sets + " Condition " + condition);

		assertTrue("Wrong initialized BULKING 1", condition);
		set.getExerciseAssociated().setPriority(Priority.MEDIUM);
		set.init();
		reps = set.getReps();
		sets = set.getSets();
		condition = ((sets >= 3 && sets < 5) && (reps >= 8 && reps < 13));
//		System.out.println("SECOND BULKING: reps:" + reps + " sets: " + sets + " Condition " + condition);

		assertTrue("Wrong initialized BULKING 2", condition);

		set.getExerciseAssociated().setPriority(Priority.LOW);
		set.init();
		reps = set.getReps();
		sets = set.getSets();
		condition = ((sets >= 3 && sets < 5) && (reps >= 8 && reps < 15));
		assertTrue("Wrong initialized BULKING 3", condition);
//		System.out.println("THIRD BULKING: reps:" + reps + " sets: " + sets + " Condition " + condition);

		setUpStage2();
		set.init();
		reps = set.getReps();
		sets = set.getSets();
		condition = ((sets >= 4 && sets < 6) && (reps >= 10 && reps < 16));
//		System.out.println("FIRST SHREDDING: reps:" + reps + " sets: " + sets + " Condition " + condition);
		assertTrue("Wrong initialized SHREDDING 1", condition);

		set.getExerciseAssociated().setPriority(Priority.MEDIUM);
		set.init();
		reps = set.getReps();
		sets = set.getSets();
		condition = ((sets >= 4 && sets < 6) && (reps >= 12 && reps < 19));
//		System.out.println("SECOND SHREDDING: reps:" + reps + " sets: " + sets + " Condition " + condition);

		assertTrue("Wrong initialized SHREDDING 2", condition);

		set.getExerciseAssociated().setPriority(Priority.LOW);
		set.init();
		reps = set.getReps();
		sets = set.getSets();
		condition = ((sets >= 4 && sets < 6) && (reps >= 15 && reps < 21));

//		System.out.println("THIRD SHREDDING: reps:" + reps + " sets: " + sets + " Condition " + condition);

		assertTrue("Wrong initialized SHREDDING 3", condition);
	}

}
