package hello.proxy;

import io.micronaut.core.version.VersionUtils;
import io.micronaut.runtime.Micronaut;

public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
        System.out.println("Micronaut Version "+ VersionUtils.getMicronautVersion());
    }
}