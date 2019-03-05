package questionnaire;

import java.util.ArrayList;

public class Questionnaire {
	private String titre;
	private ArrayList<Question> questions = new ArrayList<>();
	
	public Questionnaire(String titre) {
		this.titre = titre;
	}
	
	public void addQuestion(Question question){
		questions.add(question);
	}
	
	public String getTitre() {
		return titre;
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public int getScore(){
		int score = 0;
		for(Question question : questions)
			for(Reponse reponse : question.getReponses())
				if(reponse.isChecked() && reponse.getScore()==1)
					score++;
		return score;
	}
}
