import java.util.*;

public class RecommendationSystem {
    
    static String[] movies = {
        "Inception", "Interstellar", "The Dark Knight", "The Matrix", "Titanic"
    };
    static String[] genres = {
        "Sci-Fi Thriller", "Sci-Fi Adventure", "Action Killer", "Sci-Fi Action", "Romance Drama"
    };

    static int similarity(String g1, String g2) {
        Set<String> set1 = new HashSet<>(Arrays.asList(g1.toLowerCase().split(" ")));
        Set<String> set2 = new HashSet<>(Arrays.asList(g2.toLowerCase().split(" ")));
        set1.retainAll(set2);
        return set1.size();
    }

    static List<String> recommend(String movie) {
        int idx = -1;
        for (int i = 0; i < movies.length; i++) {
            if (movies[i].equalsIgnoreCase(movie)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println("Movie not found!");
            return Collections.emptyList();
        }

        String targetGenre = genres[idx];
        Map<String, Integer> scores = new HashMap<>();

        for (int i = 0; i < movies.length; i++) {
            if (i != idx) {
                int score = similarity(targetGenre, genres[i]);
                scores.put(movies[i], score);
            }
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(scores.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        List<String> recommendations = new ArrayList<>();
        for (int i = 0; i < Math.min(3, sorted.size()); i++) {
            recommendations.add(sorted.get(i).getKey());
        }
        return recommendations;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a movie name:");
        String movie = sc.nextLine();

        List<String> recs = recommend(movie);
        if (!recs.isEmpty()) {
            System.out.println("Recommendations for " + movie + ":");
            for (String r : recs) {
                System.out.println("- " + r);
            }
        }
        sc.close();
    }
}
