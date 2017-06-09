package hello.maven;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.ast.Db;
import com.rethinkdb.gen.ast.ForEach;
import com.rethinkdb.gen.exc.ReqlError;
import com.rethinkdb.gen.exc.ReqlQueryLogicError;
import com.rethinkdb.model.Arguments;
import com.rethinkdb.model.MapObject;


import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.awt.event.ActionEvent;

public class affiche extends JFrame {

	private JPanel contentPane;
	private JTextField output;
	private JTextField table;
	private JTextField db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					affiche frame = new affiche();
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
	public affiche() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		output = new JTextField();
		output.setBounds(32, 100, 402, 155);
		contentPane.add(output);
		output.setColumns(10);
		
		JButton btnAfficher = new JButton("Afficher");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final RethinkDB r = RethinkDB.r;
				Connection conn = r.connection().hostname("localhost").port(28015).connect();
			//	r.db(db.getText()).table(table.getText()).sample(3).run(conn);
				Cursor cursor = r.db(db.getText()).table(table.getText()).run(conn);
				 HashSet<Object> hs = new HashSet();
				for (Object doc : cursor) {
				   hs.add(doc);
				}
				
			}
			
		});
		btnAfficher.setBounds(50, 63, 89, 23);
		contentPane.add(btnAfficher);
		
		table = new JTextField();
		table.setBounds(322, 49, 86, 20);
		contentPane.add(table);
		table.setColumns(10);
		
		db = new JTextField();
		db.setBounds(322, 11, 86, 20);
		contentPane.add(db);
		db.setColumns(10);
	}
}
