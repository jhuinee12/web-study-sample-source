package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;	// 스프링 설정 클래스 의미
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import spring.cust_data.MemberDao;
import spring.cust_join.MemberRegisterService;
import spring.cust_pw_change.ChangePasswordService;
import spring.printer.MemberInfoPrinter;
import spring.printer.MemberListPrinter;
import spring.printer.MemberPrinter;

@Configuration
public class DBConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();	// 드라이버 객체 생성
		ds.setDriverClassName("com.mysql.jdbc.Driver");	// JDBC 드라이버 클래스 지정
//		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");	// 인코딩 utf8
		// [Error] javax.net.ssl.SSLException
		ds.setUrl("jdbc:mysql://localhost:3306/spring5fs?useSSL=false&characterEncoding=utf8");	// 인코딩 utf8
		ds.setUsername("spring5");	// db 연결 이름
		ds.setPassword("spring5");	// db 연결 암호
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}
	
	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
		return infoPrinter;
	}
}
