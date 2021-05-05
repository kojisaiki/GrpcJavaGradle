package org.example;

import io.grpc.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreeterImplTest {

    private Server server;

    @BeforeAll
    public void beforeAll() {
        server = ServerBuilder.forPort(6565)
                .addService(new GreeterImpl())
                .build();

        server.start();
    }

    @Test
    public void clientTest() {

    }
}