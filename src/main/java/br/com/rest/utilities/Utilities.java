package br.com.rest.utilities;

import org.springframework.util.StringUtils;

public class Utilities {

	public static Long converteParaLong(String longString) {
		if(StringUtils.isEmpty(longString))
			return null;
		
		Long longConvertido = new Long(longString);

		return longConvertido;
	}

}
