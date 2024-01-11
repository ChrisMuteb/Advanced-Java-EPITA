package fr.epita.quiz.web.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MicroServer {
    HttpServer server;
    public MicroServer(int port, int processors, String path, HttpHandler handler) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        server.setExecutor(executor);
        HttpContext context = this.server.createContext(path);
        context.setHandler(handler);
    }
    public void start(){
        this.server.start();
    }
    public void stop(){
        this.server.stop(0);
    }

}
