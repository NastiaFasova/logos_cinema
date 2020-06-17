package cinema;

import cinema.exception.WrongInputException;
import cinema.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class Navigator {
    cinema.Cinema cinema = new cinema.Cinema();
    public void menu() {
        System.out.println("Make your choice, please: ");
        System.out.println("1. Add a new movie");
        System.out.println("2. Add a new seance");
        System.out.println("3. Add a new movie into the movieLibrary");
        System.out.println("4. Remove the movie from the movieLibrary");
        System.out.println("5. Add a new seance into the schedule");
        System.out.println("6. Remove the seance from the schedule");
        System.out.println("7. Show all movies added into the movieLibrary");
        System.out.println("8. Show all seances added into the schedule");
        System.out.println("9. Exit");
    }

    public void navigate() {
        System.out.println("Enter the time the cinema is opened and closed in the format(hour:minutes): ");
        Scanner scanner = new Scanner(System.in);
        Time open = Time.from(scanner.next());
        Time closed = Time.from(scanner.next());
        cinema.setOpen(open);
        cinema.setClose(closed);
        while(true) {
            menu();
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                throw new WrongInputException("Wrong input.Enter a number, please", e);
            }
            switch (choice) {
                case 1: {
                    System.out.println("Enter the title of a movie:");
                    String title = scanner.next();
                    System.out.println("Enter the duration of a movie in the format(hour:minutes):");
                    String duration = scanner.next();
                    System.out.println(createMovie(title, duration));
                    break;
                }
                case 2 : {
                    System.out.println("Enter the title of a movie, its duration "
                            + " and a startTime in the format(hour:minutes):");
                    String title = scanner.next();
                    String duration = scanner.next();
                    String startTime = scanner.next();
                    cinema.checkBoundsOfSeance(createSeance(title, duration, startTime));
                    Seance seance = createSeance(title, duration, startTime);
                    System.out.println(seance);
                    break;
                }
                case 3 : {
                    System.out.println("Enter the title of a movie:");
                    String title = scanner.next();
                    System.out.println("Enter the duration of a movie in the format(hour:minutes):");
                    String duration = scanner.next();
                    createMovie(title, duration);
                    System.out.println(cinema.addMovie(createMovie(title, duration), new Time(30, 12),
                            new Time(0, 14)));
                    break;
                }
                case 4 : {
                    System.out.println("Enter the title of a movie:");
                    String title = scanner.next();
                    cinema.removeMovie(findMovie(title).orElseThrow());
                    break;
                }
                case 5 : {
                    System.out.println("Enter the title of a movie, its duration, "
                            + "startTime in the format(hour:minutes) and a day");
                    String title = scanner.next();
                    String duration = scanner.next();
                    String startTime = scanner.next();
                    String day = scanner.next();
                    System.out.println(cinema.addSeance(createSeance(title, duration, startTime), day));
                    break;
                }
                case 6 : {
                    System.out.println("Enter the title of a movie, its "
                            + "startTime in the format(hour:minutes): and a day");
                    String title = scanner.next();
                    String time = scanner.next();
                    String day = scanner.next();
                    Time startTime = Time.from(time);
                    cinema.removeSeance(findSeance(title, startTime).orElseThrow(), day);
                }
                case 7 : {
                    System.out.println(cinema.getMovies());
                    break;
                }
                case 8 : {
                    System.out.println(cinema.getSeances());
                    break;
                }
                case 9 : {
                    System.exit(0);
                }
            }
        }
    }

    private Seance createSeance(String title, String duration, String startTime) {
        Movie movie = createMovie(title, duration);
        return new Seance(movie, Time.from(startTime));
    }

    private Movie createMovie(String title, String duration) {
        return new Movie(title, Time.from(duration));
    }

    private Optional<Movie> findMovie(String title) {
        for (Movie movie : cinema.getMoviesLibrary()) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                    return Optional.of(movie);
            }
        }
        return Optional.empty();
    }

    private Optional<Seance> findSeance(String movieTitle, Time startTime) {
        for (Schedule schedule : cinema.getSchedules().values()) {
            for (Seance seance : schedule.getSeances()) {
                if (seance.getMovie().getTitle().equalsIgnoreCase(movieTitle)
                        && seance.getStartTime().equals(startTime)) {
                    return Optional.of(seance);
                }
            }
        }
        return Optional.empty();
    }
}
