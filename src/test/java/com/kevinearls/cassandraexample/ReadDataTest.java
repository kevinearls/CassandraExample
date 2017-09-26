package test.java.com.kevinearls.cassandraexample;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.junit.Test;

import java.util.Map;

public class ReadDataTest {
    private static Map<String, String> envs = System.getenv();

    private static String CLUSTER_IP = envs.getOrDefault("CLUSTER_IP", "127.0.0.1");  
    private static String KEYSPACE_NAME = envs.getOrDefault("KEYSPACE_NAME", "tp");   // jaeger_v1_cd1
    private static String QUERY = envs.getOrDefault("QUERY", "SELECT * FROM employee");  // select count(*) from traces

    @Test
    public void simple() {
        System.out.println(">>>> Starting with CLUSTER_IP " + CLUSTER_IP + " Keyspace " + KEYSPACE_NAME);
        Cluster.Builder builder = Cluster.builder();
        builder.addContactPoint(CLUSTER_IP);
        Cluster cluster = builder.build();

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
