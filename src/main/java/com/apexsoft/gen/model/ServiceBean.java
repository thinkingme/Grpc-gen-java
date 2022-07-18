package com.apexsoft.gen.model;

import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: huige
 * @date: 2022/7/8 16:10
 */

@Data
public class ServiceBean {

    private String producerPackageName;

    private String serviceNameUpperCase;

    private String serviceNameLowerCase;

    private String servicePackageName;

    private String grpcPackageName;

    private List<FuncBean> funcList;

    private String grpcService;

    private String className;

}
