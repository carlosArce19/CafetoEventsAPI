package co.cafeto.EventManager.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import co.cafeto.EventManager.dto.PersonTokenDTO;
import co.cafeto.EventManager.exception.JWTFormatException;

@Component
public class JWTUtil implements IJWTUtil {

	@Override
	public PersonTokenDTO getPersonFronJWT(String jwtToken)  throws JWTFormatException{
		try {
			String[] split_string = jwtToken.split("\\.");
	        String base64EncodedBody = split_string[1];
	        Base64 base64Url = new Base64(true);

	        String body = new String(base64Url.decode(base64EncodedBody));
	        System.out.println(body);
	        Gson gson =new Gson();
	        
	        PersonTokenDTO personTokenDTO = gson.fromJson(body, PersonTokenDTO.class);
	        
	        if(personTokenDTO.getEmail() == null || personTokenDTO.getEmail().trim().isEmpty()) {
	        	throw new JWTFormatException();
	        }
	        
			return personTokenDTO;
		} catch (Exception e) {
			throw new JWTFormatException();
		}
        
	}

}
