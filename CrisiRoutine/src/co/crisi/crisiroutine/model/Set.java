package co.crisi.crisiroutine.model;

import java.io.Serializable;
import java.util.Random;

public class Set implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int reps;
	private int sets;
	private Exercise exerciseAssociated;

	public Set() {
		this(new Exercise());
	}

	public Set(Exercise exerciseAssociated) {
		this.exerciseAssociated = exerciseAssociated;
		init();
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public Exercise getExerciseAssociated() {
		return exerciseAssociated;
	}

	public void setExerciseAssociated(Exercise exerciseAssociated) {
		this.exerciseAssociated = exerciseAssociated;
	}

	/**
	 * Testear This method allows to initialize the sets and repetitions of an
	 * exercise depending by the mode of the routine and the priority of the
	 * exercise
	 */
	public void init() {
		DailyRoutine dailyRoutine = exerciseAssociated.getDailyRoutineAssociated();
		WeeklyRoutine weeklyRoutine = dailyRoutine.getWeeklyRoutineAssociated();
		Routine routine = weeklyRoutine.getRoutineAssociated();
		Mode mode = routine.getMode();
		if (mode == Mode.BULKING) {
			if (exerciseAssociated.getPriority() == Priority.HIGH) {
				setSets(getRandomInt(3, 5));
				setReps(getRandomInt(6, 11));
				return;
			}
			if (exerciseAssociated.getPriority() == Priority.MEDIUM) {
				setSets(getRandomInt(3, 5));
				setReps(getRandomInt(8, 13));
				return;
			}
			if (exerciseAssociated.getPriority() == Priority.LOW) {
				setSets(getRandomInt(3, 5));
				setReps(getRandomInt(8, 15));
				return;
			}
		} else {
			if (exerciseAssociated.getPriority() == Priority.HIGH) {
				setSets(getRandomInt(4, 6));
				setReps(getRandomInt(10, 16));
				return;
			}
			if (exerciseAssociated.getPriority() == Priority.MEDIUM) {
				setSets(getRandomInt(4, 6));
				setReps(getRandomInt(12, 19));
				return;
			}
			if (exerciseAssociated.getPriority() == Priority.LOW) {
				setSets(getRandomInt(4, 6));
				setReps(getRandomInt(15, 21));

				return;
			}
		}
	}

	/**
	 * This method allows to generate a random int
	 * 
	 * @param min inclusive
	 * @param max exclusive
	 * @return a random int
	 */
	private int getRandomInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reps;
		result = prime * result + sets;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Set other = (Set) obj;
		if (reps != other.reps)
			return false;
		if (sets != other.sets)
			return false;
		return true;
	}
}
