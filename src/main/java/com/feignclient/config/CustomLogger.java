package com.feignclient.config;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLogger extends feign.Logger {

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        if (log.isDebugEnabled()) {
            List<String> logs = new ArrayList<>();

            logs.add(format(configKey, "---> %s %s HTTP/1.1", request.httpMethod(), request.url()));
            if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {

                for (String field : request.headers().keySet()) {
					/*
					 * for (String value : valuesOrEmpty(request.headers(), field)) {
					 * logs.add(format(configKey, "%s: %s", field, value)); }
					 */
                }

                int bodyLength = 0;
                if (request.body() != null) {
                    bodyLength = request.body().length;
                    if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                        String
                                bodyText =
                                request.charset() != null ? new String(request.body(), request.charset()) : null;
                        logs.add(format(configKey, "")); // CRLF
                        logs.add(format(configKey, "%s", bodyText != null ? bodyText : "Binary data"));
                    }
                }
                
                logs.add(format(configKey, "---> END HTTP (%s-byte body)", bodyLength));
                
            }
            log.debug(String.join("\n", logs));
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response,
                                              long elapsedTime) throws IOException {
        if (log.isDebugEnabled()) {
            List<String> logs = new ArrayList<>();

            String reason = response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ?
                    " " + response.reason() : "";
            int status = response.status();
            logs.add(format(configKey, "<--- HTTP/1.1 %s%s (%sms)", status, reason, elapsedTime));
            if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {

                for (String field : response.headers().keySet()) {
					/*
					 * for (String value : valuesOrEmpty(response.headers(), field)) {
					 * logs.add(format(configKey, "%s: %s", field, value)); }
					 */
                }

                int bodyLength = 0;
                if (response.body() != null && !(status == 204 || status == 205)) {
                    // HTTP 204 No Content "...response MUST NOT include a message-body"
                    // HTTP 205 Reset Content "...response MUST NOT include an entity"
                    if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                        logs.add(format(configKey, "")); // CRLF
                    }
                    byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                    bodyLength = bodyData.length;
					/*
					 * if (logLevel.ordinal() >= Level.FULL.ordinal() && bodyLength > 0) {
					 * logs.add(format(configKey, "%s", decodeOrDefault(bodyData, "UTF_8",
					 * "Binary data"))); }
					 */
                    String str = new String(bodyData);
                    logs.add(str);
                    logs.add(format(configKey, "<--- END HTTP (%s-byte body)", bodyLength));
                    log.debug(String.join("\n", logs));
                    return response.toBuilder().body(bodyData).build();
                } else {
                    logs.add(format(configKey, "<--- END HTTP (%s-byte body)", bodyLength));
                }
            }
            log.debug(String.join("\n", logs));
            return response;
        }
        return response;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        // Not using SLF4J's support for parameterized messages (even though it would be more efficient) because it would
        // require the incoming message formats to be SLF4J-specific.
        if (log.isDebugEnabled()) {
            log.debug(format(configKey, format, args));
        }
    }

    private String format(String configKey, String format, Object... args) {
        return String.format(methodTag(configKey) + format, args);
    }
}