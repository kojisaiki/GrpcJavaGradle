package org.example;

public class DemoServer {
    public static void main(String[] args){

        Server server = ServerBuilder.forPort(6565)
                .addService(new GreeterImpl())
                .build();

        server.start();

        server.awaitTermination();
    }
}
