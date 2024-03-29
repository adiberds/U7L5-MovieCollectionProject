import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a tital search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }



    public void searchCast() {
        System.out.print("Enter the name of a cast member search term: ");
        String searchTerm = scanner.nextLine();
        searchTerm = searchTerm.toLowerCase();
        ArrayList<String> results = new ArrayList<String>();
        ArrayList<Movie> results2 = new ArrayList<Movie>();
        if (results.size() == 0) {
            for (int i = 0; i < movies.size(); i++) {
                String[] Cast = movies.get(i).getCast().split("\\|");
                for (String CastActor : Cast) {
                    if (CastActor.toLowerCase().contains(searchTerm) && results.contains(CastActor) == false) {
                        results.add(CastActor);
                        if (results.size() == 0) {
                            String[] currentCast = movies.get(i).getCast().split("\\|");
                                for (String actor : currentCast) {
                                    if (actor.toLowerCase().contains(searchTerm) && results.contains(actor) == false) {
                                        results.add(actor);
                                    }
                                }
                            }
                            for (int i = 0; i < results.size(); i++) {
                                System.out.println(i++ + "." + results.get(i));
                            }

                            System.out.println("Which actor would you like to see?");
                            int actor = scanner.nextInt();
                            actor = actor--;

                            for (Movie actorMovie : movies) {
                                if (actorMovie.getCast().contains(results.get(actor))) {
                                    results2.add(actorMovie);
                                }
                            }
                            for (int i = 0; i < results2.size(); i++) {
                                System.out.println(i++ + ". " + results2.get(i).getTitle());
                            }

                            System.out.println("Which movie would you like to know about?");
                            int item = scanner.nextInt();
                            displayMovieInfo(results2.get(item--));
                            scanner.nextLine();
                        } else {
                            System.out.println("No actor found!");}}

                    }
                }
            }


            private void searchKeywords(){
    System.out.print("Enter:");
    String searchTerm = scanner.nextLine().toLowerCase();

                ArrayList<Movie> results = new ArrayList<Movie>();

                    for (Movie movie : movies)
                    {
                        String Keywords = movie.getKeywords().toLowerCase();
                        if (Keywords.contains(searchTerm))
                        {
                            results.add(movie);
                        }

                    }
                    if (results.isEmpty()){
                        System.out.print("no results!");
                    }
                    else
                    {
                        System.out.println("Results;");
                        for(int i = 0; i < results.size(); i++){
                            System.out.print((i++) + "." + results.get(i).getTitle());
                }
System.out.println("Enter the number of the movie you want to view!");
                        String Input = scanner.nextLine();
                        try {
                            int num = Integer.parseInt(Input);
                            if (num > 0 && num <= results.size()) {
                                displayMovieInfo((results.get(num--)));
                            } else {
                                System.out.println("No movie found!");

                            }


                            

    private void listGenres()
    {
ArrayList<String> genres = new ArrayList<String>();
for (int i = 0; i < movies.size(); i++){
    String[] MoviesGenres = movies.get(i).getGenres().split("\\|");
    for (int gen = 0; gen < MoviesGenres.length; gen++ )
    {

if (genres.get(gen).equals(MoviesGenres[gen]));
{
            boolean inList = true;
        }
        for(int gen2 = 0; gen2 < MoviesGenres.length; gen2++){
            boolean inList = false;
        }
    }
}
    }

    private void listHighestRated()
    {
        ArrayList<Movie> topMovies = new ArrayList<>(movies);
        for (int i = 0; i < topMovies.size(); i++){
            for (int nextIndex = i + 1; nextIndex < topMovies.size(); nextIndex++){
                Movie movie1 = topMovies.get(i);
                Movie movie2 = topMovies.get(nextIndex);
                if(movie1.getUserRating() < movie2.getUserRating()){
                    topMovies.set(i, movie2);
                    topMovies.set(nextIndex, movie1);
                }

            }

        }
        System.out.println("Top 50 of the highest rated Movies:");
        for (int i = 0; i < 50 && i < topMovies.size(); i++){
        Movie movie = topMovies.get(i);
            double rating = movie.getUserRating();
            String title = movie.getTitle();
            System.out.println((i+1) + ". " + title + " (" + rating + ")");
        }



    }

    private void listHighestRevenue()
    {
ArrayList<Movie> top50revenue = new ArrayList<>(movies) {
    for(int i = 0; i < top50revenue.size(); i++){
for (int nextIndex = i +1; nextIndex < top50revenue.size(); nextIndex++)
{
    Movie movie1 = top50revenue.get(i);
    Movie movie2 = top50revenue.get(nextIndex);
    if(movie1.getRevenue() < movie2.getRevenue()){
        top50revenue.set(i, movie2);
        top50revenue.set(nextIndex, movie1);

    System.out.println("The top 50 of the highest revenue movies are:");
    for (int i = 0; i < 50 && i < top50revenue.size(); i++) {
        String title = movie1.getTitle();
        double revenue = movie1.getRevenue();
        Movie movie = top50revenue.get(i);
        System.out.println((i)+1) + ". " + title + " ($" + revenue + ")"))
    }
    }



}

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}