package com.kh.jdbc.day00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRUN {
	public static void main(String[] args) {
		/* 
		 <<JDBC 코딩 절차 무조건 외우기>>
		 * 1. 드라이버 등록
		 * 2. dbms 연결 생성 (연결된 상태)
		 * 3. statement 객체 생성 (쿼리문 실행 준비)
		 * 4. SQL 전송 (쿼리문 실행된 상태)
		 * 5. 결과값 받기 (ResultSet을 받은 상태) -> 후처리는 나중에
		 * 6. 자원해제 (닫기 close)
		 */		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //고정
		/* jdbc: jdbc 라이브러리 사용
		 * oracle: oracle db 사용
		 * thin: JDBC 드라이버 타입으로 thin은 자바로 연결한다는 옵션인듯 (반대?는 OCI -> OS로 작동)
		 * DB IP
		 * DB 포트
		 * XE :  Oracle database client의 고유한 service name (default로 고정되는 값이라 보면 됨)
		 */
		String user = "KH";
		String pwd = "KH";
		String sql = "SELECT * FROM EMPLOYEE";
		//1. 드라이버 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //고정
			//2. db연결생성
			Connection conn = DriverManager.getConnection(url,user, pwd); //객체 생성(new) 안하고 바로 메소드 호출할 수 있는 이유는
			//DriverManager에 있는 메소드가 static메소드여서 가능함.
			
			//3. 쿼리문 실행준비 (statement 객체생성)
			Statement stmt = conn.createStatement();
			//쿼리문 실행준비 완료
			
			//4. 쿼리문 실행 + 5. 결과값 받기(ResultSet)
			ResultSet rset = stmt.executeQuery(sql);
			// 후처리 - db에서 가져온 데이터 사용
			
			if(rset.next()) { //다음값이 있는지 없는지 ture, false로 판단
				System.out.println("사원 아이디: "+rset.getString("EMP_ID"));
				System.out.println("사원 이름: "+rset.getString("EMP_NAME"));
			}
			
			//while문으로 바꾸면 전체 데이터를 가져오게 됨
			while(rset.next()) { //다음값이 있는지 없는지 ture, false로 판단
				System.out.println("사원 아이디: "+rset.getString("EMP_ID"));
				System.out.println("사원 이름: "+rset.getString("EMP_NAME"));
			}
			
			
			
			//자원해제
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
}
