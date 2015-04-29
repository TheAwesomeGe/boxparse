package boxparse.interpretation;

public class Token {
	private String id;
	private String word;
	private String pos;
	private String ne;
	
	public Token(String id, String word, String pos, String ne) {
		this.id = id;
		this.word = word;
		this.pos = pos;
		this.ne = ne;
	}
	
	public Token(String id, String word, String pos) {
		this(id, word, pos, "");
	}
	
	public String getID() {
		return id;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getPOSTag() {
		return pos;
	}
	
	public String getNETag() {
		return ne;
	}
}
