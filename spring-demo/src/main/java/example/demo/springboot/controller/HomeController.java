package example.demo.springboot.controller;

import org.apache.camel.Body;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
    
    private String s = "demo";

    @GetMapping("/demo")
    public String index() throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        //camelContext.addComponent("stream", new StreamComponet);
        camelContext.addRoutes(new RouteBuilder(){

            @Override
            public void configure() throws Exception {
                s="1000";
                
                // TODO Auto-generated method stub
                from("file:C:/Users/dragon/Desktop/spring-demo/src/main/resources/data/in?fileName=a.txt&noop=true")
                .convertBodyTo(String.class)
                .split(body().tokenize("\n"))
                .streaming()
                .filter(body().contains("li"))
                .process(exchange -> {
                    
                    s = exchange.getIn().getBody(String.class);
                    log.info(s);
                });
            //     from("timer:test?fixedRate=true&period=1000")
            // .log("Hello");
                s="1200";
            }
            
        });
        camelContext.start();
        Thread.sleep(10000);
        camelContext.stop();
        return s;
    }

	@GetMapping("/demo1234")
    public String index1234() {
        return "demo karaf 1234.";
    }

    @GetMapping("/demo1111234")
    public String index2131234() {
        return "demo karaf 1234.";
    }

}
