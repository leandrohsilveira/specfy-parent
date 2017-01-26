package com.github.leandrohsilveira.specfy.exceptions;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;
import com.github.leandrohsilveira.specfy.exceptions.http.client.BadRequest;
import com.github.leandrohsilveira.specfy.exceptions.http.client.Conflict;
import com.github.leandrohsilveira.specfy.exceptions.http.client.ExpectationFailed;
import com.github.leandrohsilveira.specfy.exceptions.http.client.Forbidden;
import com.github.leandrohsilveira.specfy.exceptions.http.client.Gone;
import com.github.leandrohsilveira.specfy.exceptions.http.client.LengthRequired;
import com.github.leandrohsilveira.specfy.exceptions.http.client.MethodNotAllowed;
import com.github.leandrohsilveira.specfy.exceptions.http.client.NotAcceptable;
import com.github.leandrohsilveira.specfy.exceptions.http.client.NotFound;
import com.github.leandrohsilveira.specfy.exceptions.http.client.PaymentRequired;
import com.github.leandrohsilveira.specfy.exceptions.http.client.PreconditionFailed;
import com.github.leandrohsilveira.specfy.exceptions.http.client.ProxyAuthenticationRequired;
import com.github.leandrohsilveira.specfy.exceptions.http.client.RequestEntityTooLarge;
import com.github.leandrohsilveira.specfy.exceptions.http.client.RequestTimeout;
import com.github.leandrohsilveira.specfy.exceptions.http.client.RequestURITooLong;
import com.github.leandrohsilveira.specfy.exceptions.http.client.RequestedRangeNotSatisfiable;
import com.github.leandrohsilveira.specfy.exceptions.http.client.Unauthorized;
import com.github.leandrohsilveira.specfy.exceptions.http.client.UnsupportedMediaType;
import com.github.leandrohsilveira.specfy.exceptions.http.server.BadGateway;
import com.github.leandrohsilveira.specfy.exceptions.http.server.GatewayTimeout;
import com.github.leandrohsilveira.specfy.exceptions.http.server.HTTPVersionNotSupported;
import com.github.leandrohsilveira.specfy.exceptions.http.server.InternalServerError;
import com.github.leandrohsilveira.specfy.exceptions.http.server.NotImplemented;
import com.github.leandrohsilveira.specfy.exceptions.http.server.ServiceUnavailable;

public abstract class ResponseException extends Exception {

	private static final long serialVersionUID = -4737642975124215055L;

	public ResponseException(Request request) {
		super(request.getResponse().getStatusText());
		this.request = request;
	}

	private Request request;

	public Request getRequest() {
		return request;
	}

	public static void checkResponseStatus(Request request) throws ClientError, ServerError {
		int status = request.getResponse().getStatus();
		if (status >= 400) {
			switch (status) {
				case 400:
					throw new BadRequest(request);
				case 401:
					throw new Unauthorized(request);
				case 402:
					throw new PaymentRequired(request);
				case 403:
					throw new Forbidden(request);
				case 404:
					throw new NotFound(request);
				case 405:
					throw new MethodNotAllowed(request);
				case 406:
					throw new NotAcceptable(request);
				case 407:
					throw new ProxyAuthenticationRequired(request);
				case 408:
					throw new RequestTimeout(request);
				case 409:
					throw new Conflict(request);
				case 410:
					throw new Gone(request);
				case 411:
					throw new LengthRequired(request);
				case 412:
					throw new PreconditionFailed(request);
				case 413:
					throw new RequestEntityTooLarge(request);
				case 414:
					throw new RequestURITooLong(request);
				case 415:
					throw new UnsupportedMediaType(request);
				case 416:
					throw new RequestedRangeNotSatisfiable(request);
				case 417:
					throw new ExpectationFailed(request);

				case 500:
					throw new InternalServerError(request);
				case 501:
					throw new NotImplemented(request);
				case 502:
					throw new BadGateway(request);
				case 503:
					throw new ServiceUnavailable(request);
				case 504:
					throw new GatewayTimeout(request);
				case 505:
					throw new HTTPVersionNotSupported(request);

				default:
					throw new UnknownError("Unknown HTTP Error Status " + status);
			}
		}
	}

}
