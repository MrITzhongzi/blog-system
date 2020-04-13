package com.lhw.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QiniuConfig {

    @Value("${qiniu.ACCESSKEY}")
    public String accessKey;

    @Value("${qiniu.SECRETKEY}")
    public String secretKey;

    @Value("${qiniu.BUCKETNAME}")
    public String bucket;
}
