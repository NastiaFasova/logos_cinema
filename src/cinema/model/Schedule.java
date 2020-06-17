package cinema.model;

import java.util.Set;
import java.util.TreeSet;

public class Schedule {
	private Set<Seance> seances = new TreeSet<>();

	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(Set<Seance> seances) {
		this.seances = seances;
	}

	public Seance addSeance(Seance seance) {
		seances.add(seance);
		return seance;
	}

	public Seance removeSeance(Seance seance) {
		seances.remove(seance);
		return seance;
	}

}
