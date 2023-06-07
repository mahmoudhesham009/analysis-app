package com.spirawn.assertiv.services.impl;

import com.spirawn.assertiv.services.HttpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;

import static java.nio.charset.StandardCharsets.UTF_8;


@Service
public class HttpServiceImpl implements HttpService {

    @Value("#{'${assertiv.aps.url}'}")
    String BASE_URL;

    @Value("#{'${assertiv.aps.userName}'}")
    String USER;

    @Value("#{'${assertiv.aps.password}'}")
    String PASSWORD;
    @Override
    public <T> T getReq(String path, Class<T> c) throws URISyntaxException {
        WebClient client = WebClient.create();

        return client.get()
                .uri(new URI(BASE_URL+path))
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,getCredentials())
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        response ->{ throw new RuntimeException();})
                .bodyToMono(c)
                .block();
    }

    @Override
    public <T> T postReq(String path, Object body, Class<T> c) throws URISyntaxException {
        WebClient client = WebClient.create();

        return client.post()
                .uri(new URI(BASE_URL+path))
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,getCredentials())
                .bodyValue(body)
                .retrieve()
                .onStatus(
                        s-> !s.equals(HttpStatus.OK),
                        response ->{ throw new RuntimeException(response.toString());})
                .bodyToMono(c)
                .block();
    }

    @Override
    public <T> T deleteReq(String path, Class<T> c) throws URISyntaxException {
        WebClient client = WebClient.create();
        return client.delete()
                .uri(new URI(BASE_URL+path))
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,getCredentials())
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        response ->{ throw new RuntimeException();})
                .bodyToMono(c)
                .block();

    }

    private String getCredentials() {

        String creds = USER + ":"+ PASSWORD;
        creds = new String("Basic "+ Base64Utils
                .encodeToString((creds).getBytes(UTF_8)));
        return creds;
    }
}
