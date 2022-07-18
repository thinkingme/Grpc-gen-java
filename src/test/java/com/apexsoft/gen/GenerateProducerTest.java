package com.apexsoft.gen;

import com.apexsoft.gen.config.Properties;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author: huige
 * @date: 2022/7/7 14:02
 */
public class GenerateProducerTest {



    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        // step2 获取模版路径
        configuration.setDirectoryForTemplateLoading(new File(Properties.TEMPLATE_PATH));
        // step3 创建数据模型
        Map producerData = GrpcResolve.resolveProducer();
        Map serviceData = GrpcResolve.resolveService();
        // step4 加载模版文件
        Template producerTemplate = configuration.getTemplate(Properties.producerTemplateName);
        Template serviceTemplate = configuration.getTemplate(Properties.serviceTemplateName);

        String producerPath = Properties.TO_PATH+ File.separator  + "producer";
        String servicePath = Properties.TO_PATH+ File.separator  + "service";
        FreemarkerGenerate.generate( producerData,producerTemplate,producerPath);
        FreemarkerGenerate.generate( serviceData,serviceTemplate,servicePath);
    }

}
