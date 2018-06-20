import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.Statement;

import static com.couchbase.client.java.query.Select.select;

// Singleton für die Coucbase-Datenbank-Verbindung
public final class DBConnector
{
    private static DBConnector instance;
    private Bucket bucket;

    private DBConnector()
    {
        // Verbindung zur Couchbase-Datenbank aufbauen
        Cluster cluster = CouchbaseCluster.create("localhost");
        cluster.authenticate("Administrator", "password");
        bucket = cluster.openBucket("product");
    }

    public static DBConnector getInstance()
    {
        if (DBConnector.instance == null)
        {
            DBConnector.instance = new DBConnector();
        }
        return DBConnector.instance;
    }

    // Suche nach dem Namen eines Produkts
    public String searchProductByName(String text)
    {
        Statement statement = select("name", "price").from("`product`").where("LOWER(name) LIKE  '%" + text.toLowerCase() + "%'");

        N1qlQueryResult result = bucket.query(statement);

        StringBuilder stringBuilder = new StringBuilder();

        for (N1qlQueryRow row : result)
        {
            stringBuilder.append(row.value().toString());
        }

        return stringBuilder.toString();
    }
}
