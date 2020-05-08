package com.lwb.retrofit;

import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public interface CookieJar {

    /** A cookie jar that never accepts any cookies. */
    okhttp3.CookieJar NO_COOKIES = new okhttp3.CookieJar() {
        @Override public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        }

        @Override public List<Cookie> loadForRequest(HttpUrl url) {
            return Collections.emptyList();
        }
    };

    /**
     * Saves {@code cookies} from an HTTP response to this store according to this jar's policy.
     *
     * <p>Note that this method may be called a second time for a single HTTP response if the response
     * includes a trailer. For this obscure HTTP feature, {@code cookies} contains only the trailer's
     * cookies.
     */
    void saveFromResponse(HttpUrl url, List<Cookie> cookies);

    /**
     * Load cookies from the jar for an HTTP request to {@code url}. This method returns a possibly
     * empty list of cookies for the network request.
     *
     * <p>Simple implementations will return the accepted cookies that have not yet expired and that
     * {@linkplain Cookie#matches match} {@code url}.
     */
    List<Cookie> loadForRequest(HttpUrl url);
}
