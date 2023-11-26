package submit;


import io.grpc.StatusRuntimeException;
import io.grpc.netty.shaded.io.grpc.netty.NegotiationType;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.examples.lib.HelloReply;
import net.devh.boot.grpc.examples.lib.HelloRequest;
import net.devh.boot.grpc.examples.lib.UserSearchServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    @GrpcClient("cr-user-service")
    private UserSearchServiceGrpc.UserSearchServiceBlockingStub simpleStub;

    public String sendMessage(final String name) {
//        channel = NettyChannelBuilder.forAddress("127.0.0.1", 9090)
//                .negotiationType(NegotiationType.PLAINTEXT)
//                .build();
        try {
            final HelloReply response = this.simpleStub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            e.printStackTrace();
            return "FAILED with " + e.getStatus().getCode().name();

        }
    }
}
