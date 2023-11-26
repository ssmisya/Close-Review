package user.service;

import net.devh.boot.grpc.examples.lib.UserSearchServiceGrpc;
import net.devh.boot.grpc.examples.lib.UserResponse;
import net.devh.boot.grpc.examples.lib.UserRequest;
import net.devh.boot.grpc.examples.lib.HelloRequest;
import net.devh.boot.grpc.examples.lib.HelloReply;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import user.entity.User;
import user.entity.UserRole;
import user.repository.UserRepository;
import user.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@GrpcService
public class UserSearchServiceImpl extends UserSearchServiceGrpc.UserSearchServiceImplBase {

    private final UserRepository repository;
    private final UserRoleRepository roleRepository;

    public UserSearchServiceImpl(UserRepository repository, UserRoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }


    @Override
    public void searchUserByName(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        List<User> userlist = repository.findByUserName(request.getQueryUsername());
        List<UserRole> rolelist = roleRepository.findByUserName(request.getQueryUsername());
        UserResponse reply = null;
        if (userlist.isEmpty()||rolelist.isEmpty()){
            reply = UserResponse.newBuilder()
                    .setStatus(-1)
                    .build();
        }else{
            reply = UserResponse.newBuilder()
                    .setId(userlist.get(0).getId())
                    .setUserName(userlist.get(0).getUserName())
                    .setNickName(userlist.get(0).getNickName())
                    .setEmail(userlist.get(0).getEmail())
                    .setOrganization(userlist.get(0).getOrganization())
                    .setRegion(userlist.get(0).getRegion())
                    .setRole(rolelist.get(0).getRole())
                    .setStatus(1)
                    .build();
        }
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
