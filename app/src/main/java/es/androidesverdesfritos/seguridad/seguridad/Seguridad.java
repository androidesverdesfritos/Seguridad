package es.androidesverdesfritos.seguridad.seguridad;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Seguridad {

	/**
	 * Devuelve el código único de firma
	 *
	 * @param context
	 * @return
	 */
	public static String getAppSignatureA(final Context context) {
		try {
			Signature[] sigs = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures;

			for (Signature sig : sigs) {
				String firma = Base64.encodeToString(sig.toByteArray(), Base64.DEFAULT);
				Log.i("Nuevo código", "Signature hashcode : " + sig.hashCode());
				Log.i("Nuevo código", "Signature\n" + firma);
				return firma;
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Devuelve el código único de la firma
	 * Gracias a: https://www.airpair.com/android/posts/adding-tampering-detection-to-your-android-app
	 *
	 * @param context
	 * @return
	 */
	public static String getAppSignatureB(final Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
			for (Signature signature : packageInfo.signatures) {
				byte[] signatureBytes = signature.toByteArray();
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				String firma = Base64.encodeToString(md.digest(), Base64.DEFAULT);
				Log.d("airpair", "Firma " + firma);
				return firma;
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
