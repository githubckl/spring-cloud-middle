package com.atguigu.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*bean和configuration在忽略大小写后不能一样,*/
@Configuration
public class MyRule {
    @Bean
    public IRule mySelfRule(){
        return new RandomRule();
    }
}
