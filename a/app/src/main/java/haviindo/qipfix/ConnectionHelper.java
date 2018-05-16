package haviindo.qipfix;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 10.500.000 on 07/05/2018.
 */

public class ConnectionHelper {

    String IP, DB, DBUserName, DBPassword;

    @SuppressLint("NewAPI")
    public Connection connection(){
        IP = "117.120.7.181:1433";
        DB = "QIP_CHECKLIST";
        DBUserName = "vps";
        DBPassword = "vps321!";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;

        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            ConnectionURL ="jdbc:jtds:sqlserver://" + IP + ";Initial Catalog="+ DB + ";user="+ DBUserName + ";Password=" + DBPassword + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }catch (SQLException se){
            Log.e("error from SQL",se.getMessage());
        }
        catch (ClassNotFoundException e) {
            Log.e("Error From Class",e.getMessage());
        }
        catch (Exception ex) {
            Log.e("Error From Class",ex.getMessage());
        }

        return connection;
    }
}
