package com.epam.learn.javaadvanced;

import com.epam.learn.javaadvanced.pingpong.PingPongServiceGrpc;
import com.epam.learn.javaadvanced.pingpong.PingRequest;
import com.epam.learn.javaadvanced.pingpong.PongResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class PingPongServer {
    private final int port;
    private final Server server;

    public PingPongServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new PingPongServiceImpl())
                .build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            PingPongServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class PingPongServiceImpl extends PingPongServiceGrpc.PingPongServiceImplBase {
        @Override
        public void ping(PingRequest request, StreamObserver<PongResponse> responseObserver) {
            System.out.println("Received Ping: " + request.getMessage());
            PongResponse response = PongResponse.newBuilder()
                    .setMessage("Pong")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        PingPongServer server = new PingPongServer(8080);
        server.start();
        server.blockUntilShutdown();
    }
}
