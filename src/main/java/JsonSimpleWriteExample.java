import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import jdk.nashorn.api.scripting.URLReader;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


public class JsonSimpleWriteExample {
    public static void main(String[] args) throws IOException {

        Gson gson = new Gson();
        StringBuilder result = new StringBuilder();
    try{

      //Object obj = parser.parse(new FileReader("C:\\Users\\Kudret Batuhan\\Desktop\\JiraSync\\MavenJsonExample\\src\\resource\\example.json"));
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Kudret Batuhan\\Desktop\\JiraSync\\MavenJsonExample\\src\\resource\\example.json"));

        Example ilkjson = gson.fromJson(br, Example.class);

        System.out.println("Objenin İlk Value Değeri  : "+ilkjson.getExpand());

        String arr = ilkjson.getIssues().get(1).getExpand();

        String usermail = ilkjson.getIssues().get(1).getFields().getCreator().getEmailAddress();

        System.out.println("Arry içi " +arr);

        System.out.println("UserMail"+usermail);


        String sURL = "https://reqres.in/api/users?page=2"; //just a string

        // Connect to the URL using java's native library
        URL obj = new URL(sURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
         // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + sURL);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            //Read JSON response and print
         Exampledatum exdatum = gson.fromJson(response.toString(),Exampledatum.class);
         Datum dat = gson.fromJson(response.toString(),Datum.class);

        System.out.println("Page  "+exdatum.getTotalPages());
        System.out.println("DataFirstPage "+exdatum.getData().get(0).getAvatar());





    }catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

}
