package org.digitalstore.registration.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.digitalstore.registration.constant.RegistrationPath;
import org.digitalstore.registration.constant.SwaggerConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerHelper.
 */
@Configuration
@EnableSwagger2
public class SwaggerHelper extends SwaggerConstants
{

	@Value("${sdx.oauth.authentication.path}")
	private String authenticationPath;

	/**
	 * Api.
	 * 
	 * @return the docket
	 */
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("org.digitalstore.registration.controller"))
					.paths(PathSelectors.any()).build().apiInfo(apiEndPointsInfo())
					.tags(new Tag(REGISTRATION_TAG, REGISTRATION_DESCRIPTION))
					.globalOperationParameters(Arrays.asList(
								new ParameterBuilder().name(RegistrationPath.HEADER_USER_NAME)
											.description("Enter the User Name")
											.modelRef(new ModelRef("string"))
											.parameterType("header").required(false).build()))
					.securitySchemes(Arrays.asList(securityScheme()))
					.securityContexts(Arrays.asList(securityContext()));
	}

	@Bean
	public SecurityConfiguration security()
	{
		return SecurityConfigurationBuilder.builder()
					// .clientId(AdminConstants.OAUTH_CLIENT_USER)
					// .clientSecret(AdminConstants.OAUTH_CLIENT_CREDENTIAL).
					// scopeSeparator(" ")
					.useBasicAuthenticationWithAccessCodeGrant(true).build();
	}

	private List<SecurityReference> defaultAuth()
	{
		return Collections
					.singletonList(new SecurityReference(OAUTH2SCHEMA, getAuthorizationScopes()));
	}

	private AuthorizationScope[] getAuthorizationScopes()
	{
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
		authorizationScopes[0] = new AuthorizationScope(READ, READ_ALL);
		authorizationScopes[1] = new AuthorizationScope(TRUST, TRUST_ALL);
		authorizationScopes[2] = new AuthorizationScope(WRITE, WRITE_ALL);
		return authorizationScopes;
	}

	private SecurityContext securityContext()
	{
		return SecurityContext.builder().securityReferences(defaultAuth())
					.forPaths(PathSelectors.any()).build();
	}

	private SecurityScheme securityScheme()
	{

		List<GrantType> grantTypes = new ArrayList<>();
		GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(authenticationPath);
		grantTypes.add(creGrant);
		return new OAuth(OAUTH2SCHEMA, Arrays.asList(getAuthorizationScopes()), grantTypes);
	}

	/**
	 * Api end points info.
	 * 
	 * @return the api info
	 */
	private ApiInfo apiEndPointsInfo()
	{
		return new ApiInfoBuilder().title("Service DX").version("2.0")
					.description("User API Details").build();
	}
}
