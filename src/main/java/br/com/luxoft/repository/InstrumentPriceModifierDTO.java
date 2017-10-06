package br.com.luxoft.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Repository;

@Repository
public class InstrumentPriceModifierDTO {

	public static BigDecimal instrument(String instru) throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement stmt = null;
		String nome = null;
		BigDecimal valor = null;
		
		String driver = "org.postgresql.Driver";
        String user = "postgres";
        String pass = "root";
        String url = "jdbc:postgresql://localhost:5432/luxoft";
		
		Class.forName(driver);  
		
		con = DriverManager.getConnection(url,user,pass);
		stmt = con.createStatement();									
		ResultSet  resultSet = stmt.executeQuery(query(instru));
		while(resultSet.next()){
			nome = resultSet.getString("instrument_name");
			valor = new BigDecimal(resultSet.getString("multiplier"));
		}
		stmt.close();
		con.close();
		
		return valor;
	}
	
	public static String query(String instru) {
		String query;
		
		query = "select instrument_name, multiplier from public.instrument_price_modifier where instrument_name ='"+instru+"'";
		
		return query;		
	}
}
