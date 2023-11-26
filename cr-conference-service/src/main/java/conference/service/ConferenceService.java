package conference.service;
import conference.entity.Conference;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.examples.lib.HelloRequest;
import net.devh.boot.grpc.examples.lib.UserRequest;
import net.devh.boot.grpc.examples.lib.UserSearchServiceGrpc.UserSearchServiceBlockingStub;

import net.devh.boot.grpc.client.inject.GrpcClient;

import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {
    @GrpcClient("cr-user-service")
    private UserSearchServiceBlockingStub userSearchServiceBlockingStub;


    public String  searchUser(String name) {
        UserRequest request = UserRequest.newBuilder()
                .setQueryUsername(name)
                .build();
        return userSearchServiceBlockingStub.searchUserByName(request).toString();
    }
    public String receiveGreeting(String name) {
        try{
            HelloRequest request = HelloRequest.newBuilder()
                    .setName(name)
                    .build();
            return userSearchServiceBlockingStub.sayHello(request).getMessage();
        }catch (final StatusRuntimeException e) {
            e.printStackTrace();
            return "FAILED with " + e.getStatus().getCode().name();
        }

    }

}
