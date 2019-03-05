import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import questionnaire.Question;
import questionnaire.Questionnaire;
import questionnaire.Reponse;


public class Application {
	
	public ArrayList<Questionnaire> questionnaires = new ArrayList<>();
	public ArrayList<Reponse> reponses = new ArrayList<>();
	
	public Application(){
		chargerQuestionnaire();
	}
	
	public void chargerQuestionnaire(){
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(new File("questionnaire.xml"));
			doc.getDocumentElement().normalize();
			
			XPath xPath =  XPathFactory.newInstance().newXPath();   
			
			XPathExpression xExpr = xPath.compile("/questionnaires/questionnaire");
			NodeList questionnaireList = (NodeList) xExpr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < questionnaireList.getLength(); i++) {
				xExpr = xPath.compile("titre");
				String titre = (String) xExpr.evaluate(questionnaireList.item(i), XPathConstants.STRING);
				Questionnaire questionnaire = new Questionnaire(titre);
				
				xExpr = xPath.compile("questions/question");
				NodeList questionList = (NodeList) xExpr.evaluate(questionnaireList.item(i), XPathConstants.NODESET);
				for (int j = 0; j < questionList.getLength(); j++) {
					xExpr = xPath.compile("enonce");
					String enonce = (String) xExpr.evaluate(questionList.item(j), XPathConstants.STRING);
					Question question = new Question(enonce);
				
					xExpr = xPath.compile("reponse");
					NodeList reponseList = (NodeList) xExpr.evaluate(questionList.item(j), XPathConstants.NODESET);
					for (int q = 0; q < reponseList.getLength(); q++) {
						Element element = (Element) reponseList.item(q);
						String rep = element.getTextContent();
						int score = Integer.parseInt(element.getAttribute("score"));
						Reponse reponse = new Reponse(q, rep, score);
						question.addReponse(reponse);
					}
					questionnaire.addQuestion(question);
				}
				questionnaires.add(questionnaire);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Scanner sc;
	public void play(){
		sc = new Scanner(System.in);
		for(Questionnaire questionnaire : questionnaires){
			System.out.println(questionnaire.getTitre());
			for(Question question : questionnaire.getQuestions()){
				System.out.println("\n"+question.getEnonce());
				for(int i=0; i<question.getReponses().size(); i++){
					System.out.println(question.getReponse(i).getNumero()+" : "+question.getReponse(i).getReponse());
				}
				System.out.println("Réponse 0, 1, 2 ou 3 ?");
				int idReponse = Integer.parseInt(sc.nextLine());
				reponses.add(question.getReponse(idReponse));
				question.getReponse(idReponse).checked();
			}
			System.out.println("\n"+"Questionnaire terminé !");
			System.out.println("Score = "+questionnaire.getScore()+"/"+questionnaire.getQuestions().size());
			System.out.println("Appuyer sur entrer pour passer au questionnaire suivant...");
			sc.nextLine();
		}
		System.out.println("Il n'y a plus de questionnaire.");
	}
}
