package com.formation.tp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Cr�ation d'une instance de ma classe LivresBDD
		LivresBDD livreBdd = new LivresBDD(this);
		
		//Cr�ation d'un livre
		Livre livre = new Livre("123456789", "Programmez pour Android");
		
		//On ouvre la base de donn�es pour �crire dedans
		livreBdd.open();
		
		//On ins�re le livre que l'on vient de cr�er
		livreBdd.insertLivre(livre);
		
		//Pour v�rifier que l'on a bien cr�� notre livre dans la BDD
		//on extrait le livre de la BDD gr�ce au titre du livre que l'on a cr�� pr�c�demment
		Livre livreFromBdd = livreBdd.getLivreWithTitre(livre.getTitre());
		
		//Si un livre est retourn� (donc si le livre � bien �t� ajout� � la BDD)
		if(livreFromBdd != null){
		//On affiche les infos du livre dans un Toast
		Toast.makeText(this, livreFromBdd.toString(), Toast.LENGTH_LONG).show();
		//On modifie le titre du livre
		livreFromBdd.setTitre("J'ai modifi� le titre du livre");
		//Puis on met � jour la BDD
		livreBdd.updateLivre(livreFromBdd.getId(), livreFromBdd);
		}
		
		//On extrait le livre de la BDD gr�ce au nouveau titre
		livreFromBdd = livreBdd.getLivreWithTitre("J'ai modifi� le titre du livre");
		
		//S'il existe un livre poss�dant ce titre dans la BDD
		if(livreFromBdd != null){
		//On affiche les nouvelles informations du livre pour v�rifier que le titre du livre a bien �t� mis � jour
		Toast.makeText(this, livreFromBdd.toString(), Toast.LENGTH_LONG).show();
		//on supprime le livre de la BDD gr�ce � son ID
		livreBdd.removeLivreWithID(livreFromBdd.getId());
		}
		
		//On essaye d'extraire de nouveau le livre de la BDD toujours gr�ce � son nouveau titre
		livreFromBdd = livreBdd.getLivreWithTitre("J'ai modifi� le titre du livre");
		
		//Si aucun livre n'est retourn�
		if(livreFromBdd == null){
		//On affiche un message indiquant que le livre n'existe pas dans la BDD
		Toast.makeText(this, "Ce livre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
		}
		//Si le livre existe (mais normalement il ne devrait pas)
		else{
		//on affiche un message indiquant que le livre existe dans la BDD
		Toast.makeText(this, "Ce livre existe dans la BDD", Toast.LENGTH_LONG).show();
		}
		
		livreBdd.close();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
