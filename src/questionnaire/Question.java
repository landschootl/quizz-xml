package questionnaire;

import java.util.ArrayList;

public class Question {
	private String enonce;
	private ArrayList<Reponse> reponses = new ArrayList<>();
	
	public Question(String enonce) {
		this.enonce = enonce;
	}

	public void addReponse(Reponse reponse){
		reponses.add(reponse);
	}
	
	public String getEnonce() {
		return enonce;
	}
	
	public ArrayList<Reponse> getReponses() {
		return reponses;
	}
	
	public Reponse getReponse(int id) {
		return reponses.get(id);
	}
}
