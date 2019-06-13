import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VektorraumModell {

	private static Map<String, List<FileInfo>> map;
	private static String MISCHUNG = "Mischung.txt";
	public static String TEST = "test.txt";

	public static void main(String[] args) {
		map = new HashMap<String, List<FileInfo>>();
		System.out.println("Searching...");
		createList(MISCHUNG, 3);
		createList(TEST, 4);
		findWord("lebendig");
		findWord("klettern");
		findWord("Papageientaucher");
	}

	public static void createList(String filename, int id) {
		List<String> text = new ArrayList<String>();
		BufferedReader br;
		try {
			File file = new File(Paths.get(filename).toString());
			br = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = br.readLine()) != null) {
				String[] order = line.trim().split("[ ,.!?\r\n]"); //zeile splitten (neuen absatz einfügen)
                
               
				for (String word : order) {
					word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
					
					if (word.length() > 0) {
						text.add(word);
					}
				}
			}
			
			for (String editWord : text) {

				if (map.get(editWord) == null) {
					List<FileInfo> fileInfo = new ArrayList<FileInfo>();
					map.put(editWord, fileInfo);
					map.get(editWord).add(new FileInfo(id, 1));
				} else {

					

					if (!map.get(editWord).contains(new FileInfo(id, 1))) {
						map.get(editWord).add(new FileInfo(id, 1));
					} else {
						
						// neue liste mit dem typ FileInfo wird mit der Map gefüllt
						List<FileInfo> listFI = map.get(editWord);
						
						// hier wird über die liste "listFi" iteriert
						for (FileInfo fileI : listFI) {
							if (fileI.getId() == id) {
								fileI.incrementNumOfWord();
							}
						}
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void findWord(String searchedWord) {
		
		searchedWord = searchedWord.toLowerCase();

		if (map.get(searchedWord) == null) {
			System.out.println("Im Bestand befinden sich keine Dokumente in dem das Wort enthalten ist");
		} else {
			List<FileInfo> result = map.get(searchedWord);
			System.out.println("Das Wort " + searchedWord + " ist in folgenden Dokumenten enthalten");
			for (FileInfo fi : result) {
				System.out
						.println("Im Dokument " + fi.getId() + " ist das Wort " + fi.getNumOfWord() + " mal enthalten");
			}
		}
	}

}
