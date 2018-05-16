package haviindo.qipfix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StatusPenerimaanActivity extends AppCompatActivity {
    EditText acc;
    TextView op,b;
    String regexStr = "^[0-9]$";
    CheckBox checkBox2, checkBox1, checkBox3, checkBox4;
    Map penInsert;
    String potrn, id, value, value2, category, namaitem, kategori, returnString, buid, supplierid, itembuid, label, coa, tipetruck;
    EditText namaitemdetail,WRINNo,Supplier,DocNo,Itembuid,Date,negaraasal,truckno,seal,suhusetting,suhudisplay,suhuphyro;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_penerimaan);

        penInsert = (HashMap) getIntent().getSerializableExtra("array");
        Set keys = penInsert.keySet();
        System.out.println("All keys are: " + keys);
// To get all key: value
        for(Object key: keys){
            System.out.println(key + ": " + penInsert.get(key));
        }
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        acc = (EditText) findViewById(R.id.acc);
        Submit = (Button) findViewById(R.id.Submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });

        }

    public void addData() {
        Log.i("Step", "10. add data");
        Connection connect;
        String ConnectionResult;
        boolean isSucces = false;

        Map lel = penInsert;
        String connection = "";
//        Set keys = lel.keySet();
        ArrayList apa = new ArrayList();
        ArrayList dimana = new ArrayList();
        ArrayList kenapa1 = new ArrayList();
        ArrayList kenapa2 = new ArrayList();
//        for (Iterator i = keys.iterator(); i.hasNext(); ) {
//
//            String key = (String) i.next();
//
//                try {
//                    String value = (String) lel.get(key);
//
//                }catch ( Exception e )
//                {
//                    Log.i("String","gkbisa");
//                }
//            try {
//                int value = (Integer) lel.get(key);
//            }catch ( Exception e )
//            {
//                Log.i("Integer","gkbisa");
//            }
//
//            apa.add(key);
//            dimana.add(value);
//        }

        Set key = penInsert.keySet();
        for (Iterator i = key.iterator(); i.hasNext(); ) {
            String kolom = (String) i.next();
            apa.add(kolom);
        }

//        for (int p = 0;p<penInsert.size();p++) {
//            apa.add(penInsert.get(key));
//        }
// To get all key: value
        for(Object keys: key){
            dimana.add(penInsert.get(keys));
            System.out.println(key + ": " + penInsert.get(keys));
        }
        for (int z=0 ; z<apa.size();z++){
            String coba = "["+apa.get(z)+"]";

            kenapa1.add(coba);
        }

        for (int z=0 ; z<dimana.size();z++){
            try {
             int asd = (int) dimana.get(z);
                String coba = ""+asd+"";
                System.out.println("pas masuk for" + ": " + dimana.get(z));
                kenapa2.add(coba);
            }catch (Exception e){
                Log.i("Integer","gkbisa");
            }
            try {
                String asd = (String) dimana.get(z);
                String coba = "'"+asd+"'";
                System.out.println("pas masuk for" + ": " + dimana.get(z));
                kenapa2.add(coba);
            }catch (Exception e){
                Log.i("String","gkbisa");
            }
            try {
                java.util.Date asd = (Date) dimana.get(z);
                String coba = "'"+asd+"'";
                System.out.println("pas masuk for" + ": " + dimana.get(z));
                kenapa2.add(coba);
            }catch (Exception e){
                Log.i("Date","gkbisa");
            }


        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sbd = new StringBuilder();
        for (int l = 0 ; l<kenapa1.size();l++) {
            if (l!=kenapa1.size()-1){
                sb.append(kenapa1.get(l));
                sb.append(",");
            }
            else if (l == kenapa1.size()-1) {
                sb.append(kenapa1.get(l));
            }
        }
        for (int l = 0 ; l<kenapa2.size();l++) {
            if (l!=kenapa2.size()-1){
                sbd.append(kenapa2.get(l));
                sbd.append(",");
            }
            else if (l == kenapa2.size()-1) {
                sbd.append(kenapa2.get(l));
            }
        }

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access";
            } else {
//                Log.i("valuenya",maumasuk.get(0).get(lel.keySet().toArray()[0]));
                Log.i("stringbuilder",sb.toString());
                Log.i("valuenya",sbd.toString());
                String query = "INSERT INTO [QIP_CHECKLIST].[dbo].TRX_QIP_CHECKLIST ("+sb.toString()+") VALUES ("+sbd.toString()+")";
                Log.i("query",query);
                Statement stmt = connect.createStatement();
                stmt.executeUpdate(query);
                ConnectionResult = "Succesfull";
                Log.i("conncetion",ConnectionResult);
                isSucces = true;

            }

        } catch (Exception ex) {
            isSucces = false;
            ConnectionResult = ex.getMessage();
        }
        Log.i("kkuh",ConnectionResult);
        Toast.makeText(this, ConnectionResult, Toast.LENGTH_SHORT).show();

    }
}
