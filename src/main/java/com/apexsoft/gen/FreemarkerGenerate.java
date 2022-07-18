package com.apexsoft.gen;

import cn.hutool.core.io.FileUtil;
import com.apexsoft.gen.model.ProducerBean;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.*;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author: huige
 * @date: 2022/7/6 11:25
 */

public class FreemarkerGenerate {

    public static void generate(Map<String, Object> data, Template template, String parentDir) {
        try {

            data.forEach((key, value) -> {
                // step5 生成数据
                File parentFile = new File(parentDir);
                File file = new File(parentFile.getAbsolutePath()+File.separator+key+".java");
                if(file.exists()){
                    return;
                }else{
                    FileUtil.mkParentDirs(file);
                }
                Writer out =null;
                try {
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    // step6 输出文件
                    template.process(value, out);
                } catch (TemplateException | IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (null != out) {
                            out.flush();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}