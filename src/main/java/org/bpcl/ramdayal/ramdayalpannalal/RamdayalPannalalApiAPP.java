package org.bpcl.ramdayal.ramdayalpannalal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class RamdayalPannalalApiAPP 
{
    public static void main( String[] args )
    {
        SpringApplication.run(RamdayalPannalalApiAPP.class, args);
    }
}
