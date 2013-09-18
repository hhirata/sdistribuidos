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


import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.management.ObjectInstance;
import javax.swing.text.TabExpander;
import javax.xml.bind.JAXBException;

import br.ufmt.checksum.CheckSum;
import br.ufmt.dados.DadosArquivo;
import br.ufmt.dados.TrataXMLDados;
import br.ufmt.publica.Publica;
import br.ufmt.publica.TrataXmlPlub;

import com.sun.tools.hat.internal.parser.Reader;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Principal extends Application {
	File caminho = null;
	File caminho2 = null;
	File fl=null;
	File fl2 = null;
	TableView tabela = new TableView<>();
	TableView tabela2 = new TableView<>();
	TableView tabela3 = new TableView<>();
	TableView tabela4 = new TableView<>();
	DadosArquivo novo = new DadosArquivo();
	ArrayList<DadosArquivo> da = new ArrayList<>();
	final ObservableList<ArquivoTabela> dados = FXCollections.observableArrayList();
	final ObservableList<IpTabela> dados2 = FXCollections.observableArrayList();
	@Override
	public void start(final Stage stage) throws Exception {
		
		final StackPane st = new StackPane();
		
		TabPane tb= new TabPane();
		
		final Tab tbusca = new Tab();
		tbusca.setText("Busca");
		
		tbusca.setClosable(false);
		Tab tbaixar = new Tab();
		tbaixar.setText("Download");
		tbaixar.setClosable(false);
		tb.getTabs().addAll(tbaixar,tbusca);
		

		ImageView imgDown = new ImageView("File:img/down.png");
		Button bt = new Button();
		bt.setStyle("-fx-background-color: rgba(0, 0, 0, 0);"); 
		imgDown.setFitHeight(48);
		imgDown.setFitWidth(48);
		bt.setGraphic(imgDown);
		
		
		ImageView imgServ = new ImageView("File:img/server.png");
		Button bt2 = new Button();
		bt2.setStyle("-fx-background-color: rgba(0, 0, 0, 0);"); 
		imgServ.setFitHeight(48);
		imgServ.setFitWidth(48);
		bt2.setGraphic(imgServ);
		
		ImageView imgUp = new ImageView("File:img/up.png");
		Button bt3 = new Button();
		bt3.setStyle("-fx-background-color: rgba(0, 0, 0, 0);"); 
		imgUp.setFitHeight(48);
		imgUp.setFitWidth(48);
		bt3.setGraphic(imgUp);
				
		final Label lblServer= new Label("Diretório Servidor:");
		final Label lblDown= new Label("Diretório Downloads:");
		
		
		final AnchorPane anchorDown = new AnchorPane();
		GridPane gridDownload = new GridPane();
		final Label lblArquivo= new Label("Nome do Arquivo:");
		final Label lblDir= new Label("Diretório Downloads:");
		final TextField txtNome = new TextField();
		final TextField txtDir = new TextField();
		Button btSeleciona = new Button("Selecionar Arquivo");
		Button btSelecionaDir = new Button("Selecionar Dir");
		final Button btOK = new Button("OK");
		txtNome.setEditable(false);
		txtDir.setEditable(false);
		btOK.setDisable(true);
		Button btCancelar = new Button("Cancelar");
		gridDownload.setVgap(4);
		gridDownload.setHgap(4);
		gridDownload.setPadding(new Insets(5, 5, 5, 5));
		lblArquivo.setStyle("-fx-background-color: rgba(10, 10, 0, 0);-fx-font-weight:bold;-fx-text-fill: #FFFFFF;-fx-font-size: 12pt;");
		lblDir.setStyle("-fx-background-color: rgba(10, 10, 0, 0);-fx-font-weight:bold;-fx-text-fill: #FFFFFF;-fx-font-size: 12pt;");
		gridDownload.add(lblArquivo, 1, 0);
		gridDownload.add(txtNome, 2, 0);
		gridDownload.add(btSeleciona, 3, 0);
		gridDownload.add(lblDir, 1, 1);
		gridDownload.add(txtDir, 2, 1);
		gridDownload.add(btSelecionaDir,3, 1);
		gridDownload.add(btCancelar,2, 2);
		gridDownload.add(btOK,3, 2);
		anchorDown.setMaxHeight(100);
		anchorDown.setMaxWidth(500);
		anchorDown.getChildren().add(gridDownload);
		anchorDown.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius:5;");
		
		
		final AnchorPane anchorUp = new AnchorPane();
		GridPane gridUp = new GridPane();
		final Label lblArquivoUp= new Label("Nome do Arquivo:");
		lblArquivoUp.setStyle("-fx-background-color: rgba(10, 10, 0, 0);-fx-font-weight:bold;-fx-text-fill: #FFFFFF;-fx-font-size: 12pt;");
	    final TextField txtNomeUp = new TextField();
	    Button btSelecionaUp = new Button("Selecionar Arquivo");
	    final Button btOKUp = new Button("OK");
	    btOKUp.setDisable(true);
	    Button btCancelaUp = new Button("Cancelar");
		btOKUp.setDisable(true);
		gridUp.setVgap(4);
		gridUp.setHgap(4);
		gridUp.setPadding(new Insets(5, 5, 5, 5));
		gridUp.add(lblArquivoUp, 1, 0);
		gridUp.add(txtNomeUp, 2, 0);
		gridUp.add(btSelecionaUp, 3, 0);
		gridUp.add(btCancelaUp, 2, 1);
		gridUp.add(btOKUp, 3, 1);
		anchorUp.setMaxHeight(50);
		anchorUp.setMaxWidth(500);
		anchorUp.getChildren().add(gridUp);
		anchorUp.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius:5;");
		
		TableColumn coluna1 = new TableColumn("Nome");
		coluna1.setCellValueFactory(new PropertyValueFactory<ArquivoTabela,String>("nome"));
		TableColumn coluna2 = new TableColumn("Tamanho");
		coluna2.setCellValueFactory(new PropertyValueFactory<ArquivoTabela,String>("tamanho"));
		TableColumn coluna3 = new TableColumn("IPs");
		coluna3.setCellValueFactory(new PropertyValueFactory<IpTabela,String>("ip"));
		
		TableColumn coluna4 = new TableColumn("Nome");
		coluna1.setCellValueFactory(new PropertyValueFactory<ArquivoTabela,String>("nome"));
		TableColumn coluna5 = new TableColumn("Tamanho");
		coluna2.setCellValueFactory(new PropertyValueFactory<ArquivoTabela,String>("tamanho"));
		TableColumn coluna6 = new TableColumn("IPs");
		coluna3.setCellValueFactory(new PropertyValueFactory<IpTabela,String>("ip"));
		
		tabela.setEditable(false);
		tabela.setMinWidth(500);
		tabela.setMaxSize(500, 200);
		tabela.getColumns().addAll(coluna1,coluna2);
		tabela.setItems(dados);
		
		tabela2.setEditable(false);
		tabela2.setMinWidth(200);
		tabela2.setMaxSize(500, 200);
		tabela2.getColumns().addAll(coluna3);
		tabela2.setItems(dados2);
		
		
		tabela3.setEditable(false);
		tabela3.setMinWidth(500);
		tabela3.setMaxSize(500, 200);
		tabela3.getColumns().addAll(coluna1,coluna2);
		tabela3.setItems(dados);
		
		tabela4.setEditable(false);
		tabela4.setMinWidth(200);
		tabela4.setMaxSize(500, 200);
		tabela4.getColumns().addAll(coluna3);
		tabela4.setItems(dados2);
		
		VBox vbCont = new VBox(4);
	    vbCont.setSpacing(10);
	    vbCont.setAlignment(Pos.TOP_LEFT);
		
		
		VBox vbMenu = new VBox();
	    vbMenu.setSpacing(10);
	    vbMenu.setAlignment(Pos.CENTER);
	    
		HBox hbBaixar=  new HBox();
		

		VBox vbBusca = new VBox();
		GridPane gridBusca = new GridPane();
		gridBusca.setVgap(4);
		gridBusca.setHgap(4);
		gridBusca.setPadding(new Insets(5, 5, 5, 5));
		TextField txtBusca = new TextField();
		Button btBusca = new Button();
		ImageView imgBusca = new ImageView("File:img/search.png");
		imgBusca.setFitHeight(24);
		imgBusca.setFitWidth(24);
		btBusca.setGraphic(imgBusca);
		btBusca.setStyle("-fx-background-color: rgba(0, 0, 0, 0);"); 
		Button btDown = new Button();
		btDown.setStyle("-fx-background-color: rgba(0, 0, 0, 0);"); 
		ImageView imgD = new ImageView("File:img/down.png");
		imgD.setFitHeight(24);
		imgD.setFitWidth(24);
		btDown.setGraphic(imgD);
		
		gridBusca.add(txtBusca, 1, 0);
		gridBusca.add(imgBusca, 2, 0);
		
		vbBusca.getChildren().addAll(gridBusca,tabela3,tabela4);
		vbBusca.setAlignment(Pos.CENTER);
		
		bt.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
//
//				File fl=null;
//				FileChooser fc= new FileChooser();
//				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
//				fc.getExtensionFilters().add(extFilter);
//				fl= fc.showOpenDialog(stage);
//				Platform.runLater( new Thread(new ClienteC(1024, 1, caminho2.getAbsolutePath(), fl)));
				
				st.getChildren().add(anchorDown);
								
			}
		});
		
		
		bt2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DirectoryChooser chooser = new DirectoryChooser();
				chooser.setTitle("Selecione o Diretório do Servidor");
				caminho = chooser.showDialog(stage);
				lblServer.setText("Diretório Servidor: "+caminho.getAbsolutePath());
				try {
					new ServidorC(1024,caminho.getAbsolutePath());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	               
				
			}
		});
		
		btSeleciona.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
				FileChooser fc= new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
				fc.getExtensionFilters().add(extFilter);
				fc.setTitle("Selecionar o Arquivo");
				fl= fc.showOpenDialog(stage);
				novo = new TrataXMLDados().le(fl);
				txtNome.setText(novo.getNome());
				if (fl!=null && caminho2!=null){
					btOK.setDisable(false);
				}
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		bt3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
//
//				File fl=null;
//				FileChooser fc= new FileChooser();
//				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
//				fc.getExtensionFilters().add(extFilter);
//				fl= fc.showOpenDialog(stage);
//				Platform.runLater( new Thread(new ClienteC(1024, 1, caminho2.getAbsolutePath(), fl)));
				
				st.getChildren().add(anchorUp);
								
			}
		});
		
		
		btSelecionaUp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				FileChooser fc= new FileChooser();
				fc.setTitle("Selecionar o Arquivo para a publicação");
				fl2= fc.showOpenDialog(stage);
			    txtNomeUp.setText(fl2.getAbsolutePath());
			    btOKUp.setDisable(false);

				}
		});
		
		btSelecionaDir.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				DirectoryChooser chooser = new DirectoryChooser();
				chooser.setTitle("Selecionar Local para Download");
				caminho2 = chooser.showDialog(stage);
				txtDir.setText(caminho2.getAbsolutePath());
				tbusca.setDisable(false);
				lblDown.setText("Diretório Downloads: "+caminho2.getAbsolutePath());
				if (fl!=null && caminho2!=null){
					btOK.setDisable(false);
				}
			}
		});
		
		btOK.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				da.add(novo);
				dados.add(new ArquivoTabela(novo.getNome(), novo.getTamanho()));
				st.getChildren().remove(anchorDown);
				new Thread(new ClienteC(1024, 1, caminho2.getAbsolutePath(), fl)).start();;
				
			}
		});
		
		btOKUp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				InetAddress addr;
				try {
					addr = InetAddress.getLocalHost();
					Publica pb = new Publica();
					pb.setNome(fl2.getName());
					pb.setTamanho((int)fl2.length());
					pb.setMd5(new CheckSum(fl2.getAbsolutePath()).calculaMD5());
					pb.setIp(addr.getHostAddress());
					String bff = new TrataXmlPlub().criaXmlPubl(pb); 
					System.out.println(bff);
					st.getChildren().remove(anchorUp);
				} catch (UnknownHostException | JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
				
			}
		});
		
		btCancelar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				st.getChildren().remove(anchorDown);
			}
		});
		btCancelaUp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				st.getChildren().remove(anchorUp);
			}
		});
		
		tabela.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				dados2.clear();
				int i =tabela.getSelectionModel().getSelectedIndex();
				if(i>=0){
				DadosArquivo  info= da.get(i);
				ArrayList<IpTabela>ips = new ArrayList<>();
				for(String str : info.getIp()){
					ips.add(new IpTabela(str));
				}
				dados2.addAll(ips);
				}
			}
			
		});
		
		btBusca.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		vbCont.setAlignment(Pos.CENTER);
		vbCont.getChildren().addAll(lblServer,lblDown,tabela,tabela2);
		vbMenu.getChildren().addAll(bt2,bt,bt3);
		hbBaixar.getChildren().addAll(vbMenu,vbCont);
		
		st.getChildren().add(hbBaixar);
		tbaixar.setContent(st);
		tbusca.setContent(vbBusca);
		tbusca.setDisable(true);
		

		Scene sc= new Scene(tb);
		stage.setScene(sc);
		stage.setTitle("Compartilhador");
		stage.setMinHeight(600);
		stage.setMinWidth(800);
		stage.setMaxHeight(600);
		stage.setMaxWidth(800);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		stage.show();
		

		
	}

	
	public static class ArquivoTabela{
		private final SimpleStringProperty nome;
		private final SimpleIntegerProperty tamanho;
		
		private ArquivoTabela(String fnome, int ftamanho){
			this.nome= new SimpleStringProperty(fnome);
			this.tamanho= new SimpleIntegerProperty(ftamanho);
			
			
		}
		
		public SimpleStringProperty nomeProperty() {
			return nome;
		}

		public SimpleIntegerProperty tamanhoProperty() {
			return tamanho;
		}
		
		
	}
	
	public static class IpTabela{
		private final SimpleStringProperty ip;
	
		private IpTabela(String fip){
			this.ip= new SimpleStringProperty(fip);
						
		}
		
		public SimpleStringProperty ipProperty() {
			return ip;
		}


		
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
		
	}

}
