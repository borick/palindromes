package palindrome;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServer;

import static rx.Observable.*;

public final class PalindromeServer {

    public static void main(final String[] args) {
        Palindrome p = new Palindrome();
        p.loadData();
        HttpServer<ByteBuf, ByteBuf> server;
        server = HttpServer.newServer(8080)
                           .start((req, resp) -> {
                                    String uri = (String)req.getUri();
                                    String tmpOut;
                                    if (uri.equals("/palindromes")) {
                                        tmpOut = String.join("\n", p.list);
                                        return resp.writeString(just(tmpOut));
                                    } else if (uri.equals("/palindromes/count")) {
                                        tmpOut = Integer.toString(p.list.size());
                                        return resp.writeString(just(tmpOut));
                                    } else {
                                        return resp.writeString(just("error"));
                                    }
                               }
                           );
        server.awaitShutdown();
    }
}