package co.crisi.crisiroutine.model;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.TreeMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DailyRoutine implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nameOfDay;
	private TreeMap<String, Exercise> exercises;
	private WeeklyRoutine weeklyRoutineAssociated;

	public DailyRoutine() {
		this("MONDAY", new WeeklyRoutine());
	}

	public DailyRoutine(String nameOfDay, WeeklyRoutine weeklyRoutineAssociated) {
		this.nameOfDay = nameOfDay;
		this.weeklyRoutineAssociated = weeklyRoutineAssociated;
		exercises = new TreeMap<String, Exercise>();
	}

	public TreeMap<String, Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(TreeMap<String, Exercise> exercises) {
		this.exercises = exercises;
	}

	public String getNameOfDay() {
		return nameOfDay;
	}

	public void setNameOfDay(String nameOfDay) {
		this.nameOfDay = nameOfDay;
	}

	public WeeklyRoutine getWeeklyRoutineAssociated() {
		return weeklyRoutineAssociated;
	}

	public void setWeeklyRoutineAssociated(WeeklyRoutine weeklyRoutineAssociated) {
		this.weeklyRoutineAssociated = weeklyRoutineAssociated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameOfDay == null) ? 0 : nameOfDay.hashCode());
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
		DailyRoutine other = (DailyRoutine) obj;
		if (nameOfDay == null) {
			if (other.nameOfDay != null)
				return false;
		} else if (!nameOfDay.equals(other.nameOfDay))
			return false;
		return true;
	}

	private boolean isOnDailyRoutine(String name) {
		return exercises.containsKey(name);
	}

	/**
	 * Testear This method allows to add exercises
	 * 
	 * @param name
	 * @param priority
	 * @param muscleGroup
	 * @param superSet
	 * @param dailyRoutineAssociated
	 * @return
	 */
	public boolean addExercise(String name, Priority priority, MuscleGroup muscleGroup, Super superSet) {
		boolean isAdded = false;
		if (!isOnDailyRoutine(name)) {
			exercises.put(name, new Exercise(name, priority, muscleGroup, superSet, this));
			isAdded = true;
		}
		return isAdded;
	}

	/**
	 * This method allows to save the exercises information in an ArrayList
	 * 
	 * @return an ArrayList
	 */
	public ArrayList<Exercise> getExercisesInList() {
		ArrayList<Exercise> exercisesList = new ArrayList<Exercise>();
		Iterator<String> iterator = exercises.keySet().iterator();
		Exercise exercise;
		while (iterator.hasNext()) {
			exercise = exercises.get(iterator.next());
			exercisesList.add(exercise);
		}
		return exercisesList;
	}
	public StringProperty infoProperty() {
		ArrayList<Exercise> exercisesList = getExercisesInList();
		String info = "";
		for (int i = 0; i < exercisesList.size(); i++) {
			info += exercisesList.get(i).infoExercise().get()+"\n";
		}
		return new SimpleStringProperty(info);
	}

}
