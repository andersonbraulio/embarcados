package ufrn.dr.disciplinas.embarcados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.lang.String;

import eu.chainfire.libsuperuser.Shell;

import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void commandRun(View v) {
        EditText command        = (EditText) findViewById(R.id.command);
        Button button_run       = (Button) findViewById(R.id.button_run);
        TextView message        = (TextView) findViewById(R.id.message);
        EditText command_return = (EditText) findViewById(R.id.command_return);

        // clean message field
        message.setText("");

        try {
            String str_cmd_typed = command.getText().toString();
            if (Shell.SU.available()) {
                List<String> strs_return = Shell.SU.run(str_cmd_typed);
                command_return.setText(strListString(strs_return));
                message.setText("O comando foi executado corretamente.");
            } else {
                message.setText("SU não disponível.");
            }
        } catch (Exception e) {
            message.setText("O comando NÃO foi executado corretamente.");
        }
    }

    public String strListString(List<String> lst_string) {
        StringBuilder joiner = new StringBuilder();
        for (String s : lst_string)
            joiner.append(s).append("\n");
        return joiner.toString();
    }
}
