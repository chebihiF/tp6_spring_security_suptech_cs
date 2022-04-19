package suptech.miage.tp6.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        if(token != null || token.startsWith("Bearer ")){
            token = token.replace("Bearer ","");
            String secretKey = "AREYGDK16381JZKDL9182210?DJDO29203";
            try {
                Jws<Claims> claimsJws = Jwts
                        .parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                        .build().parseClaimsJws(token);
                String username = claimsJws.getBody().getSubject();
                User user = userRepository.findById(username).get();

                List<Map<String,String>> authorities = new ArrayList<>();
                for(Authority authority : user.getAuthorities()) {
                    Map<String,String> map = new HashMap<String,String>();
                    map.put("authority", authority.getName());
                    authorities.add(map);
                }

                String access_token = Jwts.builder()
                        .setSubject(user.getUsername()) //ID, username
                        .claim("authorities", authorities)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis()+30*1000))
                        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                        .compact();

                response.getWriter().write("Bearer "+access_token);

            }catch (JwtException e){
                e.printStackTrace();
                //throw new IllegalStateException("Token "+token+" cannot be trusted");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
