
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

    public static void run(Scanner scanner) {
        try {
            System.out.print("Введіть URL вебсторінки: ");
            String url = scanner.nextLine().trim();

            URI uri = URI.create(url);

            if (!"http".equalsIgnoreCase(uri.getScheme())
                    && !"https".equalsIgnoreCase(uri.getScheme())) {
                throw new IllegalArgumentException(
                        "URL повинен починатися з http:// або https://");
            }

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("User-Agent", "JavaLab5/1.0")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200
                    || response.statusCode() >= 300) {
                System.out.println("Помилка HTTP: "
                        + response.statusCode());
                return;
            }

            String html = response.body();

            Pattern pattern = Pattern.compile(
                    "<\\s*/?\\s*([a-zA-Z][a-zA-Z0-9:-]*)\\b",
                    Pattern.CASE_INSENSITIVE);

            Matcher matcher = pattern.matcher(html);

            Map<String, Integer> frequencies = new TreeMap<>();

            while (matcher.find()) {
                String tag = matcher.group(1)
                        .toLowerCase(Locale.ROOT);

                frequencies.merge(tag, 1, Integer::sum);
            }

            if (frequencies.isEmpty()) {
                System.out.println("HTML-теги не знайдено.");
                return;
            }

            System.out.println("\nТеги в лексикографічному порядку:");

            frequencies.forEach((tag, count) ->
                    System.out.println("<" + tag + "> : " + count));

            System.out.println("\nТеги за зростанням частоти:");

            frequencies.entrySet().stream()
                    .sorted(Comparator
                            .comparingInt(
                                    (Map.Entry<String, Integer> entry)
                                            -> entry.getValue())
                            .thenComparing(Map.Entry::getKey))
                    .forEach(entry ->
                            System.out.println("<" + entry.getKey()
                                    + "> : " + entry.getValue()));

        } catch (IllegalArgumentException e) {
            System.out.println("Некоректний URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка отримання сторінки: "
                    + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("HTTP-запит було перервано.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }
}
