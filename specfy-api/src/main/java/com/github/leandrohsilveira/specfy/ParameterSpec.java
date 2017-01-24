package com.github.leandrohsilveira.specfy;

public class ParameterSpec {

	protected ParameterSpec(ParameterType type, String name, String regex, boolean required) {
		this(type, name, regex, required, null);
	}

	protected ParameterSpec(ParameterType type, String name, String regex, boolean required, Object defaultValue) {
		this.type = type;
		this.name = name;
		this.regex = regex;
		this.defaultValue = defaultValue;
		this.required = required;
		this.fixed = false;
	}

	protected ParameterSpec(ParameterType type, String name, Object fixedValue) {
		this.type = type;
		this.name = name;
		this.defaultValue = fixedValue;
		this.fixed = true;
	}

	protected String name;

	protected ParameterType type;

	protected String regex;

	protected boolean required;

	protected Object defaultValue;

	protected boolean fixed;

	public String getName() {
		return name;
	}

	public ParameterType getType() {
		return type;
	}

	public String getRegex() {
		return regex;
	}

	public boolean isRequired() {
		return required;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public boolean isFixed() {
		return fixed;
	}

}