import grpc
import pingpong_pb2
import pingpong_pb2_grpc

def run():
    # Create a channel to the server
    channel = grpc.insecure_channel('localhost:8080')

    # Create a stub (client)
    stub = pingpong_pb2_grpc.PingPongServiceStub(channel)

    # Create a request
    request = pingpong_pb2.PingRequest(message="Ping from Python")

    try:
        # Make the RPC call
        response = stub.Ping(request)
        print(f"Received Pong response: {response.message}")
    except grpc.RpcError as e:
        print(f"RPC failed: {e.code()}: {e.details()}")

if __name__ == '__main__':
    run()