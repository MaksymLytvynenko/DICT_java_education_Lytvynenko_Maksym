package CurrencyExchange;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyExchange {
    private static final Map<String, Double> cache = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String baseCurrency = scanner.nextLine().toUpperCase();

        while (true) {
            String targetCurrency = scanner.nextLine().toUpperCase();
            if (targetCurrency.isEmpty()) break;

            double amount = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Checking the cache...");
            String cacheKey = baseCurrency + "_" + targetCurrency;
            if (cache.containsKey(cacheKey)) {
                System.out.println("It is in the cache!");
                double rate = cache.get(cacheKey);
                double convertedAmount = amount * rate;
                System.out.printf("You received %.2f %s.%n", convertedAmount, targetCurrency);
            } else {
                System.out.println("Sorry, but it is not in the cache!");
                double rate = getExchangeRate(baseCurrency, targetCurrency);
                if (rate != -1) {
                    cache.put(cacheKey, rate);
                    double convertedAmount = amount * rate;
                    System.out.printf("You received %.2f %s.%n", convertedAmount, targetCurrency);
                }
            }
        }

        scanner.close();
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        String apiUrl = "http://www.floatrates.com/daily/" + baseCurrency.toLowerCase() + ".json";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonResponse = new JSONObject(response.body());
            if (jsonResponse.has(targetCurrency.toLowerCase())) {
                JSONObject rateObject = jsonResponse.getJSONObject(targetCurrency.toLowerCase());
                return rateObject.getDouble("rate");
            } else {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
}
