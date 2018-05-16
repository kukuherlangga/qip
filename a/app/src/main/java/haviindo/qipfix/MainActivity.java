package haviindo.qipfix;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import static android.content.Intent.ACTION_MAIN;

public class MainActivity extends AppCompatActivity {
    boolean valid1 = false;
    Button ButtonListPO,Submit,BtDatePicker,masukin;
    ListView listview;
    TextView op,b;
    String potrn,id, value,value2,category,namaitem,kategori,returnString,buid,supplierid,itembuid,label,coa,tipetruck;
    Spinner Spinnercat,spinneritem,spinnerdetail;
    Intent intent,intent2;
    ArrayList<String>cat;
    ArrayAdapter AD;
    ArrayList<String> item;
    EditText namaitemdetail,WRINNo,Supplier,DocNo,Itembuid,Date,negaraasal,truckno,seal,suhusetting,suhudisplay,suhuphyro,sealid;
    boolean valid = false;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    DatePicker simpleDatePicker;
    ScrollView scroolview;
    RadioButton Labelhalal, trucktype;
    int reqcode = 0;
    Map<String,String> hm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satu);
        masukin = (Button) findViewById(R.id.masukin);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        Date = (EditText) findViewById(R.id.date);
        BtDatePicker = (Button) findViewById(R.id.bt_datepicker);
        BtDatePicker.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
        scroolview = (ScrollView) findViewById(R.id.Scroolview);
        namaitemdetail = (EditText) findViewById(R.id.namaItem);
        namaitemdetail.setText(getIntent().getStringExtra("detaildata"));
        DocNo = (EditText)findViewById(R.id.DocNo);
        negaraasal = (EditText)findViewById(R.id.NegaraAsal);
        truckno = (EditText) findViewById(R.id.notruck);
        seal = (EditText) findViewById(R.id.noseal);
        suhusetting = (EditText) findViewById(R.id.SuhuSetting);
        suhudisplay = (EditText) findViewById(R.id.SuhuDisplay);
        suhuphyro = (EditText) findViewById(R.id.SuhuActual);
        masukin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hm = new HashMap<String,String>();
                hm.put("QIP_Doc_No",DocNo.getText().toString());
                hm.put("WRIN",WRINNo.getText().toString());
                hm.put("Item_Bu_Name",namaitemdetail.getText().toString());

            }
        });
        Submit = (Button) findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Map penInsert = new HashMap<>();
              java.util.Date c = Calendar.getInstance().getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");



              if (penInsert.isEmpty()){
                  penInsert.put("BU_ID",buid);
                  penInsert.put("QIP_Doc_No",DocNo.getText().toString());
                  penInsert.put("WRIN",WRINNo.getText().toString());
                  penInsert.put("Item_Bu_Id",itembuid);
                  penInsert.put("Item_Bu_Name",namaitemdetail.getText().toString());
//                  penInsert.put("QIP_Date",sdf.format(c));
                  penInsert.put("PO_NO",op.getText().toString());
                  penInsert.put("SupplierID",supplierid.trim());
                  penInsert.put("NegaraAsal",negaraasal.getText().toString());
                  penInsert.put("LabelHalal",label);
                  penInsert.put("CoA",coa);
                  penInsert.put("Truck_NO",truckno.getText().toString());
                  penInsert.put("SealId",seal.getText().toString());
                  penInsert.put("TruckType",tipetruck);
//                  penInsert.put("SuhuTruckSetting",Integer.parseInt(suhusetting.getText().toString()));
//                  penInsert.put("SuhuTruckDisplay",Integer.parseInt(suhudisplay.getText().toString()));
//                  penInsert.put("SuhuTruckPhyro",Integer.parseInt(suhuphyro.getText().toString()));

              }else{
                  penInsert.clear();
                  penInsert.put("BU_ID",buid);
                  penInsert.put("QIP_Doc_No",DocNo.getText().toString());
                  penInsert.put("WRIN",WRINNo.getText().toString());
                  penInsert.put("Item_Bu_Id",itembuid);
                  penInsert.put("Item_Bu_Name",namaitemdetail.getText().toString());
//                  penInsert.put("QIP_Date",Date.getText().toString());
                  penInsert.put("PO_NO",op.getText().toString());
                  penInsert.put("SupplierID",supplierid);
                  penInsert.put("NegaraAsal",negaraasal.getText().toString());
                  penInsert.put("LabelHalal",label);
                  penInsert.put("CoA",coa);
                  penInsert.put("Truck_NO",truckno.getText().toString());
                  penInsert.put("SealId",seal.getText().toString());
                  penInsert.put("TruckType",tipetruck);
//                  penInsert.put("SuhuTruckSetting",suhusetting.getText().toString());
//                  penInsert.put("SuhuTruckDisplay",suhudisplay.getText().toString());
//                  penInsert.put("SuhuTruckPhyro",suhuphyro.getText().toString());
              }

                if (kategori.equals("Beverage")){
                    Intent i = new Intent(MainActivity.this,Beverage1.class);
                    i.putExtra("array" , (Serializable) penInsert);
                    startActivity(i);
                 }
                 else if (kategori.equals("DryFood(Milo,etc)")) {
                    Intent i = new Intent(MainActivity.this, Dryfoodmilo1.class);
                    i.putExtra("array", (Serializable) penInsert);
                    startActivity(i);
                }
                else if (kategori.equals("Topping(Chilled)")) {
                    Intent i = new Intent(MainActivity.this, ToppingChilled1.class);
                    i.putExtra("array", (Serializable) penInsert);
                    startActivity(i);
                }
                else if (kategori.equals("Topping(Dry)")) {
                    Intent i = new Intent(MainActivity.this, ToppingDry1.class);
                    i.putExtra("array", (Serializable) penInsert);
                    startActivity(i);
                }
                else if (kategori.equals("Bakery-Muffin")) {
                    Intent i = new Intent(MainActivity.this, BakeryMuffin1.class);
                    i.putExtra("array", (Serializable) penInsert);
                    startActivity(i);
                }
            }
        });

        Spinnercat = (Spinner) findViewById(R.id.SpinnerCat);
        category();
        ArrayAdapter d = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,cat);
        Spinnercat.setAdapter(d);
        kategori = Spinnercat.getSelectedItem().toString();
        if (kategori != ""){
            Log.i("kategorinya",kategori);
        }else
        {
            Log.i("kategorinya","tidak ada");
        }
        Spinnercat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Step","6. masukin Item");
                kategori = Spinnercat.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinneritem = (Spinner) findViewById(R.id.SpinnerItem);
        spinneritem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int j, long k) {
                Log.i("Step","7. item dah masuk");
                namaitem = spinneritem.getItemAtPosition(j).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        op = (TextView) findViewById(R.id.PO);

        ButtonListPO = (Button) findViewById(R.id.ButtonListPO);
        ButtonListPO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListPO.class);
                Log.i("Step","4.List Po");
                startActivityForResult(intent,reqcode);

            }
        });

    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Date.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == reqcode) {
            if (resultCode == RESULT_OK) {

                // get String data from Intent
                returnString = data.getStringExtra("po");
                op.setText(returnString);

                Log.i("opnya",op.getText().toString());
                // set text view with string

                scroolview.setVisibility(View.VISIBLE);
                Log.i("Step","5. scrollview visible");

                item();

                spinneritem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int j, long k) {
                        Log.i("Step","7. item dah masuk");
                        namaitem = spinneritem.getItemAtPosition(j).toString();
                        item();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        }
    }

    public void category(){

        Log.i("Step","2. Categgory");
        Log.i("masuk","masuk");
        Connection connect;
        cat = new ArrayList<String>();
        String ConnectionResult;
        String kolom = "ProductCatName";
        String a = "[QIP_CHECKLIST].[dbo].[MF_QIP_TYPE]";
        boolean isSucces = false;
        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();
        ArrayList<String> np = new ArrayList<>();
        String CAT = getIntent().getStringExtra("CAT");

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check Your Internet Access";
            }else {
                String query = "select [QIP_CHECKLIST].dbo.MF_QIP_TYPE.ProductCatName From"+a;
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    cat.add(rs.getString("ProductCatName"));
                }
                ConnectionResult="Successfull";
                isSucces = true;
                Log.i("Step","3. mmasukin data product category name");

            }
        }catch (Exception ex){
            isSucces = false;
            ConnectionResult = ex.getMessage();
        }
    }

    public void item(){
        Log.i("masuk","masuk");
        Connection connect;
        ArrayList<String> item = new ArrayList<>();
        item.add("ITEM_BU_NAME");
        String ConnectionResult;
        String kolom = "ITEM_BU_NAME";
        String po = "";
        String a = "[QIP_CHECKLIST].[dbo].[TRX_PO_RN_INV]";
        boolean isSucces = false;
        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String,String>>();
        ArrayList<String> np = new ArrayList<>();
        /*Log.  i("nomor",NOPO);*/
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access";
            } else {
                String query = "select [QIP_CHECKLIST].[dbo].[TRX_PO_RN_INV].ITEM_BU_NAME," +
                        "[QIP_CHECKLIST].[dbo].[TRX_PO_RN_INV].BU_ID,[QIP_CHECKLIST].[dbo].[TRX_PO_RN_INV].SUPPLIER_ID" +
                        ",[QIP_CHECKLIST].[dbo].[TRX_PO_RN_INV].ITEM_BU_ID" +
                        " From "+a+" where [QIP_CHECKLIST].[dbo].TRX_PO_RN_INV.PO_NO ='"+returnString+"'";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                item = new ArrayList<>();
                while (rs.next()) {
                    item.add(rs.getString("ITEM_BU_NAME"));
                    buid = rs.getString("BU_ID");
                    supplierid = rs.getString("SUPPLIER_ID");
                    itembuid = rs.getString("ITEM_BU_ID");
                }
                    Log.i("masuk buid dan supid",buid+supplierid);
                ConnectionResult = "Succesfull";
                isSucces = true;
                ArrayAdapter AD = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,item);
                spinneritem.setAdapter(AD);
                spinneritem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int posisi, long l) {
                        namaitem = spinneritem.getItemAtPosition(posisi).toString();
                        namaitemdetail = (EditText) findViewById(R.id.namaItem);
                        namaitemdetail.setText(namaitem);
                        Log.i("result nama item",namaitem);
                        data();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        }catch (Exception ex){
            isSucces = false;
            ConnectionResult = ex.getMessage();
        }
