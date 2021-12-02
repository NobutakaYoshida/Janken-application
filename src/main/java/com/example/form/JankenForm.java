package com.example.form;

import javax.validation.constraints.NotNull;

public class JankenForm {
	
	@NotNull(message="選択してください")
	private Integer hitonote;

	public Integer getHitonote() {
		return hitonote;
	}

	public void setHitonote(Integer hitonote) {
		this.hitonote = hitonote;
	}

	@Override
	public String toString() {
		return "JankenForm [hitonote=" + hitonote + "]";
	}

}
