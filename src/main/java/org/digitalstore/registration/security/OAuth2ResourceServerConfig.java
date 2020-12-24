package org.digitalstore.registration.security;

import org.digitalstore.registration.constant.RegistrationConstants;
import org.digitalstore.registration.constant.RegistrationPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Value("${sdx.security.enable}")
	private Boolean enableOAuth;

	@Autowired
	private CustomAccessTokenConverter customAccessTokenConverter;

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// http.authorizeRequests().antMatchers("/**/create").authenticated();
		// http.authorizeRequests().antMatchers("/**/update").authenticated();
		// http.authorizeRequests().antMatchers("/**/delete").authenticated();

		// http.authorizeRequests().antMatchers("/**/create").hasAuthority("USER").anyRequest().authenticated();
		// http.authorizeRequests().antMatchers("/**/delete").hasAuthority("ADMINISTRATOR").anyRequest().authenticated();

		if (enableOAuth) {
			http.authorizeRequests().antMatchers(RegistrationPath.NO_SECURITY_URL_LIST).permitAll();
			http.authorizeRequests().antMatchers("/**/delete").hasAuthority("ADMINISTRATOR").anyRequest()
					.authenticated();
		} else {
			http.authorizeRequests().anyRequest().permitAll();
		}

		http.formLogin().loginPage("/login").permitAll()//
				.and().logout().permitAll();
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	protected JwtAccessTokenConverter accessTokenConverter() {
		// KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new
		// ClassPathResource("jwt.jks"), "mySecretKey".toCharArray());
		// JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		// converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));

		// -- for the simple demo purpose, used the secret for signing.
		// -- for production, it is recommended to use public/private key pair

		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setAccessTokenConverter(customAccessTokenConverter);
		converter.setSigningKey(RegistrationConstants.OAUTH_CLIENT_USER);
		return converter;

	}
}
