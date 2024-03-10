package Http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebServiceClient {

    public static final String WEBSERVICE_BASE_URL = "https://viacep.com.br/ws/";

    /**
     * Realiza uma request para o webservice e, em caso de sucesso,
     * retorna um String de JSON
     * @param cep
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String sendRequest(String cep) throws IOException, InterruptedException {
        URI uri = this.buildWebServiceUri(cep);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );

        return response.body();
    }

    private URI buildWebServiceUri(String cep) {
        String url = WEBSERVICE_BASE_URL + cep + "/json";
        URI uri = URI.create(url);
        return uri;
    }
}
