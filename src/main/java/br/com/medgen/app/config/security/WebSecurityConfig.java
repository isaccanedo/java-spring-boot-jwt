package br.com.isaccanedo.app.config.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration      //indica que é uma classe de configuracao
@EnableWebSecurity  //annotation para habilitar seguranca
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AutenticacaoService autenticacaoService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // esse eh o metodo que serve para configurar autorizacoes (url, perfil de acesso a urls)
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/produtos") /* qual url, metodo? permitir ou nao? */.permitAll() //libera acesso, por exemplo apenas para o GET
                .antMatchers(HttpMethod.GET,"/produtos/*").permitAll()
                .antMatchers(HttpMethod.GET, "/home").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()  //desabilitar essa validação
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // avisar para o spring que a autenticacao sera stateless
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // configuracoes de recursos estaticos (css, js, imagens)

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // esse eh o metodo que serve para configurar autenticacoes (login, controle de acesso)
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
