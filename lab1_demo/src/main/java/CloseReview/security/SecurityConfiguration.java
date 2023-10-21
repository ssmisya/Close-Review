package CloseReview.security;
/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import CloseReview.registration.Exception.NullUserNameException;
import CloseReview.registration.Exception.PasswordNotMatchException;
import CloseReview.registration.Exception.UserAlreadyExistException;
import CloseReview.registration.MyJdbcUserDetailsManager;
import CloseReview.registration.UserDto;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   HandlerMappingIntrospector introspector) throws Exception {

        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        http.csrf(csrfConfigurer ->
                csrfConfigurer.ignoringRequestMatchers(mvcMatcherBuilder.pattern("/**"),
                        PathRequest.toH2Console()));
        http.headers(headersConfigurer ->
                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http
                .authorizeRequests((authorize) -> authorize
                        //这里想要放行某个访问，当有2个servelet时只能用这种方法，SB开发者整一堆表示方法，新版本中全都用不了了
                        .requestMatchers(new AntPathRequestMatcher("/registration")).permitAll()
                        //放行某个角色对某页面的访问
                        .requestMatchers(new AntPathRequestMatcher("/dev/**")).hasRole("Administer")
                        .requestMatchers(new AntPathRequestMatcher("/h2/**")).access("hasRole('Administer') or hasRole('DBA')")
//                        .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                        .anyRequest().authenticated()
                )
                //登录页面
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                );
        http
                //logout设置
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
//                .logoutSuccessHandler(logoutSuccessHandler)
                .invalidateHttpSession(true);
//                .addLogoutHandler(logoutHandler)
//                .deleteCookies(cookieNamesToClear)
        // @formatter:on
        return http.build();
    }

    //默认的密码编码类
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(16);
    }


    //数据源
    @Bean("H2Datasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:./lab1_demo/db/h2_db_file");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }


    //用户管理类
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        //初始化jdbc用户信息管理对象
        MyJdbcUserDetailsManager users = new MyJdbcUserDetailsManager(dataSource);
        //用户注册统一接口
        try {
            //初始化待插入信息
            UserDto user = new UserDto(
                    "dev",
                    "dev",
                    "password",
                    "password",
                    "abc@somemail.com",
                    "fdu",
                    "China",
                    "Administer"
            );
            //插入用户至数据库
            users.createUser(user);
            }catch (UserAlreadyExistException | NullUserNameException | PasswordNotMatchException e){
            e.printStackTrace();
        }
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("dev")
//                .password("password")
//                .roles("Administer")
//                .build();
//        users.setUsersByUsernameQuery("SELECT user_name AS username,password,enabled FROM user_info where user_name = ?");
//        users.setAuthoritiesByUsernameQuery("SELECT user_name AS username,role AS authority FROM user_role where user_name=?");
//        users.setCreateUserSql("INSERT INTO user_info (user_name, password ,enabled, email, organization, region) VALUES(?,?,?,'null','null','null');");
//        users.setCreateAuthoritySql("INSERT INTO user_role (user_name,role) VALUES (?,?)");
        return users;
    }
}


