public class Main {
    public static void main(String[] args) {
        MovieDatabase movieDatabase = new MovieDatabase();

        // 1. Read in a database from the file
        movieDatabase.readDatabaseFromFile("movies.txt");

        // 2. Display the entire collection of movies sorted by duration
        movieDatabase.displayMoviesSortedByDuration();

        // 3. Test displaying the lowest-rated Sci-Fi movie
        movieDatabase.displayLowestRatedSciFiMovie();

        // 4. Test displaying the fifth most recent "PG" rated movie
        movieDatabase.displayFifthMostRecentPGRatedMovie();

        // 5. Test displaying the movie with the longest name
        movieDatabase.displayMovieWithLongestName();

        // 6. Test displaying the number of years between the oldest and newest movies
        movieDatabase.displayYearsBetweenOldestAndNewestMovies();

        // Print the results
        movieDatabase.printOutput();
    }
}
