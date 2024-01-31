import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MovieDatabase {
    private List<Movie> movies;
    private List<String> outputLines;

    public MovieDatabase() {
        this.movies = new ArrayList<>();
        this.outputLines = new ArrayList<>();
    }

    public void readDatabaseFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = parseCsvLine(line);

                if (data.length == 6) {
                    String title = data[0].trim().replace("\"", "");
                    int year = Integer.parseInt(data[1].trim());
                    String certificate = data[2].trim().replace("\"", "");
                    String genres = data[3].trim().replace("\"", "");
                    int duration = Integer.parseInt(data[4].trim());
                    double averageRating = Double.parseDouble(data[5].trim());

                    Movie movie = new Movie(title, year, certificate, genres, duration, averageRating);
                    movies.add(movie);
                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String[] parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }

        fields.add(currentField.toString());
        return fields.toArray(new String[0]);
    }

    public void displayMoviesSortedByDuration() {
        outputLines.add(""); // Add a gap
        outputLines.add("Movies sorted by duration:");
        outputLines.add(""); // Add a gap       
    
        Collections.sort(movies, Comparator.comparingInt(Movie::getDuration).reversed());
    
        for (Movie movie : movies) {
            // Assuming the Movie class has a toString method that returns the raw line from the file
            outputLines.add(movie.toString());
        }
    
        outputLines.add("Total movies scanned: " + movies.size());
        outputLines.add(""); // Add a gap
    }
    
    
    
    
    

    
    public void displayLowestRatedSciFiMovie() {
        if (movies.isEmpty()) {
            outputLines.add("No movies found");
            return;
        }

        Movie lowestRatedSciFiMovie = null;
        double lowestRating = Double.MAX_VALUE;

        for (Movie movie : movies) {
            if (movie.getGenres().contains("Sci-Fi") && movie.getAverageRating() < lowestRating) {
                lowestRating = movie.getAverageRating();
                lowestRatedSciFiMovie = movie;
            }
        }

        if (lowestRatedSciFiMovie != null) {
            outputLines.add("Lowest-rated Sci-Fi movie: " + formatMovieInfo(lowestRatedSciFiMovie));
        } else {
            outputLines.add("No Sci-Fi movies found");
        }
        outputLines.add(""); // Add a gap

    }


    public void displayFifthMostRecentPGRatedMovie() {
    List<Movie> pgMovies = new ArrayList<>();

    for (Movie movie : movies) {
        if (movie.getCertificate().equals("PG")) {
            pgMovies.add(movie);
        }
    }

    if (pgMovies.size() >= 5) {
        Movie fifthMostRecentPGMovie = pgMovies.get(pgMovies.size() - 5);
        outputLines.add("Fifth most recent PG-rated movie: " + formatMovieInfo(fifthMostRecentPGMovie));
    } else {
        outputLines.add("Not enough PG-rated movies found");
    }
    outputLines.add(""); // Add a gap

    }


    public void displayMovieWithLongestName() {
    if (movies.isEmpty()) {
        outputLines.add("No movies found");
        return;
    }

    Movie movieWithLongestName = null;
    int longestNameLength = 0;

    for (Movie movie : movies) {
        int currentNameLength = movie.getTitle().length();
        if (currentNameLength > longestNameLength) {
            longestNameLength = currentNameLength;
            movieWithLongestName = movie;
        }
    }

    if (movieWithLongestName != null) {
        outputLines.add("Movie with the longest name: " + formatMovieInfo(movieWithLongestName));
    } else {
        outputLines.add("No movies found");
    }
    outputLines.add(""); // Add a gap

    }

    private String formatMovieInfo(Movie movie) {
    return "\"" + movie.getTitle() + "\", Year: " + movie.getYear() +
            ", Certificate: " + movie.getCertificate() + ", Genre: " + movie.getGenres() +
            ", Duration: " + movie.getDuration() + " mins, Average Rating: " + movie.getAverageRating();
    }


    public void displayYearsBetweenOldestAndNewestMovies() {
    if (movies.isEmpty()) {
        outputLines.add("No movies found");
        return;
    }

    int oldestYear = Integer.MAX_VALUE;
    int newestYear = Integer.MIN_VALUE;
    Movie oldestMovie = null;
    Movie newestMovie = null;

    for (Movie movie : movies) {
        int currentYear = movie.getYear();
        if (currentYear < oldestYear) {
            oldestYear = currentYear;
            oldestMovie = movie;
        }
        if (currentYear > newestYear) {
            newestYear = currentYear;
            newestMovie = movie;
        }
    }

    outputLines.add("Oldest movie: " + (oldestMovie != null ? oldestMovie.getTitle() : "N/A"));
    outputLines.add("Newest movie: " + (newestMovie != null ? newestMovie.getTitle() : "N/A"));
    outputLines.add("Number of years between the oldest and newest movies: " + (newestYear - oldestYear));

    outputLines.add(""); // Add a gap
    }




    public static void main(String[] args) {
        MovieDatabase movieDatabase = new MovieDatabase();
        movieDatabase.readDatabaseFromFile("movies.txt");

        // Print the results
        movieDatabase.printOutput();
    }

    public void printOutput() {
        System.out.flush(); // Flush the standard output to ensure previous lines are displayed
        System.out.println("Printing output lines:");
    
        // Print all lines stored in outputLines
        for (String line : outputLines) {
            System.out.println(line);
        }
    
        System.out.println(); // Add a gap after printing output lines
        System.out.println("End of output lines.");
    }
    
    
}