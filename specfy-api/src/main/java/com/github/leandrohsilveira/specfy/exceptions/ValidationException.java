package com.github.leandrohsilveira.specfy.exceptions;

import java.util.List;
import java.util.Objects;

import com.github.leandrohsilveira.specfy.ParameterSpec;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 5806848335908711181L;

	public ValidationException(String message, List<Detail> details) {
		super(message);
		this.details = details;
	}

	private List<Detail> details;

	public List<Detail> getDetails() {
		return details;
	}

	public static class Detail {

		public Detail(ParameterSpec parameterSpec, Failure failure, Object value) {
			super();
			this.parameterSpec = parameterSpec;
			this.failure = failure;
			this.setValue(value);
		}

		private ParameterSpec parameterSpec;

		private Failure failure;

		private Object value;

		@Override
		public String toString() {
			switch (this.failure) {
				case INVALID:
					return String.format("The %s parameter \"%s\" value \"%s\" is invalid for pattern %s.", parameterSpec.getType().name().toLowerCase(), parameterSpec.getName(), Objects.toString(value), parameterSpec.getRegex());
				case INVALID_SIZE:
					return String.format("The %s parameter \"%s\" exceeded the values limit of 1 providing %s values.", parameterSpec.getType().name().toLowerCase(), parameterSpec.getName(), Objects.toString(value));
				case MISSING_BODY:
					return "The required request body were not provided";
				case MISSING:
				default:
					return String.format("The required %s parameter \"%s\" were not provided.", parameterSpec.getType().name().toLowerCase(), parameterSpec.getName());
			}
		}

		public static enum Failure {

			MISSING, INVALID, INVALID_SIZE, MISSING_BODY;

		}

		public ParameterSpec getParameterSpec() {
			return parameterSpec;
		}

		public void setParameterSpec(ParameterSpec parameterSpec) {
			this.parameterSpec = parameterSpec;
		}

		public Failure getFailure() {
			return failure;
		}

		public void setFailure(Failure failure) {
			this.failure = failure;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

	}

}
