package co.crisi.crisiroutine.model;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Exercise implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Priority priority;
	private MuscleGroup muscleGroup;
	private Super superSet;
	private Set sets;
	private DailyRoutine dailyRoutineAssociated;

	public Exercise() {
		this("DEFAULT_SQUATS", Priority.HIGH, MuscleGroup.FRONT_LEGS, Super.NOT, new DailyRoutine());
	}

	public Exercise(String name, Priority priority, MuscleGroup muscleGroup, Super superSet,
			DailyRoutine dailyRoutineAssociated) {
		this.name = name;
		this.priority = priority;
		this.muscleGroup = muscleGroup;
		this.superSet = superSet;
		this.dailyRoutineAssociated = dailyRoutineAssociated;
		sets = new Set(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public MuscleGroup getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(MuscleGroup muscleGroup) {
		this.muscleGroup = muscleGroup;
	}

	public Set getSets() {
		return sets;
	}

	public void setSets(Set sets) {
		this.sets = sets;
	}

	public DailyRoutine getDailyRoutineAssociated() {
		return dailyRoutineAssociated;
	}

	public void setDailyRoutineAssociated(DailyRoutine dailyRoutineAssociated) {
		this.dailyRoutineAssociated = dailyRoutineAssociated;
	}

	public Super getSuperSet() {
		return superSet;
	}

	public void setSuperSet(Super superSet) {
		this.superSet = superSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((muscleGroup == null) ? 0 : muscleGroup.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
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
		Exercise other = (Exercise) obj;
		if (muscleGroup != other.muscleGroup)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority != other.priority)
			return false;
		return true;
	}

	public StringProperty infoExercise() {
		return new SimpleStringProperty(
				name + "(R: " + sets.getReps() + " S: " + sets.getSets() + ")" + superSet.toString());
	}

	@Override
	public String toString() {
		return name;
	}

}
