package patterns;

import java.util.*;

class HttpRequest {
    final String method, url, body;
    final Map<String, String> headers;

    private HttpRequest(Builder b) {
        this.method = b.method; this.url = b.url; this.body = b.body;
        this.headers = Collections.unmodifiableMap(new LinkedHashMap<>(b.headers));
    }

    static class Builder {
        private String method = "GET";
        private String url;
        private String body;
        private final Map<String, String> headers = new LinkedHashMap<>();

        public Builder url(String url) { this.url = url; return this; }
        public Builder method(String method) { this.method = method; return this; }
        public Builder header(String k, String v) { this.headers.put(k, v); return this; }
        public Builder body(String body) { this.body = body; return this; }

        public HttpRequest build() {
            if (url == null || url.isBlank()) throw new IllegalStateException("URL is required");
            if (!Arrays.asList("GET","POST","PUT","DELETE","PATCH").contains(method))
                throw new IllegalStateException("Unsupported method: " + method);
            return new HttpRequest(this);
        }
    }

    @Override public String toString() {
        return String.format("HttpRequest{method=%s, url=%s, headers=%s, body=%s}",
                method, url, headers, body);
    }
}