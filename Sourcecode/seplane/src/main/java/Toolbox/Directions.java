package Toolbox;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Directions {
    public void testDirection() throws IOException {
        URL url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=Kanzlerstra%C3%9Fe+14+47119+duisburg&destination=Friedrichsplatz+2+47119+duisburg&key=AIzaSyCueqaRjrGLGd6mYhJCpRvnkoDpOz3PgYo");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int status = connection.getResponseCode();
        System.out.println(status);
        switch (status) {
            case 200:
                BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));


                Gson gson = new GsonBuilder()
                        .setLenient()
                        .registerTypeAdapter(int.class, new EmptyStringToNumberTypeAdapter())
                        .registerTypeAdapter(Integer.class, new EmptyStringToNumberTypeAdapter())
                        .registerTypeAdapter(double.class, new EmptyStringToNumberTypeAdapter())
                        .registerTypeAdapter(Double.class, new EmptyStringToNumberTypeAdapter())
                        .create();

                List<String> di = (List<String>) gson.fromJson(bf,  Directions.class);
                System.out.println(di);
        }


    }

//    public HttpResponse http(String url2, String body) throws MalformedURLException {
//        URL url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=Kanzlerstra%C3%9Fe+14+47119+duisburg&destination=Friedrichsplatz+2+47119+duisburg&key=AIzaSyCueqaRjrGLGd6mYhJCpRvnkoDpOz3PgYo");
//
//        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
//            HttpPost request = new HttpPost(String.valueOf(url2));
//            StringEntity params = new StringEntity(body);
//            request.addHeader("content-type", "application/json");
//            request.setEntity(params);
//            HttpResponse result = httpClient.execute(request);
//            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
//
//            com.google.gson.Gson gson = new com.google.gson.Gson();
//            String di = gson.toJson(request, DIrections.class);
//
//
//            System.out.println(di);
//
//        } catch (IOException ex) {
//        }
//        return null;
//    }

    public static void main(String[] args) throws IOException {
        new Directions().testDirection();
    }



}
