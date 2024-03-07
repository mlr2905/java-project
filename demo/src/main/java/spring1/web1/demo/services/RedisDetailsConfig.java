package spring1.web1.demo.services;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

//@ConstructorBinding
@ConfigurationProperties("redis")
public class RedisDetailsConfig {
    @Getter
    protected String host;
    @Getter
    protected int port;
    @Getter
    protected long ttl;

    public RedisDetailsConfig(String host, int port, long ttl) {
        this.host = host;
        this.port = port;
        this.ttl = ttl;
    }
}
