package co.crisi.crisiroutine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import co.crisi.crisiroutine.model.DailyRoutine;
import co.crisi.crisiroutine.model.MuscleGroup;
import co.crisi.crisiroutine.model.Priority;
import co.crisi.crisiroutine.model.Super;

public class DailyRoutineTest {

	private DailyRoutine dailyRoutine;

	private void setUpStage1() {
		dailyRoutine = new DailyRoutine();
	}

	@Test
	public void addExerciseTest() {
		setUpStage1();
		assertTrue("Wrong added 1", dailyRoutine.addExercise("SQUATS", Priority.HIGH, MuscleGroup.BACK, Super.YES));
		assertFalse("Wrong added 2", dailyRoutine.addExercise("SQUATS", Priority.HIGH, MuscleGroup.BACK, Super.YES));
	}

}