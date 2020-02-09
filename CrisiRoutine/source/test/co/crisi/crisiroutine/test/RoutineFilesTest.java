package co.crisi.crisiroutine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import co.crisi.crisiroutine.model.DailyRoutine;
import co.crisi.crisiroutine.model.MuscleGroup;
import co.crisi.crisiroutine.persistence.RoutineFiles;

public class RoutineFilesTest {

	private DailyRoutine dailyRoutine;

	private void setUpStage1() {
		dailyRoutine = new DailyRoutine();
	}

	@Test
	public void createDailyRoutineTest() {
		setUpStage1();
		MuscleGroup[] muscleGroups = { MuscleGroup.BACK, MuscleGroup.BICEPS, MuscleGroup.ABS };
		RoutineFiles.createDailyRoutine("exercises\\TopExercises.txt", dailyRoutine, muscleGroups);
		System.err.println(dailyRoutine.getExercises().size()+" BICEPS AND BACK");
		assertTrue("Back and bicep wrong size",
				dailyRoutine.getExercises().size() >= 7 && dailyRoutine.getExercises().size() <= 9);
		dailyRoutine.getExercises().clear();
		muscleGroups[0] = MuscleGroup.FRONT_LEGS;
		muscleGroups[1] = MuscleGroup.FRONT_LEGS;
		RoutineFiles.createDailyRoutine("exercises\\LegExercises.txt", dailyRoutine, muscleGroups);
		System.err.println(dailyRoutine.getExercises().size()+" FRONT_LEGS");
		assertTrue("Front legs wrong size",
				dailyRoutine.getExercises().size() >= 7 && dailyRoutine.getExercises().size() <= 10);
		dailyRoutine.getExercises().clear();
		muscleGroups[0] = MuscleGroup.BACK_LEGS;
		muscleGroups[1] = MuscleGroup.BACK_LEGS;
		RoutineFiles.createDailyRoutine("exercises\\LegExercises.txt", dailyRoutine, muscleGroups);
		System.err.println(dailyRoutine.getExercises().size()+" BACK_LEGS");
		assertTrue("Back legs wrong size", dailyRoutine.getExercises().size() >= 12 && dailyRoutine.getExercises().size() <= 15);
		dailyRoutine.getExercises().clear();
		muscleGroups[0] = MuscleGroup.PECTORALS;
		muscleGroups[1] = MuscleGroup.PECTORALS;
		RoutineFiles.createDailyRoutine("exercises\\TopExercises.txt", dailyRoutine, muscleGroups);
		System.err.println(dailyRoutine.getExercises().size()+" PECTORALS");
		assertTrue("Pectorals wrong size", dailyRoutine.getExercises().size() >= 7 && dailyRoutine.getExercises().size() <= 9);
	}

}
