package com.github.leandrohsilveira.specfy.exceptions;

import com.github.leandrohsilveira.specfy.Response;
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

	public ResponseException(Response response) {
		super(response.getStatusText());
		this.response = response;
	}

	private Response response;

	public Response getResponse() {
		return response;
	}

	public static void checkResponseStatus(Response response) throws ClientError, ServerError {
		int status = response.getStatus();
		if (status >= 400) {
			switch (status) {
				case 400:
					throw new BadRequest(response);
				case 401:
					throw new Unauthorized(response);
				case 402:
					throw new PaymentRequired(response);
				case 403:
					throw new Forbidden(response);
				case 404:
					throw new NotFound(response);
				case 405:
					throw new MethodNotAllowed(response);
				case 406:
					throw new NotAcceptable(response);
				case 407:
					throw new ProxyAuthenticationRequired(response);
				case 408:
					throw new RequestTimeout(response);
				case 409:
					throw new Conflict(response);
				case 410:
					throw new Gone(response);
				case 411:
					throw new LengthRequired(response);
				case 412:
					throw new PreconditionFailed(response);
				case 413:
					throw new RequestEntityTooLarge(response);
				case 414:
					throw new RequestURITooLong(response);
				case 415:
					throw new UnsupportedMediaType(response);
				case 416:
					throw new RequestedRangeNotSatisfiable(response);
				case 417:
					throw new ExpectationFailed(response);

				case 500:
					throw new InternalServerError(response);
				case 501:
					throw new NotImplemented(response);
				case 502:
					throw new BadGateway(response);
				case 503:
					throw new ServiceUnavailable(response);
				case 504:
					throw new GatewayTimeout(response);
				case 505:
					throw new HTTPVersionNotSupported(response);

				default:
					throw new UnknownError("Unknown HTTP Error Status " + status);
			}
		}
	}

}
