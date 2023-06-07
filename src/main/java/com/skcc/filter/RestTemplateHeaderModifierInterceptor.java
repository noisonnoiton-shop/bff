package com.skcc.filter;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapSetter;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

    // private final OpenTelemetry openTelemetry = GlobalOpenTelemetry.get();

    TextMapSetter<HttpRequest> setter =
            (carrier, key, value) -> {
                // Insert the context as Header
                assert carrier != null;
                carrier.getHeaders().set(key, value);
            };

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // openTelemetry.get().getPropagators().getTextMapPropagator().inject(Context.current(), request, setter);
        GlobalOpenTelemetry.get().getPropagators().getTextMapPropagator().inject(Context.current(), request, setter);
        ClientHttpResponse response = execution.execute(request, body);
        response.getHeaders().add("Foo", "Bar");
        return response;
    }
}
