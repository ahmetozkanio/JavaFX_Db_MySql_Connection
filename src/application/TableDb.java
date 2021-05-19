package application;




import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.IsteMySql.Util.VeriTabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TableDb {
	
	
	public TableDb() {
		baglanti = VeriTabaniUtil.baglan();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<KayitlarLogin> table_view;

    @FXML
    private TableColumn<KayitlarLogin, Integer> col_id;

    @FXML
    private TableColumn<KayitlarLogin, String> col_kul;

    @FXML
    private TableColumn<KayitlarLogin, String> col_sifre;

    @FXML
    private TableColumn<KayitlarLogin, String> col_yetki;

    @FXML
    private TextField txt_kul_adi;

    @FXML
    private TextField txt_sifre;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_yetki;

    @FXML
    private Button btn_yenile;
    
    //database baglanti
  	Connection baglanti = null;
  	PreparedStatement sorguIfadesi=null;
  	ResultSet getirilen = null;
  	String sql;

    @FXML
    void btn_yenile_click(ActionEvent event) {

    }
    
    //*******************
    public void DegerleriGetir(TableView table) {
		sql = "select * from login ";
		
		ObservableList<KayitlarLogin> kayitlarListe= FXCollections.observableArrayList();
		
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			getirilen = sorguIfadesi.executeQuery();
			while (getirilen.next()) {
				kayitlarListe.add(new KayitlarLogin(getirilen.getInt("id"),
						getirilen.getString("kul_ad"),getirilen.getString("sifre")
								 ,getirilen.getInt("yetkinlik")));
				
			}
		
			col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			col_kul.setCellValueFactory(new PropertyValueFactory<>("kul_ad"));
			col_sifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
			col_yetki.setCellValueFactory(new PropertyValueFactory<>("yetki"));
			
			table_view.setItems(kayitlarListe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage().toString());
		}
	}
    

    @FXML
    void table_view_click(MouseEvent event) {
    		KayitlarLogin kayitlarLogin= new KayitlarLogin();
    		kayitlarLogin=(KayitlarLogin) table_view.getItems().get(table_view.getSelectionModel().getSelectedIndex());
    		txt_kul_adi.setText(kayitlarLogin.getKul_ad());
    		txt_sifre.setText(kayitlarLogin.getSifre());
    		lbl_id.setText(String.valueOf(kayitlarLogin.getId()));
    		if (kayitlarLogin.getYetki()==0) {
				lbl_yetki.setText("Normal Kullanici");
			}
    		else if (kayitlarLogin.getYetki()==1) {
				lbl_yetki.setText("Ust duzey kullanici");
			}
    }

    @FXML
    void initialize() {
DegerleriGetir(table_view);
    }
}
