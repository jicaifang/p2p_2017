package com.xmg.p2p.website;

import com.CoreCofing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by seemygo on 2017/10/23.
 */
@SpringBootApplication
@Import({CoreCofing.class,WebMvcConfig.class})
@PropertySource("classpath:application_website.properties")
public class WebsiteConfig {
    public static void main(String[] args) {
        SpringApplication.run(WebsiteConfig.class,args);
    }
}
