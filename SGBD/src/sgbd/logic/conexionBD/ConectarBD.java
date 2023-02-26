/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgbd.logic.conexionBD;
import java.sql.*;//import the necessary classes
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Hector
 */
public class ConectarBD {
    private String URLBD;//direccion de la base de datos
    Connection conexion = null;
    private static String access="access";
    private static String postgresql="postgresql";
    private static String DriverAccess ="net.ucanaccess.jdbc.UcanaccessDriver";
    private static String DriverPostgresql ="org.postgresql.Driver";
    private String UrlSource;
    private String typeDB;
    //estas variables se usan cuando se use postgresql
    private String usser;
    private String password;
    private String bd;
    private String ip;
    private String port;
    
    
    /**
     * @author César
     * @param String tipoBD Este param tiene el tipo de base de dato a la cual se va a conectar el SGBD
     * @return true or false si la conexion fue satisfactoria o no
     */
    private boolean establecerConexion(String tipoBD){
        boolean isValid = false;
        
        String driver=null;
        //aqui se define el tipo de bd que se va a manejar para instalar los driver correspondientes
        if(tipoBD.toLowerCase().equals(access)){
            typeDB=access;
            driver=DriverAccess;
        }else if(tipoBD.toLowerCase().equals(postgresql)){
            typeDB=postgresql;
            driver=DriverPostgresql;
        }
        
        try {
            if(typeDB != null){
                //Load the driver class
                Class.forName(driver);
                if(typeDB.equals(access)){
                    conexion = DriverManager.getConnection("jdbc:ucanaccess://"+UrlSource);
                }else if(typeDB.equals(postgresql)){
                    conexion = DriverManager.getConnection("jdbc:postgresql://"+UrlSource,usser,password);
                }
                isValid =conexion.isValid(50000);
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, e);
        }
        return isValid;
    }

    public ConectarBD(String usser, String password, String bd, String ip, String port) {
        this.usser = usser;
        this.password = password;
        this.bd = bd;
        this.ip = ip;
        this.port = port;
    }
    
}
