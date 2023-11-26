package conference.service;
import conference.repository.ConferenceRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.examples.lib.*;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class ConferenceServiceGrpcImpl extends ConferenceServiceGrpc.ConferenceServiceImplBase {
    final ConferenceRepository repository;

    public ConferenceServiceGrpcImpl(ConferenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + request.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void queryPaperNumAndUpdateOne(ConferenceRequest request, StreamObserver<ConferenceIntResponse> responseObserver){
        String conferenceId = request.getQueryContent();
        Long paperNum = repository.findPaperNumByConferenceId(conferenceId);
        repository.upDatePaperNumByConferenceId(conferenceId,paperNum+1);
        ConferenceIntResponse reply = ConferenceIntResponse.newBuilder()
                .setValue(paperNum)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void queryConferenceStatus(ConferenceRequest request, StreamObserver<ConferenceStringResponse> responseObserver){
        String conferenceId = request.getQueryContent();
        String status = repository.findConferenceStatusByConferenceId(conferenceId);
        ConferenceStringResponse reply = ConferenceStringResponse.newBuilder()
                .setValue(status)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void queryConferenceIdByConferenceName(ConferenceRequest request, StreamObserver<ConferenceStringResponse> responseObserver){
        String conferenceName = request.getQueryContent();
        String id = repository.findConferenceIdByConferenceName(conferenceName);
        ConferenceStringResponse reply = ConferenceStringResponse.newBuilder()
                .setValue(id)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    public void queryConferenceIdByShortName(ConferenceRequest request, StreamObserver<ConferenceStringResponse> responseObserver){
        String shortName = request.getQueryContent();
        String id = repository.findConferenceIdByShortName(shortName);
        ConferenceStringResponse reply = ConferenceStringResponse.newBuilder()
                .setValue(id)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    public void updatePaperNumByConferenceId(ConferenceIntAndStringRequest request, StreamObserver<ConferenceIntResponse> responseObserver) {
        String conferenceId = request.getQueryString();
        Long num = request.getQueryInt();
        String id = repository.upDatePaperNumByConferenceId(conferenceId,num);
        ConferenceIntResponse reply = ConferenceIntResponse.newBuilder()
                .setValue(1)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }


}
