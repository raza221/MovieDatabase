public class Movie implements Comparable<Movie> {
    private String title;
    private int year;
    private String certificate;
    private String genres;
    private int duration;
    private double averageRating;

    public Movie(String title, int year, String certificate, String genres, int duration, double averageRating) {
        this.title = title;
        this.year = year;
        this.certificate = certificate;
        this.genres = genres;
        this.duration = duration;
        this.averageRating = averageRating;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getCertificate() {
        return certificate;
    }

    public String getGenres() {
        return genres;
    }

    public int getDuration() {
        return duration;
    }

    public double getAverageRating() {
        return averageRating;
    }

    @Override
    public int compareTo(Movie otherMovie) {
        return Integer.compare(this.year, otherMovie.year);
    }

     @Override
    public String toString() {
        return String.format("\"%s\",%d,\"%s\",\"%s\",%d,%.1f",
                title, year, certificate, genres, duration, averageRating);
    }
}
