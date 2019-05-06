package Modelo;

public class Hash {
	public static String getHash(String txt, String hashType) {
		/**
		 * CON ESTA FUNCION CONSEGUIMOS CIFRAR EL PASSWORD QUE EL USUARIO NOS DA EN EL
		 * REGISTRO Y QUE POR SEGURIDAD GUARDAREMOS DE ESTA FORMA EN LA TABLA USUARIO
		 */
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
			byte[] array = md.digest(txt.getBytes());
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xff) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static String md5(String txt) {
		return Hash.getHash(txt, "MD5");
	}

	public static String sha1(String txt) {
		return Hash.getHash(txt, "SHA1");
	}
}
