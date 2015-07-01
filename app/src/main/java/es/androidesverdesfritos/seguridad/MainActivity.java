package es.androidesverdesfritos.seguridad;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import es.androidesverdesfritos.seguridad.seguridad.Seguridad;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((TextView) findViewById(R.id.firma_uno)).setText(Seguridad.getAppSignatureB(getApplicationContext()));
		((TextView) findViewById(R.id.firma_larga)).setText(Seguridad.getAppSignatureA(getApplicationContext()));
	}
}