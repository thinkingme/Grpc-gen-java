package ${producerPackageName};

import com.apex.ams.server.AmsService;
import ${servicePackageName}.${serviceNameUpperCase};
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import com.apexsoft.xiamenxt.cloud.app.proto.*;

@AmsService
public class ${className} extends ${grpcService}{

    @Autowired
    private ${serviceNameUpperCase} ${serviceNameLowerCase};


<#list funcList as func>
    @Override
    public void ${func.funcName}(${func.inParam} request, ${func.outParam} responseObserver) {
        ${serviceNameLowerCase}.${func.funcName}(request, responseObserver);
    }
</#list>

}