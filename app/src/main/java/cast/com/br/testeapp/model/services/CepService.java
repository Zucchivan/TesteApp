package cast.com.br.testeapp.model.services;

import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cast.com.br.testeapp.R;
import cast.com.br.testeapp.controller.ClientCrudActivity;
import cast.com.br.testeapp.model.entity.ClientAddress;

/**
 * Created by Administrador on 27/07/2015.
 */
public final class CepService {

    private static final String URL = "http://correiosapi.apphb.com/cep/";

    private CepService() {
        super();
    }

    public static ClientAddress getAddressBy(String cep) throws Exception {
        ClientAddress address = null;
        try {
            URL url = new URL(URL + cep);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                Log.w("TAG", String.valueOf(R.string.serviceWarning));
                throw new Exception(String.valueOf(R.string.serviceWarning));
            }

            InputStream inputStream = conn.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            address = objectMapper.readValue(inputStream, ClientAddress.class);

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }


}
