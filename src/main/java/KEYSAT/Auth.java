package KEYSAT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Auth {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/", "/home").permitAll()
						.requestMatchers("/secret").hasRole("USER")
						.anyRequest().authenticated())
				.formLogin((form) -> form
						.loginPage("/login")
						.permitAll())
				.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return (username) -> {
			KEYSAT.User user = userRepository.findByUsername(username);
			if (user != null) {
				UserDetails userDetails = User
						.withUsername(user.getUsername())
						.password(user.getPassword())
						.roles(user.getRoles().stream().map(Role::getName).toArray(String[]::new))
						.build();
				return userDetails;
			} else {
				throw new UsernameNotFoundException("User not found: " + username);
			}
		};
	}
}
