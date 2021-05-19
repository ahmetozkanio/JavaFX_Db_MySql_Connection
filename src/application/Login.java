package application;
import java.sql.*;



import com.IsteMySql.Util.VeriTabaniUtil;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login {

	public Login() {
		baglanti = VeriTabaniUtil.baglan();
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_login;

    @FXML
    private TextField k_adi;

    @FXML
    private TextField sifre;

    @FXML
    private Label label;

    
    @FXML
    void btn_ekle_click(ActionEvent event) {
    	sql = "insert into login(kul_ad,sifre,yetkinlik) values(?,?,?)";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, k_adi.getText().trim());
			sorguIfadesi.setString(2, VeriTabaniUtil.mD5Sifrelemesi(sifre.getText().trim()));
			sorguIfadesi.setString(3, "0");

			sorguIfadesi.executeUpdate();
			
			label.setText("Kullanici Eklendi");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			label.setText(e.getMessage().toString());
		
		}
    }

    @FXML
    void btn_guncelle_click(ActionEvent event) {
//MD5 veya SHA256
    	sql = "update login set  sifre=? where kul_ad=?";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(2, k_adi.getText().trim());
			sorguIfadesi.setString(1, VeriTabaniUtil.mD5Sifrelemesi(sifre.getText().trim()));
			sorguIfadesi.executeUpdate();
			
			label.setText("Guncellendi");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			label.setText(e.getMessage().toString());
		
		}
    }

    @FXML
    void btn_login_click(ActionEvent event) {

    	sql = "select * from login where kul_ad=? and sifre=?";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, k_adi.getText().trim());
			sorguIfadesi.setString(2, VeriTabaniUtil.mD5Sifrelemesi(sifre.getText().trim()));
			
			getirilen = sorguIfadesi.executeQuery();
			
			if (!getirilen.next()) {
				label.setText("Kullanici adi veya sifre hatali");
			}
			else {
				getirilen.getString(1);//Tabloda 1. sutundaki deger;
				System.out.println(
						"kID:"+getirilen.getString("id")
						+"\n"+
						"kullanici :"+getirilen.getString("kul_ad")
						+"\n"+
						"sifre :"+getirilen.getString("sifre")
						+"\n"+
						"Yetki : "+getirilen.getString("yetkinlik")
						);
				label.setText("Giris Onaylandi");
			}
    		
		} catch (Exception e) {
			// TODO: handle exception
			label.setText(e.getMessage().toString());
		}
    }

    @FXML
    void btn_sil_click(ActionEvent event) {
    	sql = "delete from login where  kul_ad=? and sifre=?";
    	try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.setString(2, VeriTabaniUtil.mD5Sifrelemesi(sifre.getText().trim()));
			sorguIfadesi.setString(1, k_adi.getText().trim());
			sorguIfadesi.executeUpdate();
			
			label.setText("Kullanici silindi..");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			label.setText(e.getMessage().toString());
		
		}
    }
    //database baglanti
	Connection baglanti = null;
	PreparedStatement sorguIfadesi=null;
	ResultSet getirilen = null;
	String sql;
    
    @FXML
    void initialize() {

    }
}
