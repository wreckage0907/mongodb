//QUESTION
//FINDING EMPLYEE WITH LOWEST SALARY IN A SPECIFIC AGE RANGE
//

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import java.util.Arrays;

public class Assignment2_2 {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("Saturday");
            MongoCollection<Document> collection = database.getCollection("employee");
            collection.drop();

            Document document1 = new Document("First_Name", "Sai")
                .append("Last_Name", "Kushal")
                .append("salary", 18000)
                .append("age", 30);
            Document document2 = new Document("First_Name", "Krishna")
                .append("Last_Name", "Kishore")
                .append("salary", 21000)
                .append("age", 35);
            Document document3 = new Document("First_Name", "Feroz")
                .append("Last_Name", "Shaik")
                .append("salary", 17000)
                .append("age", 42);
            Document document4 = new Document("First_Name", "Karthik")
                .append("Last_Name", "Ram")
                .append("salary", 22000)
                .append("age", 37);
            Document document5 = new Document("First_Name", "Suneeth")
                .append("Last_Name", "Reddy")
                .append("salary", 38000)
                .append("age", 44);
            Document document6 = new Document("First_Name", "Lalith")
                .append("Last_Name", "Sri")
                .append("salary", 29000)
                .append("age", 32);

            collection.insertMany(Arrays.asList(document1, document2, document3, document4, document5, document6));

            FindIterable<Document> sortedSalaries = collection.find().sort(Sorts.ascending("salary"));
            Document lowestSalary = null;
            for (Document doc : sortedSalaries) {
                int age = doc.getInteger("age");
                if (age >= 30 && age <= 40) {
                    lowestSalary = doc;
                    break;
                }
            }

            if (lowestSalary != null) {
                System.out.println("Employee with the lowest salary in the age range 30 to 40:");
                System.out.println(lowestSalary.toJson());
            } else {
                System.out.println("No employees found");
            }
        }
    }
}

