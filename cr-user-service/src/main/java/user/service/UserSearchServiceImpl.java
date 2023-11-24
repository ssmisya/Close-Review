package user.service;

import net.devh.boot.grpc.examples.lib.UserSearchServiceGrpc;
import net.devh.boot.grpc.examples.lib.UserResponse;
import net.devh.boot.grpc.examples.lib.UserRequest;
import net.devh.boot.grpc.examples.lib.HelloRequest;
import net.devh.boot.grpc.examples.lib.HelloReply;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserSearchServiceImpl extends UserSearchServiceGrpc.UserSearchServiceImplBase {

    @Override
    public void SearchUserByName(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse reply = UserResponse.newBuilder()
                .setId(5)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + request.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}
