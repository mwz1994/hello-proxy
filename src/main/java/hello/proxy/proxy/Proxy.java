package hello.proxy.proxy;

import io.micronaut.context.annotation.Parameter;

import java.net.URI;
import java.net.URISyntaxException;

public class Proxy {

    private String name;

    private String addr;

    private URI uri;

    public Proxy(@Parameter String name) {
        this.name = name;
    }

    public void generateUri() throws URISyntaxException {
        this.uri = new URI(addr);
    }

    public String getName() {
        return name;
    }


    public void setAddr(String addr) {
        this.addr = addr;
    }

    public URI getUri() {
        return uri;
    }

}
