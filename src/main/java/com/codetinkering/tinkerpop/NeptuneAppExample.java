package com.codetinkering.tinkerpop;

import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*;

@SpringBootApplication
public class NeptuneAppExample implements CommandLineRunner {

    @Autowired
    Cluster cluster;

    @Autowired
    GraphTraversalSource g;

    @Override
    public void run(String... args) throws Exception {

        g.addV("MyVertexLabel")
                // You can also specify a custom unique ID like so:
                // .property(T.id, 123)
                .property("name", "test")
                .property("country", "example")
                .property("employees", 12345)
                .next();

        writeGraphOut();
    }


    void writeGraphOut() {
        g.io("graph-file-dump.json").write().iterate();
    }

    void readGraphIn() {
        g.io("graph-file-dump.json").read().iterate();
    }

    public static void main(String[] args) {
        SpringApplication.run(NeptuneConfigExample.class, args);
    }
}
