package wiremock.code;

import com.github.tomakehurst.wiremock.client.WireMock;

public class MockServer {
    //    http://localhost:8888/order/1
    public static void main(String[] args) {
        WireMock.configureFor(8888);
        WireMock.removeAllMappings();
        mock("/order/1",1);
        mock("/order/2",2);
    }

    private static void mock(String url,Object param){
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url)).willReturn(WireMock.aResponse().withBody("{\"id\":"+param+"}").withStatus(200)));
    }

}
