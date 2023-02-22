package com.app.MovieApp.security.encryptdecrypt;

import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FaSoftwareSolutinonEncryptionDecryption {

	private static String UTF = "UTF-8";
	/*
	 * private Pattern BCRYPT_PATTERN = Pattern
	 * .compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public FaSoftwareSolutinonEncryptionDecryption() {
		/*
		 * 
		 */
	}

	public String encryptionText(String text, String key) {
		String encText = "";
		byte[] keyArray = new byte[24];
		byte[] toEncryptArray = null;
		try {
			toEncryptArray = text.getBytes(UTF);
			MessageDigest m = MessageDigest.getInstance("MD5");
			byte temporaryKey[] = m.digest(key.getBytes(UTF));
			if (temporaryKey.length < 24) {
				int index = 0;
				for (int i = temporaryKey.length; i < 24; i++)
					keyArray[i] = temporaryKey[index];

			}
			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			c.init(1, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
			byte encrypted[] = c.doFinal(toEncryptArray);
			encText = Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encText;
	}

	public String decryptText(String text, String key) {
		String decrypText = "";
		byte keyArray[] = new byte[24];
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			byte temporaryKey[] = m.digest(key.getBytes(UTF));
			if (temporaryKey.length < 24) {
				int index = 0;
				for (int i = temporaryKey.length; i < 24; i++)
					keyArray[i] = temporaryKey[index];

			}
			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			c.init(2, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
			byte decrypted[] = c.doFinal(Base64.getDecoder().decode(text));
			decrypText = new String(decrypted, UTF);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decrypText;
	}

	private static byte[] sharedvector = { 1, 2, 3, 5, 7, 11, 13, 17 };

	/*
	 * public boolean matches(CharSequence rawPassword, String encodedPassword)
	 * { if (encodedPassword == null || encodedPassword.length() == 0) {
	 * logger.warn("Empty encoded password"); return false; }
	 * 
	 * if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
	 * logger.warn("Encoded password does not look like BCrypt"); return false;
	 * }
	 * 
	 * return BCrypt.checkpw(rawPassword.toString(), encodedPassword); }
	 */

	public static void main(String[] args) {
		FaSoftwareSolutinonEncryptionDecryption impl = new FaSoftwareSolutinonEncryptionDecryption();
		String[] passwords = { "ramya@123", "fasupport", "demopot", "sayeed@123", "deepa@123", "suganya@123",
				"dhivya@123", "santhosh@123", "demomis@123", "soundhar@123", "sivakumar@123", "vanithasree@123",
				"vidhya@123", "murugesan@123", "balamurugan@123", "saravanan@123", "rajeshkumar@123", "samraj@123",
				"mohamed@123", "starfivecorp@123", "velusamy@123", "senthilkumar@123", "shivabharat@123",
				"elavarasan@123", "anbazhagan@123", "mano@123", "kalaivanan@123", "margret@123", "alexander@123",
				"aswini@123", "mohanraj@123", "ganesh@123", "asmabeevi@123", "sankar@123", "suresh@123",
				"arunprasad@123", "rajkumar@123", "mohamadiya@123", "balaji@123", "mohammed@123", "vanitha@123",
				"mohd@123", "ashokkumar@123", "thyagarajan@123", "rajeshkumar@123", "gobi@123", "vijayasarathy@123",
				"sahirabanu@123", "arulmaran@123", "surendran@123", "saravanan@123", "muthukumaran@123",
				"rangarajan@123", "ruby@123", "vijayalachoumy@123", "nagarajan@123", "panchatcharam@123",
				"ramachandran@123", "govindharaj@123", "johnson@123", "maheswari@123", "suresh@123", "vivek@123",
				"ashokkumar@123", "mohamedasif@123", "vazhivittal@123", "babu@123", "lakshmi@123", "devi@123",
				"subhash@123", "karthikeyan@123", "jakir@123", "thangalakshmi@123", "kavitha@123", "sakthivel@123",
				"rahuman@123", "purusoathaman@123", "thiyagarajan@123", "jaikumar@123", "vijayakumar@123",
				"chandramohan@123", "surendar@123", "dineshkumar@123", "subramanian@123", "nellaivadivu@123",
				"madhan@123", "subash@123", "kannan@123", "karthigeyan@123", "praveen@123", "balakrishnan@123",
				"seethalakshmi@123", "vinodkumar@123", "mohanram@123", "gobinathan@123", "nathiya@123",
				"renganathan@123", "amutha@123", "rathna@123", "sathishkumar@123", "nusrathbegum@123",
				"rajamanickam@123", "anees@123", "jebarathinam@123", "anandraj@123", "anitha@123", "karthikeyan@123",
				"mohan@123", "robert@123", "senthilkumar@123", "ragunath@123", "mohanraj@123", "gvbshermelee@123",
				"chandra@123", "arunkumar@123", "prasath@123", "umamaheswari@123", "madhanraj@123", "sangeetha@123",
				"venugopal@123", "manimaran@123", "krishnamurthy@123", "subbulakshmi@123", "loganathan@123" };

		for (String password : passwords) {
			// System.out.println(impl.encryptionText(password, "FAS"));
		}

	}

}