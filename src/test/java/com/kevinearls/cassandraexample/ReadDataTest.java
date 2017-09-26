package test.java.com.kevinearls.cassandraexample;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ReadDataTest {
    private static Map<String, String> envs = System.getenv();

    private static String CLUSTER_IP = envs.getOrDefault("CLUSTER_IP", "cassandra");
    private static String KEYSPACE_NAME = envs.getOrDefault("KEYSPACE_NAME", "jaeger_v1_dc1");   // jaeger_v1_cd1
    private static String QUERY = envs.getOrDefault("QUERY", "SELECT COUNT(*) FROM traces");  // select count(*) from traces

    @Test
    public void simple() {
        System.out.println(">>>> Starting with CLUSTER_IP " + CLUSTER_IP + " Keyspace " + KEYSPACE_NAME);
        Cluster.Builder builder = Cluster.builder();
        builder.addContactPoint(CLUSTER_IP);
        Cluster cluster = builder.build();


        System.out.println(">>>>>>>>>> List all Keyspaces");
        List<KeyspaceMetadata> keyspaces = cluster.getMetadata().getKeyspaces();
        for (KeyspaceMetadata keyspace : keyspaces) {
            System.out.println(keyspace.getName());
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");


        //Creating Session object
        Session session = cluster.connect(KEYSPACE_NAME);

        //Getting the ResultSet
        ResultSet result = session.execute(QUERY);
        System.out.println(result.all());

        /*Row one = result.one();
        long count = one.getLong(0);
        System.out.println(one);*/
    }
}
