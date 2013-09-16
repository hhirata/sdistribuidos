package br.ufmt.principal;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.text.TabExpander;

import com.sun.tools.hat.internal.parser.Reader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Principal extends Application {
	File caminho = null;
	
	@Override
	public void start(final Stage stage) throws Exception {
		// TODO Auto-generated method stub
		TabPane tb= new TabPane();
		
		Tab tbusca = new Tab();
		tbusca.setText("Busca");
		
		tbusca.setClosable(false);
		Tab tbaixar = new Tab();
		tbaixar.setText("Download");
		tbaixar.setClosable(false);
		tb.getTabs().addAll(tbusca,tbaixar);
		

		
		final Button bt = new Button("Baixar");
		
	
		
	/*	final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));*/
		bt.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
				File fl=null;
				FileChooser fc= new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
				fc.getExtensionFilters().add(extFilter);
				fl= fc.showOpenDialog(stage);
				Thread t = new Thread(new ClienteC(1024, 1, caminho.getAbsolutePath(), fl));
				t.start();
			
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
			}
		});
		
		
		HBox hb=  new HBox(3);
	
		final TextArea txtArea = new TextArea();



		tbaixar.setContent(hb);
		
		Button bt2 = new Button("Localização");
		
		
		bt2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DirectoryChooser chooser = new DirectoryChooser();
				caminho = chooser.showDialog(stage);
				try {
					new ServidorC(1024,caminho.getAbsolutePath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	               
				
			}
		});
		
		hb.getChildren().addAll(bt2,bt,txtArea);
		Scene sc= new Scene(tb);
		stage.setScene(sc);
		stage.setTitle("Compartilhador");
		stage.setMinHeight(600);
		stage.setMinWidth(800);
		stage.setMaxHeight(600);
		stage.setMaxWidth(800);
		stage.show();

		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
		
	}

}
