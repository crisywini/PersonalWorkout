package co.crisi.crisiroutine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import co.crisi.crisiroutine.model.DailyRoutine;
import co.crisi.crisiroutine.model.WeeklyRoutine;

public class WeeklyRoutineTest {

	private WeeklyRoutine weeklyRoutine;

	private void setUpStage1() {
		weeklyRoutine = new WeeklyRoutine();
	}

	@Test
	public void initWeekTest() {
		setUpStage1();
		weeklyRoutine.initWeek();
		DailyRoutine monday = weeklyRoutine.getWeeks().get("MONDAY");
		DailyRoutine tuesday = weeklyRoutine.getWeeks().get("TUESDAY");
		DailyRoutine wednesday = weeklyRoutine.getWeeks().get("WEDNESDAY");
		DailyRoutine thursday = weeklyRoutine.getWeeks().get("THURSDAY");
		DailyRoutine friday = weeklyRoutine.getWeeks().get("FRIDAY");

		assertNotNull("Monday null", monday);
		assertNotNull("Tuesday null", tuesday);
		assertNotNull("Wednesday null", wednesday);
		assertNotNull("Thursday null", thursday);
		assertNotNull("Friday null", friday);

	}

}
