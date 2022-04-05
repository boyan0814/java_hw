import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class MongoTest {

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
    }


    public void cleanCollection(MongoClient mongoClient){
        MongoDatabase db = mongoClient.getDatabase("test");
        MongoCollection<Document> coll = db.getCollection("data");
        coll.drop();
    }
    public void testNewAPI(MongoClient mongoClient, Document data) {
        
        MongoDatabase db = mongoClient.getDatabase("test");
//        db.createCollection("data");
        MongoCollection<Document> coll = db.getCollection("data");
        coll.insertOne(data);
    }

}
