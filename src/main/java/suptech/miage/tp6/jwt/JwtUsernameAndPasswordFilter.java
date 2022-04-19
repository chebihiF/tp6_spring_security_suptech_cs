package suptech.miage.tp6.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUsernameAndPasswordFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {

            UsernameAndPasswordRequest usernameAndPasswordRequest =
                    new ObjectMapper().readValue
                            (request.getInputStream(), UsernameAndPasswordRequest.class);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    usernameAndPasswordRequest.getUsername(),
                    usernameAndPasswordRequest.getPassword()
            );

            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        String secretKey = "AREYGDK16381JZKDL9182210?DJDO29203";

        String access_token = Jwts.builder()
                .setSubject(authResult.getName()) //ID, username
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+30*1000))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();

        String refresh_token = Jwts.builder()
                .setSubject(authResult.getName()) //ID, username
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusMonths(6)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();

        Map<String,String> tokens = new HashMap<String,String>();
        tokens.put("access_token",access_token);
        tokens.put("refresh_token",refresh_token);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
        //response.getWriter().println("Authorization : Bearer "+token);
        //response.addHeader("Authorization","Bearer "+token);
    }
}
