package hello.proxy.proxy;

import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/demo/proxy")
@Secured(SecurityRule.IS_ANONYMOUS)
public class ProxyController {

    @Get(uri = "/{+path}")
    public String get(@PathVariable String path){
        return "";
    }

    @Post(uri = "/{+path}")
    public String post(@PathVariable String path){
        return "";
    }

    @Put(uri = "/{+path}")
    public String put(@PathVariable String path){
        return "";
    }

    @Delete(uri = "/{+path}")
    public String delete(@PathVariable String path){
        return "";
    }
}
