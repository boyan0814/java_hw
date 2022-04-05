import com.mongodb.MongoClient;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Iterator;

class JSONTest {
    interface ProcessObj {
        void todo(JSONObject obj, int i);
    }
    public static void main(String[] args) throws FileNotFoundException, MalformedURLException, IOException {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoTest mongoTest = new MongoTest();
        mongoTest.cleanCollection(mongoClient);
        String jsonStr="";
        try
        {
            jsonStr = Jsoup.connect("https://tcgbusfs.blob.core.windows.net/dotapp/youbike/v2/youbike_immediate.json").ignoreContentType(true).execute().body();
//            System.out.println(jsonStr);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JSONArray array  = new JSONArray(jsonStr);

        forEach(array, (o, i) -> {
            System.out.println("------ "+i+" --------");
            Document data = new Document();
            for(Iterator<String> keys = o.keys(); keys.hasNext();) {
                String key = keys.next();
                System.out.println(key +":"+o.get(key));
                data.append(key,o.get(key));
            }
            mongoTest.testNewAPI(mongoClient, data);
        });
        
    }
    public static void forEach(JSONArray array, ProcessObj process) {
        for(int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            process.todo(obj, i);
        }
    }
}
