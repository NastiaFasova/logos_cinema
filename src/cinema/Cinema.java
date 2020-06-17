package cinema;

import cinema.exception.MyTimeException;
import cinema.model.*;

import java.util.*;

public class Cinema {
	private Map<Days, Schedule> schedules = new TreeMap<>();
	{
		schedules.put(Days.SUNDAY, new Schedule());
		schedules.put(Days.MONDAY, new Schedule());
		schedules.put(Days.TUESDAY, new Schedule());
		schedules.put(Days.WEDNESDAY, new Schedule());
		schedules.put(Days.THURSDAY, new Schedule());
		schedules.put(Days.FRIDAY, new Schedule());
		schedules.put(Days.SATURDAY, new Schedule());
	}
	private ArrayList<Movie> moviesLibrary = new ArrayList<>();
	private Time open;
	private Time close;

	public Map<Days, Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(Map<Days, Schedule> schedules) {
		this.schedules = schedules;
	}

	public ArrayList<Movie> getMoviesLibrary() {
		return moviesLibrary;
	}

	public void setMoviesLibrary(ArrayList<Movie> moviesLibrary) {
		this.moviesLibrary = moviesLibrary;
	}

	public Time getOpen() {
		return open;
	}

	public void setOpen(Time open) {
		this.open = open;
	}

	public Time getClose() {
		return close;
	}

	public void setClose(Time close) {
		this.close = close;
	}

	public void checkBoundsOfSeance(Seance seance) {
		if (seance.getStartTime().compareTo(open) < 0) {
			throw new MyTimeException("The time of seance must be wrong. The cinema is still closed");
		}
		if (seance.getEndTime().compareTo(close) > 0) {
			throw new MyTimeException("The time of seance must be wrong. The cinema has already been closed");
		}
	}

	public Movie addMovie(Movie movie, Time...time) {
		moviesLibrary.add(movie);
		for (Schedule schedule : schedules.values()) {
			Set<Seance> seances = schedule.getSeances();
			for (Seance seance : seances) {
				seance.setStartTime(time[0]);
				seance.setEndTime(time[1]);
				seance.setMovie(movie);
				schedule.addSeance(seance);
			}
		}
		return movie;
	}

	public Seance addSeance(Seance seance, String day) {
		checkBoundsOfSeance(seance);
		for (Days currentDay : Days.values()) {
			if (currentDay.toString().equalsIgnoreCase(day)) {
				Schedule schedule = schedules.get(currentDay);
				schedule.addSeance(seance);
				schedules.put(currentDay,schedule);
			}
		}
		return seance;
	}

	public Movie removeMovie(Movie movie) {
		moviesLibrary.remove(movie);
		System.out.println("The movie was successfully removed");
		return movie;
	}

	public Seance removeSeance(Seance seance, String day) {
		for (Days currentDay : Days.values()) {
			for (Schedule schedule : schedules.values()) {
				if (currentDay.toString().equalsIgnoreCase(day)) {
					schedule.removeSeance(seance);
				}
			}
		}
		System.out.println("The seance was successfully removed");
		return seance;
	}

	public List<Movie> getMovies() {
		return new ArrayList<>(moviesLibrary);
	}
	public Set<Seance> getSeances() {
		Set<Seance> seances = new HashSet<>();
		for (Schedule schedule : schedules.values()) {
			seances.addAll(schedule.getSeances());
		}
		return seances;
	}

}
