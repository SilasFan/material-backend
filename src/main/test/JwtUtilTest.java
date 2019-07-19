import com.material.jwt.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtUtilTest {
    @Autowired
    public static JwtUtil jwtUtil = new JwtUtil();

    public static void main(String[] args) {
        jwtUtil.createJWT("111","222","user");
    }

}
