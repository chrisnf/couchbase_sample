import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.Statement;

import static com.couchbase.client.java.query.Select.select;

public class Main
{
    public static void main(String[] args)
    {
        //Initialize the Connection
        Cluster cluster = CouchbaseCluster.create("localhost");
        cluster.authenticate("Administrator", "password");
        Bucket bucket = cluster.openBucket("product");

        JsonObject redGermanyCap = JsonObject.create()
                .put("name", "Deutschland Kappe")
                .put("price", 14.99)
                .put("color", "red")
                .put("sizes", JsonArray.from("S", "M", "L", "XL"));

        bucket.upsert(JsonDocument.create("red_germany_cap", redGermanyCap));

        System.out.println(bucket.get("red_germany_cap"));

        cluster.disconnect();

//        // Initialize the Connection
//        Cluster cluster = CouchbaseCluster.create("localhost");
//        cluster.authenticate("Administrator", "password");
//        Bucket bucket = cluster.openBucket("travel-sample");
//
//        // Create a N1QL Primary Index (but ignore if it exists)
//        bucket.bucketManager().createN1qlPrimaryIndex(true, false);
//
//        Statement statement = select("name").from("`travel-sample`").limit(10);
//
//        // Perform a N1QL Query
//        N1qlQueryResult result = bucket.query(statement);
//
//        // Print each found Row
//        for (N1qlQueryRow row : result)
//        {
//            System.out.println(row);
//        }


//        // Initialize the Connection
//        Cluster cluster = CouchbaseCluster.create("localhost");
//        cluster.authenticate("Administrator", "password");
//        Bucket bucket = cluster.openBucket("travel-sample");
//
//        // Create a JSON Document
//        JsonObject arthur = JsonObject.create()
//                .put("name", "Arthur")
//                .put("email", "kingarthur@couchbase.com")
//                .put("interests", JsonArray.from("Holy Grail", "African Swallows"));
//
//        // Store the Document
//        bucket.upsert(JsonDocument.create("u:king_arthur", arthur));
//
//        // Load the Document and print it
//        // Prints Content and Metadata of the stored Document
//        System.out.println(bucket.get("u:king_arthur"));
//
//        // Create a N1QL Primary Index (but ignore if it exists)
//        bucket.bucketManager().createN1qlPrimaryIndex(true, false);
//
//        // Perform a N1QL Query
//        N1qlQueryResult result = bucket.query(
//                N1qlQuery.parameterized("SELECT name FROM `bucketname` WHERE $1 IN interests",
//                        JsonArray.from("African Swallows"))
//        );
//
//        // Print each found Row
//        for (N1qlQueryRow row : result) {
//            // Prints {"name":"Arthur"}
//            System.out.println(row);
//        }
    }
}
