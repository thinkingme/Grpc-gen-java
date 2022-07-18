package com.apexsoft.gen;

import cn.hutool.json.JSONObject;
import com.apexsoft.gen.config.Properties;
import com.apexsoft.gen.model.FuncBean;
import com.apexsoft.gen.model.ProducerBean;
import com.apexsoft.gen.model.ServiceBean;
import com.apexsoft.gen.utils.ClassScanUtil;

import javax.annotation.Generated;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author: huige
 * @date: 2022/7/6 15:46
 */
public class GrpcResolve {
    public static String protoProjectName = "xiamenxt-app-protocol";

    public static String grpcSrc = "generatedSrc"+File.separator+"main"+File.separator+"grpc"+File.separator+"com"+File.separator+"apexsoft"+File.separator+"xiamenxt"+File.separator+"cloud"+File.separator+"app";

    public static String grpcPath = protoProjectName+ File.separator + grpcSrc;

    public static JSONObject resolveProducer() {


        Map<String, ProducerBean> serviceBeanMap = new HashMap<>();

        Set<Class> set = ClassScanUtil.getClass4Annotation(Properties.grpcPackageName, Generated.class);
        JSONObject classFile = new JSONObject();


        for (Class clazz : set) {
            JSONObject data = new JSONObject();

            ProducerBean producerBean = new ProducerBean();
            data.set("grpcPackageName",Properties.grpcPackageName);

            String serviceName =clazz.getName();
            int lastD = serviceName.lastIndexOf(".");
            String grpcClassName = serviceName.substring(lastD+1);

            String businessNameUpper = serviceName.substring(lastD+1,serviceName.indexOf(Properties.grpcSuffix));
            String businessNameLower = businessNameUpper.substring(0, 1).toLowerCase() + businessNameUpper.substring(1);
            String className = businessNameUpper+Properties.producerSuffix;
            String toPath = Properties.TO_PATH.substring(Properties.TO_PATH.indexOf("com"));

            data.set("className",className);
            data.set("serviceNameUpperCase",businessNameUpper+Properties.serviceSuffix);
            data.set("serviceNameLowerCase",businessNameLower+Properties.serviceSuffix);
            data.set("grpcService",grpcClassName.replaceAll("\\$","."));
            data.set("packageName",toPath.replaceAll("/","."));
            data.set("producerPackageName",toPath.replaceAll("/",".")+".producer");
            data.set("servicePackageName",toPath.replaceAll("/",".")+".service");


            ArrayList<FuncBean> func = new ArrayList<>();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                FuncBean funcBean = new FuncBean();
                String funcName = method.getName();
                if ("bindService".equals(funcName)) {
                    continue;
                }
                funcBean.setFuncName(funcName);
                //遍历方法入参的类型，判断是基本类型还是对象类型
                Type[] paramTypes = method.getGenericParameterTypes();
                for (int index = 0; index < paramTypes.length; index++) {
                    if(index == 0){
                        String typeName = paramTypes[index].getTypeName();
                        int i = typeName.lastIndexOf(".");
                        funcBean.setInParam(typeName.substring(i+1));
                    }else {
                        String typeName = paramTypes[index].getTypeName();
                        int left = typeName.indexOf("<");
                        int right = typeName.indexOf(">");
                        String leftClassName = typeName.substring(0, left);
                        int leftD = leftClassName.lastIndexOf(".");

                        String genericClassName = typeName.substring(left+1, right);
                        int genericD = genericClassName.lastIndexOf(".");
                        funcBean.setOutParam(leftClassName.substring(leftD+1)+"<"+genericClassName.substring(genericD+1)+">");
                        System.out.println();

                    }
                }

                func.add(funcBean);
            }

            data.set("funcList",func);
            classFile.set(className, data);
        }
        return classFile;
    }

    public static JSONObject resolveService() {


        Map<String, ServiceBean> serviceBeanMap = new HashMap<>();

        Set<Class> set = ClassScanUtil.getClass4Annotation(Properties.grpcPackageName, Generated.class);
        JSONObject classFile = new JSONObject();

        for (Class clazz : set) {
            JSONObject data = new JSONObject();

            ServiceBean serviceBean = new ServiceBean();

            data.set("grpcPackageName",Properties.grpcPackageName);

            String serviceName =clazz.getName();
            int lastD = serviceName.lastIndexOf(".");
            String grpcClassName = serviceName.substring(lastD+1);

            String businessNameUpper = serviceName.substring(lastD+1,serviceName.indexOf(Properties.grpcSuffix));
            String businessNameLower = businessNameUpper.substring(0, 1).toLowerCase() + businessNameUpper.substring(1);
            String className = businessNameUpper+Properties.serviceSuffix;
            String toPath = Properties.TO_PATH.substring(Properties.TO_PATH.indexOf("com"));

            data.set("className",className);
            data.set("serviceNameUpperCase",businessNameUpper+Properties.serviceSuffix);
            data.set("serviceNameLowerCase",businessNameLower+Properties.serviceSuffix);
            data.set("grpcService",grpcClassName.replaceAll("\\$","."));
            data.set("packageName",toPath.replaceAll("/","."));
            data.set("producerPackageName",toPath.replaceAll("/",".")+".producer");
            data.set("servicePackageName",toPath.replaceAll("/",".")+".service");


            ArrayList<FuncBean> func = new ArrayList<>();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                FuncBean funcBean = new FuncBean();
                String funcName = method.getName();
                if ("bindService".equals(funcName)) {
                    continue;
                }
                funcBean.setFuncName(funcName);
                //遍历方法入参的类型，判断是基本类型还是对象类型
                Type[] paramTypes = method.getGenericParameterTypes();
                for (int index = 0; index < paramTypes.length; index++) {
                    if(index == 0){
                        String typeName = paramTypes[index].getTypeName();
                        int i = typeName.lastIndexOf(".");
                        funcBean.setInParam(typeName.substring(i+1));
                    }else {
                        String typeName = paramTypes[index].getTypeName();
                        int left = typeName.indexOf("<");
                        int right = typeName.indexOf(">");
                        String leftClassName = typeName.substring(0, left);
                        int leftD = leftClassName.lastIndexOf(".");

                        String genericClassName = typeName.substring(left+1, right);
                        int genericD = genericClassName.lastIndexOf(".");
                        funcBean.setOutParam(leftClassName.substring(leftD+1)+"<"+genericClassName.substring(genericD+1)+">");
                        System.out.println();

                    }
                }

                func.add(funcBean);
            }

            data.set("funcList",func);

            classFile.set(className, data);
        }
        return classFile;
    }


}
