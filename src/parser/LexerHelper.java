package parser;

public class LexerHelper {
	
	public static int lexemeToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

	public static boolean lexemeToBoolean(String str) {
		return Boolean.parseBoolean(str);
	}

	public static char lexemeToChar( String str ) {

		String lexeme = str.substring(1, str.length()-1);
		char result = Character.MIN_VALUE;

		if ( lexeme.charAt(0) == '\\' ) {
			try {
				//  Decimal character literal
				String aux = lexeme.substring( 1 );
				result = (char) Integer.parseInt( aux );
			} catch ( NumberFormatException nfe ) {

				// not a number => special character
				switch (lexeme.charAt(1)){
					case 'n':
						result = '\n';
						break;
					case 't':
						result = '\t';
						break;
					default:
						System.err.println( "Invalid Character Literal" );
				}
				return result;
			}
		} else {
			//  Simple character literal
			result = lexeme.charAt( 0 );
		}
		return result;
	}

	public static double lexemeToReal(String str) {
		try {
			return Double.parseDouble(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}
}
