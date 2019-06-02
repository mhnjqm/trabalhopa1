package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mvc.model.Medidores;
import mvc.model.Medidas;
import java.time.LocalDate;


public class databaseDAO extends BaseDAO {
    
    //ResultSet rst;
    //PreparedStatement pstmt;
    String MEDIDOR_TABLE_PREFIX = "medidor";
    
    
    // CREATE 
    public boolean doCreate(String medidor_nome, String tabela) { // boolean? // medidores
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "INSERT INTO medidores (nome, tabela) VALUES(?,?) RETURNING serialno_medidores;");
            pstmt.setString(1, medidor_nome);
            pstmt.setString(2, tabela);
            ResultSet rst = pstmt.executeQuery();
            rst.next();
            
            String serialno_medidores = rst.getString("serialno_medidores");
            String newTableName = MEDIDOR_TABLE_PREFIX+serialno_medidores;
            pstmt = con.prepareStatement(
                "CREATE TABLE public."+newTableName+"(\n" +
                "    serialno serial PRIMARY KEY,\n" +
                "    medidor text NOT NULL,\n" +
                "    temperatura text NOT NULL,\n" +
                "    umidade text NOT NULL,\n" +
                "    datahora timestamp with time zone NOT NULL,\n" +
                "    serial text NOT NULL\n" +
                ");"      
            );
            pstmt.execute();
                    
            pstmt = con.prepareStatement(
               "UPDATE medidores SET tabela=? WHERE serialno_medidores=?;");
            pstmt.setString(1, newTableName);
            pstmt.setInt(2, Integer.valueOf(serialno_medidores));
            pstmt.execute();
            
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    // READ (MEDIDORES)
    public ArrayList<Medidores> doReadMedidores() { // pega td medidores
        try {
            Connection con = getConnection( );
            PreparedStatement pstmt = con.prepareStatement(
               "SELECT * FROM medidores;");
            ResultSet rst = pstmt.executeQuery();

            ArrayList<Medidores> arrayList = new ArrayList(); 
            while (rst.next()) {              
                Medidores medidor = new Medidores(); 
                medidor.setSerialno(rst.getString(1));
                medidor.setNome(rst.getString(2));
                medidor.setTabela(rst.getString(3));
                arrayList.add(medidor);
            }
            con.close();
            return arrayList;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
    
    // READ (MEDIDAS)
    public ArrayList<Medidas> doReadMedidas(String medidor_serial, LocalDate datafinal) {
        try {
            Connection con = getConnection();
            String sql = "SELECT * FROM "
                         + ""+MEDIDOR_TABLE_PREFIX+medidor_serial+""
                         + "WHERE datahora <= ?;"+";";
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            
            ArrayList<Medidas> arrayList = new ArrayList(); 
            while (rst.next()) {              
                Medidas medida = new Medidas(); 
                medida.setSerialno(rst.getString(1));
                medida.setMedidor(rst.getString(2));
                medida.setTemperatura(rst.getString(3));
                medida.setUmidade(rst.getString(4));
                medida.setDatahora(rst.getString(5));
                medida.setSerial(rst.getString(6));
                arrayList.add(medida);
            }
            con.close();
            return arrayList;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
     
    // UPDATE (MEDIDORES)
    public boolean doUpdate(String medidor_serialno, String medidor_nome) {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "UPDATE medidores SET nome=? WHERE serialno_medidores=?;");
            pstmt.setString(1, medidor_nome);
            pstmt.setInt(2, Integer.valueOf(medidor_serialno));
            pstmt.execute();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    // DELETE (MEDIDORES)
    public boolean doDelete(String medidor_serialno){ // tentar String serialno?
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "DELETE FROM campos WHERE serialno_medidores=?;");
            pstmt.setInt(1, Integer.valueOf(medidor_serialno));
            pstmt.execute();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
        
}
