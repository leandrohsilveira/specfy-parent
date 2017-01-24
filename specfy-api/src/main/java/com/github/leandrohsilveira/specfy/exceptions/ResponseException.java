package com.github.leandrohsilveira.specfy.exceptions;

import com.github.leandrohsilveira.specfy.RequestSpec;
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
	private RequestSpec requestSpec;

	public ResponseException(RequestSpec requestSpec) {
		super(requestSpec.getResponse().getStatusText());
		this.requestSpec = requestSpec;
	}

	public RequestSpec getRequestSpec() {
		return requestSpec;
	}

	public static void checkResponseStatus(RequestSpec requestSpec) throws ClientError, ServerError {
		int status = requestSpec.getResponse().getStatus();
		if (status >= 400) {
			switch (status) {
				case 400:
					throw new BadRequest(requestSpec);
				case 401:
					throw new Unauthorized(requestSpec);
				case 402:
					throw new PaymentRequired(requestSpec);
				case 403:
					throw new Forbidden(requestSpec);
				case 404:
					throw new NotFound(requestSpec);
				case 405:
					throw new MethodNotAllowed(requestSpec);
				case 406:
					throw new NotAcceptable(requestSpec);
				case 407:
					throw new ProxyAuthenticationRequired(requestSpec);
				case 408:
					throw new RequestTimeout(requestSpec);
				case 409:
					throw new Conflict(requestSpec);
				case 410:
					throw new Gone(requestSpec);
				case 411:
					throw new LengthRequired(requestSpec);
				case 412:
					throw new PreconditionFailed(requestSpec);
				case 413:
					throw new RequestEntityTooLarge(requestSpec);
				case 414:
					throw new RequestURITooLong(requestSpec);
				case 415:
					throw new UnsupportedMediaType(requestSpec);
				case 416:
					throw new RequestedRangeNotSatisfiable(requestSpec);
				case 417:
					throw new ExpectationFailed(requestSpec);

				case 500:
					throw new InternalServerError(requestSpec);
				case 501:
					throw new NotImplemented(requestSpec);
				case 502:
					throw new BadGateway(requestSpec);
				case 503:
					throw new ServiceUnavailable(requestSpec);
				case 504:
					throw new GatewayTimeout(requestSpec);
				case 505:
					throw new HTTPVersionNotSupported(requestSpec);

				default:
					throw new UnknownError("Unknown HTTP Error Status " + status);
			}
		}
	}

}
