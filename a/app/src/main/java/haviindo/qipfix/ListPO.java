package haviindo.qipfix;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListPO extends AppCompatActivity {

    ListView lvData;
    SimpleAdapter AD;
    Button getdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_po);

        lvData = (ListView) findViewById(R.id.lvData);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selection =  ((TextView) view).getText().toString();
                Intent intent = new Intent();
                intent.putExtra("po",selection);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        asd();
    }

    public void asd(){
        Log.i("masuk","masuk");
        Connection connect;
        String ConnectionResult;
        String kolom = "PO_NO";
        String po = "";
        String a = "[QIP_CHECKLIST].[dbo].[TRX_PO_RN_INV]";
        boolean isSucces = false;
        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String,String>>();
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access";
            } else {
                String query = "select DISTINCT [QIP_CHECKLIST].[dbo].[TRX_PO_RN_INV].PO_NO From "+a;
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("PO_NO", rs.getString("PO_NO"));
                    data.add(datanum);
                }
                String from[] = {"PO_NO"};
                int to[] = {android.R.id.text1};

                SimpleAdapter AD = new SimpleAdapter(this,data,android.R.layout.simple_spinner_item,from,to);

                lvData.setAdapter(AD);
                ConnectionResult = "Succesfull";
                isSucces = true;
            }
        }catch (Exception ex){
            isSucces = false;
            ConnectionResult = ex.getMessage();
        }
        Log.i("koneksi",ConnectionResult);
    }
}
