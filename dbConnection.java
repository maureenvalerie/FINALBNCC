import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class dbConnection {

	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	
	public dbConnection() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PTPudding","root","");
			st = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			st.executeQuery("select * from menu");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertmenu(String KodeMenu, String NamaMenu, Integer HargaMenu, Integer StokMenu) {
		// TODO Auto-generated method stub
		try {
			ps = con.prepareStatement("insert into menu values (?,?,?,?);");
			ps.setString(1, KodeMenu);
			ps.setString(2, NamaMenu);
			ps.setInt(3, HargaMenu);
			ps.setInt(4, StokMenu);
			ps.execute();
			System.out.println("Berhasil");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Gagal");
		}
	}
	
	public ResultSet selectallmenu() {
		try {
			ps = con.prepareStatement("Select * from menu");
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return rs;
	}
	
	public ResultSet selectuser(String KodeMenu, String NamaMenu, Integer HargaMenu, Integer StokMenu) {
		// TODO Auto-generated method stub
		try {
			ps = con.prepareStatement("select * from menu where kodemenu = ? and namamenu = ? and hargamenu = ? and stokmenu = ?");
			ps.setString(1, KodeMenu);
			ps.setString(2, NamaMenu);
			ps.setInt(3, HargaMenu);
			ps.setInt(4, StokMenu);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void updatemenu(String KodeMenu,Integer HargaMenu, Integer StokMenu) {
		try {
			ps = con.prepareStatement("update menu set hargamenu = ?, stokmenu = ? where kodemenu = ?");
			ps.setString(1, KodeMenu);
			ps.setInt(3, HargaMenu);
			ps.setInt(4, StokMenu);
			ps.execute();
			System.out.println("Berhasil");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Gagal");
		}
	}
	
	public void deletemenu (String KodeMenu, String NamaMenu, Integer HargaMenu, Integer StokMenu) {
		try {
			ps = con.prepareStatement("Delete from menu where kodemenu = ?");
			ps.setString(1, KodeMenu);
			ps.setString(2, NamaMenu);
			ps.setInt(3, HargaMenu);
			ps.setInt(4, StokMenu);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Vector<String> getIduser() {
		Vector<String> id = new Vector<String>();
		
		try {
			ps = con.prepareStatement("Select KodeMenu from menu");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				id.add(rs.getObject(1).toString());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return id;
	}

	public void getDatauser(String kode) {
		try {
			ps = con.prepareStatement("Select HargaMenu, StokMenu from Menu");
			ps.setString(1, kode);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getObject(1));				
				System.out.println(rs.getObject(2));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
