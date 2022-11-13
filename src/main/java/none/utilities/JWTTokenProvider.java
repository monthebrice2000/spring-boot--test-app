package none.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import none.constants.SecurityConstant;
import none.service.dto.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    public String generateJwtToken(UserPrincipal userPrincipal) {
        String[] claims = getClaimsFromUser(userPrincipal);
        return JWT
            .create()
            .withIssuer(SecurityConstant.GET_ARRAYS_LLC)
            .withAudience(SecurityConstant.GET_ARRAYS_ADMINISTRATION)
            .withIssuedAt(new Date())
            .withSubject(userPrincipal.getUsername())
            .withArrayClaim(SecurityConstant.AUTHORITIES, claims)
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(this.secret.getBytes()));
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        String[] claims = getClaimsFromToken(token);
        return Arrays.stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, null, authorities);
        upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return upat;
    }

    public boolean isValidToken(String username, String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getJwtVerifier(token).verify(token).getExpiresAt().before(new Date());
    }

    public String getSubject(String token) {
        return getJwtVerifier(token).verify(token).getSubject();
    }

    private String[] getClaimsFromToken(String token) {
        JWTVerifier verifier = getJwtVerifier(token);
        return verifier.verify(token).getClaim(SecurityConstant.AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJwtVerifier(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(secret.getBytes())).withIssuer(SecurityConstant.GET_ARRAYS_LLC).build();
            return verifier;
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(SecurityConstant.TOKEN_CANNOT_BE_VERIFIED);
        }
    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
        List<String> claims = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : userPrincipal.getAuthorities()) {
            claims.add(grantedAuthority.getAuthority());
        }
        return claims.toArray(new String[0]);
    }
}
