package moteur_graphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AffichageJeu extends Fenetre implements ActionListener{
	private JButton centre;
	private JLabel title;
	private JPanel principal;
	private JTextField scanner;
	private AffichageMainDuJoueur afmdj;
	private AffichageCampDuJoueur afcdj;
	private AffichageJoueur aj;
	
	public AffichageJeu(){
		super();
		principal = new JPanel();
		principal.setLayout(new GridLayout(3,1));
		
		JPanel primaire = new JPanel();
		primaire.setLayout(new GridLayout(2,1));
		title = new JLabel("Partie");
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		primaire.add(title);
		aj = new AffichageJoueur();
		primaire.add(aj);
		
		principal.add(primaire);
		
		
		
		afcdj = new AffichageCampDuJoueur(0);
		principal.add(afcdj);
		
		JPanel secondaire = new JPanel();
		secondaire.setLayout(new GridLayout(1,2));
		centre = new JButton("centre");
		centre.addActionListener(this);
		secondaire.add(centre);
		
		afmdj = new AffichageMainDuJoueur();
		secondaire.add(afmdj);
		
		principal.add(secondaire);
			
		this.setContentPane(principal);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==centre){
			PopupCentre pc = new PopupCentre();
		}
		
	}
	
}
