package com.growthers.bms;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BmsAuthConfiguration {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        http.formLogin(form -> form.defaultSuccessUrl("/guest/myPage"));
        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        jdbcTemplate.execute("""
                create table if not exists users(
                    username varchar(50) primary key,
                    password varchar(500) not null,
                    enabled boolean not null default true
                );
                create table if not exists authorities (
                    username varchar(50),
                    authority varchar(50),
                    primary key (username, authority)
                );
                """);

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        // 動作確認用のユーザを登録しています。不要であれば以下のコードを削除してください。
        PasswordEncoder enc = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String username = "test";
        if (false == users.userExists(username)) {
            users.createUser(User
                    .withUsername(username)
                    .password(enc.encode("test"))
                    .roles("USERS")
                    .build());
        }

        return users;
    }
}
