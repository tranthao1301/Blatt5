import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooleschenModell {

    private static Map<String, List<Number>> map;
    private static String BIBLE = "bible.txt";
    private static String MARTIN_LUTHER = "Martin_Luther_Uebersetzung_1912.txt";

    public static void main(String[] args){
     	map = new HashMap<String,List<Number>>();
     	System.out.println("### Datei: bible.txt: ### ");
    	createList(BIBLE, 1);
    	
    	System.out.println("### Datei: Martin_Luther_Uebersetzung_1912.txt: ### ");
    	createList(MARTIN_LUTHER, 2);
    	
    	findWord("Heaven");
    	findWord("Gott");
    }
    
    /* im parameter wird nachher die Datei angegeben und eine Id verliehn,
     * um zu sehen um welche datei es sich handelt
     */
    public static void createList(String filename, int id) {
      List<String> text = new ArrayList<String>();
      
      //gepufferter Eingabe-Stream - erlaubt das Lesen von Datein 
      BufferedReader br;
      try {
    	  File file = new File(Paths.get(filename).toString());
    	  br = new BufferedReader(new FileReader(file));
          String line;
          // solange es Zeilen gibt, liest er weiter
              while ((line = br.readLine()) != null) {
//            	  System.out.println(line);
            	  
                  String[] order = line.trim().split("[ ,.!?\r\n]"); //zeile splitten (neuen absatz einfügen)
                 
                  // Im Array "order" soll interiert werden mit der neuen variable word.
                  // jede stelle im array wird genommen und in der Variable "Word" zwischengespeichert
                  for (String word : order) {
                	  //toLowerCase() => verwandelt einen String aus Großbuchstaben in Kleinbuchstaben
                      word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                      	if(word.length() > 0){
                          text.add(word);
                      	}
                  }
              }    
              
              /*
               * alle wörter, die der liste hinzugefügt wurden, werden nun iteriert.
               * Mit "editWord" wird über das Array "text " iterert.
               * 
               * Nun wird eine Map erstellet, die das jeweilige wort mit der jeweiligen ID des Dokuments enthält.
               * Also wenn das wort aus dem Dokument mit der id 1 kommt, dann wird das word in der
               * map gespeichert + die id des dokuments.
               * 
               * 
               */
              for (String editWord : text) {
            	  // Wenn die Map das aktuelle word noch nicht enthält: dann tue folgndes
                   if (map.get(editWord) == null ) { 
                      	List<Number> documentID = new ArrayList<Number>();
                      	map.put(editWord, documentID);
                      	
                      	map.get(editWord).add(id);
                      
                    }            	 
                   // Wenn die Map das aktuelle word bereits enthält: 
                   else {
                	   
                	   /*
                	    *  wird geprüft, ob es sich um das selbe dokument handelt oder
                	    *  ob das gleich word auch in  einem aderem dokoment enthalten ist.
                	    */
                	
                      	if(!map.get(editWord).contains(id)){
                      				map.get(editWord).add(id);
                      	}
                    }
               }
              // buffered reader wird gestoppt, wenn es keine zeilen mehr gibt
              br.close();
              
      }catch(IOException e) {
    	e.printStackTrace();
      }
    }
    
    
    /*
     * hier findet die suche nach einem Word statt.
     * 
     */
    
	public static void findWord(String searchedWord) {
		searchedWord = searchedWord.toLowerCase();
		if(map.get(searchedWord)== null) {
			System.out.println("Im Bestand befinden sich keine Dokumente in dem das Wort enthalten ist");
		}else {
		List<Number> result = map.get(searchedWord);
		System.out.println("Das Wort: "+ searchedWord + " ist in folgenden Dokumenten enthalten");
			for(Number id : result) {
				System.out.println("Im Dokument: " + id + " ist das Wort enthalten");
			}
		}
	}
}
