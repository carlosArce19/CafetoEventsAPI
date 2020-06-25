package co.cafeto.EventManager.util;

import co.cafeto.EventManager.dto.PersonTokenDTO;
import co.cafeto.EventManager.exception.JWTFormatException;

public interface IJWTUtil {

	PersonTokenDTO getPersonFronJWT(String jwtToken) throws JWTFormatException;
}
