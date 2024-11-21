package hello.proxy.proxy;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.RequestFilter;
import io.micronaut.http.annotation.ServerFilter;
import io.micronaut.http.client.HttpVersionSelection;
import io.micronaut.http.client.ProxyHttpClient;
import io.micronaut.http.client.ProxyRequestOptions;
import io.micronaut.http.client.annotation.Client;
import org.apache.commons.lang3.ArrayUtils;
import reactor.core.publisher.Flux;

import java.net.URISyntaxException;


@ServerFilter("/demo/proxy/**")
public class ProxyFilter{
    private final ProxyHttpClient proxyHttpClient;

    public ProxyFilter(@Client(plaintextMode = HttpVersionSelection.PlaintextMode.HTTP_1,
            alpnModes = HttpVersionSelection.ALPN_HTTP_1) ProxyHttpClient proxyHttpClient){
        this.proxyHttpClient = proxyHttpClient;
    }

    @RequestFilter
    public Flux<MutableHttpResponse<?>> doFilter(HttpRequest<?> request) throws URISyntaxException {

        long beginTime = System.currentTimeMillis();
        String[] pathArray = request.getPath().split("/");
        Proxy proxy = getProxy(pathArray[3]);

        String[] pathForRemote = ArrayUtils.subarray(pathArray, 4, pathArray.length);

        Flux<MutableHttpResponse<?>> fluxResponse = Flux.from(proxyHttpClient.proxy(request.mutate().uri(builder -> builder
                                .scheme(proxy.getUri().getScheme())
                                .host(proxy.getUri().getHost())
                                .port(proxy.getUri().getPort())
                                .replacePath(proxy.getUri().getPath() + "/" + String.join("/", pathForRemote)))
                        .headers(headers -> {
                            headers.remove("Host").remove("Authorization")
                                    .add("Host", proxy.getUri().getHost());
                        })
                , ProxyRequestOptions.builder().retainHostHeader(true).build()));

        return fluxResponse.map(response -> {
            long elapse = System.currentTimeMillis() - beginTime;
            return response.headers(headers -> headers.remove(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN).remove(HttpHeaders.VARY));
        });
    }

    private Proxy getProxy(String target) throws URISyntaxException {

        Proxy proxy = new Proxy("freeapi");
        proxy.setAddr("https://jsonplaceholder.typicode.com");
        proxy.generateUri();

        return proxy;
    }

}
