package com.epam.learn.javaadvanced;

import com.epam.learn.javaadvanced.pingpong.PingPongServiceGrpc;
import com.epam.learn.javaadvanced.pingpong.PingRequest;
import com.epam.learn.javaadvanced.pingpong.PongResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class PingPongClient {
    private final ManagedChannel channel;
    private final PingPongServiceGrpc.PingPongServiceBlockingStub blockingStub;

    public PingPongClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.blockingStub = PingPongServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void ping() {
        PingRequest request = PingRequest.newBuilder()
                .setMessage("Ping")
                .build();
        PongResponse response = blockingStub.ping(request);
        System.out.println("Received Pong: " + response.getMessage());
    }

    public static void main(String[] args) throws InterruptedException {
        PingPongClient client = new PingPongClient("localhost", 8080);

        try {
            while(true) {
                client.ping();
                // Add 2-second delay
                TimeUnit.SECONDS.sleep(2);
            }
        } finally {
            client.shutdown();
        }
    }
}
