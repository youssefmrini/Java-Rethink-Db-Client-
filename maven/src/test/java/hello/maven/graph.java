package hello.maven;

import java.awt.List;
import java.util.ArrayList;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.ast.Db;
import com.rethinkdb.gen.ast.ForEach;
import com.rethinkdb.gen.exc.ReqlError;
import com.rethinkdb.gen.exc.ReqlQueryLogicError;
import com.rethinkdb.model.Arguments;
import com.rethinkdb.model.MapObject;
import com.rethinkdb.net.Connection;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class graph extends JFrame {

	private JPanel contentPane;
	private JTextField nom;
	private JTextField prenom;
	private JTextField titre;
	private JTextField prix;
	private JTextField isbn;
	private JTextField bd;
	private JTextField table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					graph frame = new graph();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public graph() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(36, 38, 46, 14);
		contentPane.add(lblNom);
		
		JLabel lblPrnom = new JLabel("Prénom");
		lblPrnom.setBounds(36, 63, 46, 14);
		contentPane.add(lblPrnom);
		
		JLabel lblAuteur = new JLabel("Auteur");
		lblAuteur.setBounds(67, 13, 46, 14);
		contentPane.add(lblAuteur);
		
		nom = new JTextField();
		nom.setBounds(92, 35, 86, 20);
		contentPane.add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setBounds(92, 60, 86, 20);
		contentPane.add(prenom);
		prenom.setColumns(10);
		
		JLabel lblLivre = new JLabel("Livre");
		lblLivre.setBounds(67, 101, 46, 14);
		contentPane.add(lblLivre);
		
		JLabel lblTitre = new JLabel("Titre");
		lblTitre.setBounds(36, 137, 46, 14);
		contentPane.add(lblTitre);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setBounds(36, 162, 46, 14);
		contentPane.add(lblPrix);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(36, 187, 46, 14);
		contentPane.add(lblIsbn);
		
		titre = new JTextField();
		titre.setBounds(97, 134, 86, 20);
		contentPane.add(titre);
		titre.setColumns(10);
		
		prix = new JTextField();
		prix.setBounds(97, 159, 86, 20);
		contentPane.add(prix);
		prix.setColumns(10);
		
		isbn = new JTextField();
		isbn.setBounds(97, 184, 86, 20);
		contentPane.add(isbn);
		isbn.setColumns(10);
		
		JLabel lblBaseDeDonnes_1 = new JLabel("Base de données");
		lblBaseDeDonnes_1.setBounds(215, 38, 103, 14);
		contentPane.add(lblBaseDeDonnes_1);
		
		JLabel lblLiaison = new JLabel("Liaison");
		lblLiaison.setBounds(339, 13, 46, 14);
		contentPane.add(lblLiaison);
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setBounds(215, 63, 46, 14);
		contentPane.add(lblTable);
		
		bd = new JTextField();
		bd.setBounds(327, 35, 86, 20);
		contentPane.add(bd);
		bd.setColumns(10);
		
		table = new JTextField();
		table.setBounds(327, 60, 86, 20);
		contentPane.add(table);
		table.setColumns(10);
		
		JButton btnCrer = new JButton("Créer");
		btnCrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   final RethinkDB r = RethinkDB.r;
					Connection conn = r.connection().hostname("localhost").port(28015).connect();
					  r.dbCreate(bd.getText()).run(conn);
					  r.db(bd.getText()).tableCreate(table.getText()).run(conn);
				
			}
		});
		btnCrer.setBounds(324, 97, 89, 23);
		contentPane.add(btnCrer);
		
		JButton btnInsrer = new JButton("Insérer");
		btnInsrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				final RethinkDB r = RethinkDB.r;
				Connection conn = r.connection().hostname("localhost").port(28015).connect();
				  r.db(bd.getText()).table(table.getText()).insert(r.hashMap("nom",nom.getText()).with("prenom",prenom.getText()).with("titre",titre.getText()).with("prix",prix.getText()).with("isbn",isbn.getText())).run(conn);
				
			}
		});
		btnInsrer.setBounds(69, 227, 89, 23);
		contentPane.add(btnInsrer);
		
		JButton btnSupprimerTable = new JButton("Supprimer Table");
		btnSupprimerTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final RethinkDB r = RethinkDB.r;
				Connection conn = r.connection().hostname("localhost").port(28015).connect();
				r.db(bd.getText()).tableDrop(table.getText()).run(conn);
				  
			}
		});
		btnSupprimerTable.setBounds(283, 133, 130, 23);
		contentPane.add(btnSupprimerTable);
		
		JButton btnSuppDb = new JButton("Supprimer DB");
		btnSuppDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final RethinkDB r = RethinkDB.r;
				Connection conn = r.connection().hostname("localhost").port(28015).connect();
				r.dbDrop(bd.getText()).run(conn);
			}
		});
		btnSuppDb.setBounds(283, 158, 130, 23);
		contentPane.add(btnSuppDb);
		
		JButton btnajtab = new JButton("Créer table");
		btnajtab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final RethinkDB r = RethinkDB.r;
				Connection conn = r.connection().hostname("localhost").port(28015).connect();
				  r.db(bd.getText()).tableCreate(table.getText()).run(conn);
				
			}
		});
		btnajtab.setBounds(283, 208, 130, 23);
		contentPane.add(btnajtab);
		
		JButton btnCrerDb = new JButton("Créer Db");
		btnCrerDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final RethinkDB r = RethinkDB.r;
				Connection conn = r.connection().hostname("localhost").port(28015).connect();
				  r.dbCreate(bd.getText()).run(conn);
				 
			}
		});
		btnCrerDb.setBounds(283, 183, 130, 23);
		contentPane.add(btnCrerDb);
	}
}
