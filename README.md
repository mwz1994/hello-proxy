## Version
micronaut 4.7.5
jdk 21
gradle 8.10.2

## Steps To Reproduce
Clone https://github.com/mwz1994/hello-proxy.git (main branch)

Run the app

Call the API under the ProxyFilter path (demo/proxy/**) through POST or PUT protocol with body information.
The application code forwards the request to another remote service.

Such as 

`curl --location --request PUT 'https://mydomain.com/demo/proxy/freeapi/posts/1' \
--header 'Content-Type: application/json' \
--data '{
"name": "peter",
"want2say":"Hello there! I'\''m Peter"
}'`

This is an occasional bug that requires several attempts. You will see the above error.

I get an error after 30-50 calls with a tool like jmeter.

The error message for the Https protocol is as follows; the exception message for the http protocol is somewhat different

##  Exception Message（HTTPS）
`11:02:54.221 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 1320ms. Server Running: http://localhost:8080
Micronaut Version 4.7.5
11:03:00.916 [default-nioEventLoopGroup-1-15] WARN  i.n.u.c.AbstractEventExecutor - A task raised an exception. Task: io.micronaut.http.netty.body.StreamingNettyByteBody$SharedBuffer$$Lambda/0x0000000131635f58@6d48294e
java.lang.NullPointerException: Cannot invoke "io.micronaut.http.netty.EventLoopFlow.executeNow(java.lang.Runnable)" because "this.flow" is null
at io.micronaut.http.client.netty.StreamWriter.add(StreamWriter.java:90)
at io.micronaut.http.netty.body.StreamingNettyByteBody$SharedBuffer.subscribe0(StreamingNettyByteBody.java:348)
at io.micronaut.http.netty.body.StreamingNettyByteBody$SharedBuffer.lambda$subscribe$1(StreamingNettyByteBody.java:328)
at io.netty.util.concurrent.AbstractEventExecutor.runTask(AbstractEventExecutor.java:173)
at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:166)
at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:472)
at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:569)
at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:997)
at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)`







