package br.pucminas.controledetemperatura;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lisDados;
    Button btnConsultarDados;
    private Context context;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        lisDados = (ListView) findViewById(R.id.lisDados);

        /*
        ArrayAdapter<Feeds> adapter = new ArrayAdapter<Feeds>(this, android.R.layout.simple_list_item_1, feeds);
        lisDados.setAdapter(adapter); */

        //Evento do botão
        btnConsultarDados = (Button) findViewById(R.id.btnConsultarDados);
        btnConsultarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new HttpRequestTask().execute();
            }
        });

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, ListaFeeds> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(context);
            dialog = ProgressDialog.show(context, "Aguarde", "Buscando dados...", false, true);
        }

        @Override
        protected ListaFeeds doInBackground(Void... voids) {

            try {

                final String url = "https://api.thingspeak.com/channels/349784/feeds.json?api_key=G9P52GPM1WVZZQ99";
                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                ListaFeeds listaFeeds = restTemplate.getForObject(url, ListaFeeds.class);

                return listaFeeds;


            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(ListaFeeds listaFeeds) {
            super.onPostExecute(listaFeeds);

            dialog.dismiss();

            List<Feeds> feedsRecebidos = listaFeeds.getFeeds();
            if (feedsRecebidos.size() > 0) {

            }
            ArrayAdapter<Feeds> adapter = new ArrayAdapter<Feeds>(context, android.R.layout.simple_list_item_1, feedsRecebidos);
            lisDados.setAdapter(adapter);
        }
    }
}
