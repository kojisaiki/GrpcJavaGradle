package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreeterImplTest {

    private Server server;

    @BeforeEach
    public void beforeAll() throws IOException {
        server = ServerBuilder.forPort(6565)
                .addService(new GreeterImpl())
                .build();

        server.start();
    }

    @AfterEach
    public void afterAll() {
        server.shutdown();
    }

    @Test
    public void clientTest() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

        HelloRequest request = HelloRequest.newBuilder()
                .setName("Tom")
                .build();

        HelloReply reply = stub.sayHello(request);

        assertEquals("Hello Tom", reply.getMessage());
    }
}