package com.bodybuddy.hey.userClass;

public class DBException extends RuntimeException {
	public DBException() {
		super("/DB Transaction 예외 발생: 스프링은 RuntimeException(선택)예외만 인정한다."
				+"필수 예외가 발생한 경우는 RuntimeException예외로 변환한다.");
	}
}