//        Log.i("panjang",String.valueOf(item.size()));
    }

    public void data(){
        Log.i("Step","8. ngambil data");
        Connection connect;
        String ConnectionResult;
        boolean isSucces = false;
        String Item = namaitem;
//        Log.i("nomor",NOPO);
        //update
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access";
            } else {
                String query = "select [QIP_CHECKLIST].[dbo].TRX_PO_RN_INV.WRIN,[QIP_CHECKLIST].[dbo].TRX_PO_RN_INV.SUPPLIER_NAME From [QIP_CHECKLIST].[dbo].TRX_PO_RN_INV" +
                        " where [QIP_CHECKLIST].[dbo].TRX_PO_RN_INV.ITEM_BU_NAME='"+Item+"'";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next() != false) {
                    WRINNo = (EditText) findViewById(R.id.WRINNo);
                    Supplier = (EditText) findViewById(R.id.Supplier);
                    WRINNo.setText(rs.getString("WRIN"));

                    Supplier.setText(rs.getString("SUPPLIER_NAME"));
                    Log.i("resuult wrin",rs.getString("WRIN"));
                    Log.i("resuult suppliername",rs.getString("SUPPLIER_NAME"));
                }
                ConnectionResult = "Succesfull";
                isSucces = true;
            }
        }catch (Exception ex){
            isSucces = false;
            ConnectionResult = ex.getMessage();
        }
        Log.i("nama item",Item);

    }

   /* public void wrin (){

        Log.i("masuk","masuk");
        Connection connect;
        String ConnectionResult;
        boolean isSucces = false;

//        Log.i("nomor",NOPO);

        //update
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access";
            } else {
                String query = "UPDATE [QIP_CHECKLIST].[dbo].[TRX_QIP_CHECKLIST]" +
                        "SET [QIP_CHECKLIST].[dbo].[TRX_QIP_CHECKLIST].PO_NO = 'KukUH'" +
                        "WHERE [QIP_id] = 1;";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                String response = rs.getString("Messages");
                Log.i("a0",response);
                ConnectionResult = "Succesfull";
                isSucces = true;
            }
        }catch (Exception ex){
            isSucces = false;
            ConnectionResult = ex.getMessage();
        }
        Log.i("asdd",ConnectionResult);

    }*/

    public boolean validasi(){
        String NOPO = op.getText().toString().trim();
        if (NOPO.isEmpty()){
            Toast.makeText(getApplicationContext(), "PO# cannot be empty", Toast.LENGTH_LONG).show();
            valid = false;
            return valid;
        }else{
            valid = true;
            return valid;
        }
    }

    public void LabelHalalRadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.LabelHalaladasesuai:
                if (checked)
                    label = "LabelHalaladaSesuai";
                valid = true;
                break;
            case R.id.LabelHalaladatidaksesuai:
                if (checked)
                    label = "LabelHalaltidakSesuai";
                valid = true;
                break;
            case R.id.LabelHalaltidakada:
                if (checked)
                    label = "LabelHalaltidakada";
                valid = true;
                break;
        }
    }

    public void CoARadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.CoAadasesuai:
                if (checked)
                    coa = "CoAadasesuai";
                valid = true;
                break;
            case R.id.CoAadatidaksesuai:
                if (checked)
                    coa = "CoAadatidaksesuai";
                valid = true;
                break;
            case R.id.CoAtidakada:
                if (checked)
                    coa = "Coatidakada";
                valid = true;
                break;
        }
    }

    public void JenisTruckRadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.ReeferContainer:
                if (checked)
                    tipetruck = "ReeferContainer";
                valid = true;
                    break;
            case R.id.DryContainer:
                if (checked)
                    tipetruck = "DryContainer";
                    break;
        }
    }


    public void BagusLayakRadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.YBagusLayak:
                if (checked)

                    break;
            case R.id.NBagusLayak:
                if (checked)

                    break;
        }
    }


    public void BerbautajamRadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.YBerbautajam:
                if (checked)

                    break;
            case R.id.NBerbautajam:
                if (checked)
                    break;
        }


    }

    public void SegelutuhRadioBtn (View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.YSegelutuh:
                if (checked)
                    break;
            case R.id.NSegelutuh:
                if (checked)
                    break;
        }
    }

    public void InfestasihamaRadioBtn (View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.YInfestasihama:
                if (checked)
                    break;
            case R.id.NInfestasihama:
                if (checked)
                    break;
        }
    }

    public void KotorberdebuRadioBtn (View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.YKotorberdebu:
                if (checked)
                    break;
            case R.id.NKotorberdebu:
                if (checked)
                    break;
        }
    }

}


