import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapReduce {
	private static String BIBENG = "C:\\Users\\trant\\OneDrive\\Desktop\\Studium\\4.Sem\\IN2\\Praktikum\\bible.txt";
	private static String BIBDEU = "Martin_Luther_Uebersetzung_1912.txt";
	private static Map<String, Long> map;
	
	public static void main(String[] args){
		zaehleWorte(BIBENG);
	}

	private static void zaehleWorte(String dateiname) {
		try(Stream<String> stream = Files.lines(Paths.get(dateiname))){
			map = stream.filter(zeile -> zeile.matches(".*\\D1:13.*"))
					.flatMap(zeile -> Arrays.stream(zeile.trim().split("\\s")))
					.map(wort -> wort.replaceAll("[^a-zA-ZÄäÖöÜüß]","")
							.toLowerCase().trim())
					.filter(wort -> wort.length() > 0)
					.map(wort -> new SimpleEntry<>(wort,1))
					.collect(Collectors.groupingBy(SimpleEntry::getKey, Collectors.counting()));
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.entrySet().stream()
		.sorted(Map.Entry.<String,Long> comparingByValue().reversed())
		.forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));
		
	}
	
	
	
}
