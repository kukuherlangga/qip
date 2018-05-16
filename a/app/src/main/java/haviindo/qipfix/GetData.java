package haviindo.qipfix;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 10.500.000 on 07/05/2018.
 */

public class GetData {
    Connection connect;
    String ConnectionResult;
    boolean isSucces = false;

    public List<Map<String,String>> getdata(){
        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String,String>>();

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access";
            } else {
                String query = "select PO_NO FROM TRX_PO_RN_INV";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("PO_NO", rs.getString("PO_NO"));

                    data.add(datanum);
                }

                ConnectionResult = "Succesfull";
                isSucces = true;
                connect.close();
            }
        }catch (Exception ex){
            isSucces = false;
            ConnectionResult = ex.getMessage();
        }

        return data;
    }
}
