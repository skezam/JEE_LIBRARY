package App;

import java.util.List;


import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class Test {

	public static void main(String[] args) throws EmpruntException {
		// TODO Auto-generated method stub

		try {
			Class.forName("persistantdata.MediathequeData");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Mediatheque media = Mediatheque.getInstance();
		Utilisateur utilisateur = media.getUser("skezam", "skezam");
		
		System.out.println(utilisateur != null);

		/*
		 * Document doc = media.getDocument(1); System.out.println(doc.affiche()[0]); //
		 * doc.emprunter(utilisateur); media.emprunt(doc, utilisateur);
		 * System.out.println(media.tousLesDocuments().get(0).affiche()[0]);
		 */
		
	
		 media.nouveauDocument(2,"ccs","utilisateur",null,"Nombre de pages : 2");

		 
		 
		
		 



		
		 List<Document> doc1 = media.tousLesDocuments(); 
		
		 System.out.println(doc1 ==null); 
		 System.out.println(doc1.get(1).affiche()[5]);
		 System.out.println(doc1.get(2).affiche()[0]);
		 System.out.println(doc1.get(3).affiche()[0]);
		 
	
		/*
		 * List<Document> doc1 = media.tousLesDocuments();
		 * 
		 * System.out.println(doc1 == null);
		 * System.out.println(doc1.get(0).affiche()[1]);
		 * System.out.println(doc1.get(1).affiche()[2]);
		 * System.out.println(doc1.get(1).affiche()[1]);
		 */

	}

}
