package ${servicePackageName};

import org.springframework.stereotype.Service;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import com.apexsoft.xiamenxt.cloud.app.proto.*;

@Service
public class ${className}{

    @Autowired
    private BaseProcProducerService baseProcProducerService;


<#list funcList as func>
    @GrpcMethodAnnotation
    public String ${func.funcName}(${func.inParam} request, ${func.outParam} responseObserver) {
        return baseProcProducerService.callProducer("",request, responseObserver);
    }
</#list>

}

