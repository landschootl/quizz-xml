package questionnaire;


public class Reponse {
	private int numero;
	private String reponse;
	private int score;
	private boolean checked = false;
	
	public Reponse(int numero, String reponse, int score) {
		this.numero = numero;
		this.reponse = reponse;
		this.score = score;
		
	}
	
	public String getReponse() {
		return reponse;
	}

	public int getScore() {
		return score;
	}
	
	public int getNumero(){
		return numero;
	}
	
	public void checked(){
		checked = true;
	}
	
	public boolean isChecked(){
		return checked;
	}
}
