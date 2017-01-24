package com.github.leandrohsilveira.specfy;

public enum Header {

	CACHE_CONTROL("Cache-Control"),
	CONTENT_LENGTH("Content-Length"),
	CONTENT_TYPE("Content-Type"),
	DATE("Date"),
	PRAGMA("Pragma"),
	VIA("Via"),
	WARNING("Warning"),
	ACCEPT("Accept"),
	ACCEPT_CHARSET("Accept-Charset"),
	ACCEPT_ENCODING("Accept-Encoding"),
	ACCEPT_LANGUAGE("Accept-Language"),
	ACCESS_CONTROL_REQUEST_HEADERS("Access-Control-Request-Headers"),
	ACCESS_CONTROL_REQUEST_METHOD("Access-Control-Request-Method"),
	AUTHORIZATION("Authorization"),
	CONNECTION("Connection"),
	COOKIE("Cookie"),
	EXPECT("Expect"),
	FROM("From"),
	HOST("Host"),
	IF_MATCH("If-Match"),
	IF_MODIFIED_SINCE("If-Modified-Since"),
	IF_NONE_MATCH("If-None-Match"),
	IF_RANGE("If-Range"),
	IF_UNMODIFIED_SINCE("If-Unmodified-Since"),
	LAST_EVENT_ID("Last-Event-ID"),
	MAX_FORWARDS("Max-Forwards"),
	ORIGIN("Origin"),
	PROXY_AUTHORIZATION("Proxy-Authorization"),
	RANGE("Range"),
	REFERER("Referer"),
	TE("TE"),
	UPGRADE("Upgrade"),
	USER_AGENT("User-Agent"),
	ACCEPT_RANGES("Accept-Ranges"),
	ACCESS_CONTROL_ALLOW_HEADERS("Access-Control-Allow-Headers"),
	ACCESS_CONTROL_ALLOW_METHODS("Access-Control-Allow-Methods"),
	ACCESS_CONTROL_ALLOW_ORIGIN("Access-Control-Allow-Origin"),
	ACCESS_CONTROL_ALLOW_CREDENTIALS("Access-Control-Allow-Credentials"),
	ACCESS_CONTROL_EXPOSE_HEADERS("Access-Control-Expose-Headers"),
	ACCESS_CONTROL_MAX_AGE("Access-Control-Max-Age"),
	AGE("Age"),
	ALLOW("Allow"),
	CONTENT_DISPOSITION("Content-Disposition"),
	CONTENT_ENCODING("Content-Encoding"),
	CONTENT_LANGUAGE("Content-Language"),
	CONTENT_LOCATION("Content-Location"),
	CONTENT_MD5("Content-MD5"),
	CONTENT_RANGE("Content-Range"),
	ETAG("ETag"),
	EXPIRES("Expires"),
	LAST_MODIFIED("Last-Modified"),
	LINK("Link"),
	LOCATION("Location"),
	P3P("P3P"),
	PROXY_AUTHENTICATE("Proxy-Authenticate"),
	REFRESH("Refresh"),
	RETRY_AFTER("Retry-After"),
	SERVER("Server"),
	SET_COOKIE("Set-Cookie"),
	SET_COOKIE2("Set-Cookie2"),
	TRAILER("Trailer"),
	TRANSFER_ENCODING("Transfer-Encoding"),
	VARY("Vary"),
	WWW_AUTHENTICATE("WWW-Authenticate"),
	DNT("DNT"),
	X_CONTENT_TYPE_OPTIONS("X-Content-Type-Options"),
	X_DO_NOT_TRACK("X-Do-Not-Track"),
	X_FORWARDED_FOR("X-Forwarded-For"),
	X_FORWARDED_PROTO("X-Forwarded-Proto"),
	X_FRAME_OPTIONS("X-Frame-Options"),
	X_POWERED_BY("X-Powered-By"),
	X_REQUESTED_WITH("X-Requested-With"),
	X_USER_IP("X-User-IP"),
	X_XSS_PROTECTION("X-XSS-Protection");

	private Header(String headerName) {
		this.headerName = headerName;
	}

	private String headerName;

	public String getHeaderName() {
		return headerName;
	}
}
