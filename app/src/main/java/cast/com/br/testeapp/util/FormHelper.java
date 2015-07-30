package cast.com.br.testeapp.util;

import android.content.Context;
import android.widget.EditText;

import cast.com.br.testeapp.R;

/**
 * Created by Administrador on 22/07/2015.
 */
public final class FormHelper {

    private FormHelper(){
        super();
    }
    public static boolean requireValidate(Context context, EditText... editTexts){
        for(EditText editText : editTexts){
            String value;
            if(editText != null) {
                value = editText.getText() == null ? null : editText.getText().toString();

                if (editText.getText() == null || editText.getText().toString().trim().isEmpty()) {
                    String errorMessage = context.getString(R.string.requiredField);
                    editText.setError(errorMessage);
                    return false;
                }
            }
        }
        return true;
    }
}
