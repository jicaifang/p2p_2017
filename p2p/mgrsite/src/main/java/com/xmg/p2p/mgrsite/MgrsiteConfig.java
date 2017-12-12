package com.xmg.p2p.mgrsite;

        import com.CoreCofing;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.context.annotation.Import;
        import org.springframework.context.annotation.PropertySource;

/**
 * Created by seemygo on 2017/10/26.
 */
@SpringBootApplication
@PropertySource("classpath:application_mgrsite.properties")
@Import({CoreCofing.class,WebMvcConfig.class})
public class MgrsiteConfig {
    public static void main(String[] args) {
        SpringApplication.run(MgrsiteConfig.class,args);
    }
}
