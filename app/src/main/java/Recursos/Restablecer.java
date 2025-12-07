package Recursos;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class Restablecer {

    public void restablecer(View... views) {

        for (View v : views) {

            // ====== EditText ======
            if (v instanceof EditText) {
                ((EditText) v).setText("");
            }

            // ====== Spinner ======
            else if (v instanceof Spinner) {
                ((Spinner) v).setSelection(0);
            }

            // ====== RadioButton ======
            else if (v instanceof RadioButton) {
                ((RadioButton) v).setChecked(true);
            }

            // ====== RadioGroup (opcional, por si quieres reiniciarlo completo) ======
            else if (v instanceof RadioGroup) {
                ((RadioGroup) v).clearCheck();
            }

            // ====== CheckBox ======
            else if (v instanceof CheckBox) {
                ((CheckBox) v).setChecked(false);
            }

            // ====== Switch ======
            else if (v instanceof Switch) {
                ((Switch) v).setChecked(false);
            }

            // ====== TextView ======
            else if (v instanceof TextView) {
                ((TextView) v).setText("");
            }
        }
    }

}
