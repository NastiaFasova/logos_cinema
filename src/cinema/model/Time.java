package cinema.model;

import cinema.exception.MyTimeException;

public class Time implements Comparable<Time> {
	private int min;
	private int hour;

	public Time() {
		super();
	}

	public Time(int hour, int min) {
		checkBound(min, hour);
		this.hour = hour;
		this.min = min;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	@Override
	public String toString() {
		return "Time [min=" + min + ", hour=" + hour + "]";
	}

	public Time add(Time other) {
		Time newTime = new Time(hour, min);
		newTime.min += other.min;
		newTime.hour += other.hour;
		checkBound(hour, min);
		return newTime;
	}
	@Override
	public int compareTo(Time o) {
		if(this.hour > o.getHour()) {
			return 1;
		} else if(this.hour < o.getHour()) {
			return -1;
		} else {
			if(this.min > o.getMin()) {
				return 1;
			} else if(this.min < o.getMin()) {
				return -1;
			}
		}
		return 0;
	}

	private void checkBound(int hour, int min) {
		if (min < 0) {
			this.min = 0;
		}
		if (min > 60) {
			this.min = 60;
		}
		if (hour < 0) {
			this.hour = 0;
		}
		if (hour > 24) {
			this.hour = 24;
		}
		if (min == 60) {
			this.min = 0;
			this.hour += 1;
		}
	}

	public static Time from(String data) {
		String[] time = data.split(":");
		int hour, min;
		try {
			hour = Integer.parseInt(time[0]);
			min =  Integer.parseInt(time[1]);
		} catch (Exception e) {
			throw new MyTimeException("Wrong input...");
		}
		return new Time(hour, min);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)  {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Time time = (Time) o;
		if (min != time.min) return false;
		return hour == time.hour;
	}

	@Override
	public int hashCode() {
		int result = min;
		result = 31 * result + hour;
		return result;
	}
}
