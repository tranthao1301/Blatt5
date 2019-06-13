
public class FileInfo {

	private int id;
	private int numOfWord;

	public FileInfo(int id, int numOfWord) {
	this.id = id;
	this.numOfWord = numOfWord;
	}
	
	public int incrementNumOfWord() {
		return numOfWord++;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumOfWord() {
		return numOfWord;
	}

	public void setNumOfWord(int numOfWord) {
		this.numOfWord = numOfWord;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileInfo info = (FileInfo) o;

        return id == info.id;
    }

}
