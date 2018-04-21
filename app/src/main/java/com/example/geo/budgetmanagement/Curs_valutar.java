package com.example.geo.budgetmanagement;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Curs_valutar extends AppCompatActivity {
    private ListView listView;
    private Integer[] imageId={R.drawable.eur,R.drawable.usd};
    private Spinner spinner1;
    private Spinner spinner2;
    private EditText et1;
    private EditText et2;
    private Double eur;
    private Double usd;
    Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curs_valutar);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbarCurs);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        spinner1=(Spinner)findViewById(R.id.currency1);
        spinner2=(Spinner)findViewById(R.id.currency2);
        et1=(EditText)findViewById(R.id.currencymoney1);
        et2=(EditText)findViewById(R.id.currencymoney2);
        listView = (ListView)findViewById(R.id.listViewCursValutar);
        GetXML fir = new GetXML();
        fir.execute();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == android.R.id.home){
            Intent intent=new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void converter(View v){
        if(!spinner1.getSelectedItem().toString().equals("") && !listView.getAdapter().isEmpty()){
            String spinnertxt1=spinner1.getSelectedItem().toString();
            String spinnertxt2=spinner2.getSelectedItem().toString();
            Integer currency1=Integer.valueOf(et1.getText().toString());
            switch (spinnertxt1){
                case "RON":
                    switch (spinnertxt2){
                        case "RON":
                            et2.setText(currency1.toString());
                            break;
                        case "EUR":
                            Double val=Double.valueOf(currency1/eur);
                            et2.setText(String.format("%.2f",val));
                            break;
                        case "USD":
                            Double val1=Double.valueOf(currency1/usd);
                            et2.setText(String.format("%.2f",val1));
                            break;
                    }
                    break;

                case "EUR":
                    switch (spinnertxt2){
                        case "RON":
                            Double val=Double.valueOf(currency1*eur);
                            et2.setText(String.format("%.2f",val));
                            break;
                        case "EUR":
                            et2.setText(currency1.toString());
                            break;
                        case "USD":
                            Double val1=Double.valueOf(currency1*(1.18));
                            et2.setText(String.format("%.2f",val1));
                            break;
                    }
                    break;

                case "USD":
                    switch (spinnertxt2){
                        case "RON":
                            Double val=Double.valueOf(currency1*usd);
                            et2.setText(String.format("%.2f",val));
                            break;
                        case "EUR":
                            Double val1=Double.valueOf(currency1*0.84);
                            et2.setText(String.format("%.2f",val1));
                            break;
                        case "USD":
                            et2.setText(currency1.toString());
                            break;
                    }
                    break;

            }

        }

    }

    class GetXML extends AsyncTask<Void,Void, List<String>> {


        @Override
        protected List<String> doInBackground(Void... params) {
            List<String> lista = new ArrayList<String>();
            try {
                URL url = new URL("http://www.bnr.ro/nbrfxrates.xml");
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                url.openConnection();

                InputStream is = http.getInputStream();

                //pana aici e la fel mereu

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder docB=dbf.newDocumentBuilder();
                Document doc = docB.parse(is);


                if(doc!=null){
                    NodeList noduri = doc.getElementsByTagName("Rate");

                    for(int i = 0;i<noduri.getLength();i++){
                        Node nod = noduri.item(i);
                        if(nod!=null && nod.getNodeType() ==Node.ELEMENT_NODE){
                            Element element = (Element)nod;
                            if(element.getAttribute("currency").equals("EUR") || element.getAttribute("currency").equals("USD")) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(element.getAttribute("currency"));
                                sb.append("  - ");
                                sb.append(element.getTextContent());
                                if(element.getAttribute("currency").equals("EUR")){
                                    eur=Double.valueOf(element.getTextContent());
                                }
                                if(element.getAttribute("currency").equals("USD")){
                                    usd=Double.valueOf(element.getTextContent());
                                }
                                sb.append("  lei");
                                lista.add(sb.toString());
                            }
                        }
                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            return lista;
        }


        @Override
        protected void onPostExecute(List<String> strings) {
            CurrencyAdapter adapter = new CurrencyAdapter(Curs_valutar.this,strings,imageId);
            listView.setAdapter(adapter);


        }
    }

}
