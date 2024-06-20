// QUESTION
// FINDING STUDENTS WITH THE HIGHEST MARK
//

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import java.util.Arrays;

public class ConnectionPractice {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("Saturday");
            MongoCollection<Document> collection = database.getCollection("student");
            collection.drop();

            Document document1 = new Document("First_Name", "Sai")
                .append("Last_Name", "Kushal")
                .append("Mark", 90)
                .append("age", 18);
            Document document2 = new Document("First_Name", "Suneeth")
                .append("Last_Name", "Reddy")
                .append("Mark", 90)
                .append("age", 22);
            Document document3 = new Document("First_Name", "Krishna")
                .append("Last_Name", "Kishore")
                .append("Mark", 91)
                .append("age", 20);
            Document document4 = new Document("First_Name", "Prem")
                .append("Last_Name", "Sai")
                .append("Mark", 89)
                .append("age", 19);

            collection.insertMany(Arrays.asList(document1, document2, document3, document4));

            Document highestMark = collection.find().sort(Sorts.descending("Mark")).first();
            if (highestMark != null) {
                int highestMarkValue = highestMark.getInteger("Mark");
                FindIterable<Document> studentsWithHighestMark = collection.find(Filters.eq("Mark", highestMarkValue));
                System.out.println("Students with the highest mark:");
                for (Document doc : studentsWithHighestMark) {
                    System.out.println(doc.toJson());
                }
            } else {
                System.out.println("No students found");
            }
        }
    }
}

