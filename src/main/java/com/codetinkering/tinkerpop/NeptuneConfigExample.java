package com.codetinkering.tinkerpop;

import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

@Configuration
public class NeptuneConfigExample {


    @Bean
    public Cluster cluster() {
        return Cluster.build()
                .addContactPoint("your-neptune-instance-here.xxxxxxxxxxxx.us-east-1.neptune.amazonaws.com")
                .port(8182)
                .enableSsl(true)
                .keyCertChainFile("./cert/SFSRootCAG2.pem") // download it from https://www.amazontrust.com/repository/SFSRootCAG2.pem and place it at /tinkerpop-neptune-example/cert/SFSRootCAG2.pem
                .maxConnectionPoolSize(5)
                .maxInProcessPerConnection(1)
                .maxSimultaneousUsagePerConnection(1)
                .create();
    }

    @Bean
    public GraphTraversalSource g(Cluster cluster) {
        return traversal().withRemote(DriverRemoteConnection.using(cluster));
    }


}
