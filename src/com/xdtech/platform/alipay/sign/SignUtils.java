package com.xdtech.platform.alipay.sign;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class SignUtils {

	private static final String ALGORITHM = "RSA";

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * 商户私钥，pkcs8格式
	 */
	private static final String PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALg7jMD34L74k0ymgaNG+ugBkPQEpz1YlAP7U3yo3xIRT1HGKs31/ZqnwhQlnyIpFvOBHSzs30jIHZrEI1ZrL7YseAHheZ4TXkm08KcOtNaEnpwEzzUNIj20HANSVCpwFlk9rMu7ITQKVYp6ViVJB7E8uN9N5Xno/rXsAIzdX7fvAgMBAAECgYAhYokrHsAXWyw2I6YyVFZ7XG1MCtvTfg20ewKyAFgMCh75jpOLJxyBvgypYffoVNHX5nnG/lWn9ruGpCxTKLFIXsYaP3wI8aKoWJr95SdyvOQj35YvT2698tvgnC4ljhkLuCenBK65BGFGFIAjVYeQ2m2jSqesIGynUSCjxzmlIQJBAO/LCNINF6dZ+6jJvWyucNFyXgbvgVyy6FmmuE4qqtB8DJRjSEfX4oZ4ZG1PMc1kgGPzIm484gFfD6q6sp7zBoUCQQDErzFUnOM/4OkCKgyM9RGJowrz6eZwwdH/zAvQBe/Lx51BUG3jFPGRF9B6ZeM8oZZgQ2pv2RPkrNjqnkyUiDDjAkBF6RjqPJzv0qE3ZZHPdNA5kDFvrjY184cgVCYZzAC/BduZC5UQNRW7BLCq2dL4QdvYyBL6ts9hfPiRWxsmkk4NAkEApialUqgyJS1XSXfCIHWKei4cu451F/3z69YwiGvrdcdMJffOzI1NwFdQy4U04GxUvz9UM+Y6suv9Vr2hTbx7JwJAIq1l5rPp7l79segDrvKkplFAP5JyGHEEkVqT9tgPsBGTL3ZoRD+98RdxyOITVkQY+pfN4kFDwcW9Z8UuxARDQA==";
	
	public static String sign(String content) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decode(PRIVATEKEY));
			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));

			byte[] signed = signature.sign();

			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
