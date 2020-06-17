package cinema.model;

public class Seance implements Comparable<Seance> {
	private Movie movie;
	private Time startTime;
	private Time endTime;

	public Seance(Movie movie, Time startTime) {
		super();
		this.movie = movie;
		this.startTime = startTime;
		this.endTime = startTime.add(movie.getDuration());
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Seance [movie=" + movie + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	@Override
	public int compareTo(Seance o) {
		if (this.startTime.compareTo(o.getStartTime()) > 0) {
			return 1;
		} else if (this.startTime.compareTo(o.getStartTime()) < 0) {
			return -1;
		}
		return 0;
	}
}
