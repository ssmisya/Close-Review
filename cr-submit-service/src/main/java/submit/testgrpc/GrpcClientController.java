package submit.testgrpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrpcClientController {

    @Autowired
    private GrpcClientService grpcClientService;

    @RequestMapping("/testgrpc/")
    public String printMessage(@RequestParam(defaultValue = "will") String name) {
        return grpcClientService.sendMessage(name);
    }
}
