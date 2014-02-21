package com.excilys.formation.projet.validation;

public class ValidationMessage {
	private String message;
	private boolean valid;

	private ValidationMessage(Builder b){
		this.setMessage(b.getMessage());
		this.setValid(b.isValid());
	}
	@Override
	public String toString() {
		return "ValidationMessage [message=" + message + ", valid=" + valid
				+ "]";
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static class Builder{
		private String message;
		private boolean valid;
		
		public Builder(){
			this.setMessage("");
			this.setValid(false);
		}
		
		public Builder message(String message){
			this.setMessage(message);
			return this;
		}
		
		public Builder valid(boolean valid){
			this.setValid(valid);
			return this;
		}
		
		public ValidationMessage build(){
			return new ValidationMessage(this);
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public boolean isValid() {
			return valid;
		}

		public void setValid(boolean valid) {
			this.valid = valid;
		}
	}
}
