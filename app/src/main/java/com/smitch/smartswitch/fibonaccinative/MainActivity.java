package com.smitch.smartswitch.fibonaccinative;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private TextView result;
    private RadioGroup type;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MainActivity activity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.result = (TextView)super.findViewById(R.id.text);
        this.input = (EditText)super.findViewById(R.id.input);
        this.type = (RadioGroup)super.findViewById(R.id.type);
        Button button = (Button) super.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textInput = activity.input.getText().toString();
                if (!textInput.isEmpty()) {
                    final Long input = Long.parseLong(textInput);
                    final ProgressDialog dialog = ProgressDialog.show(activity, "Progress", "Computing...", true);

                    new AsyncTask<Void, Void, String>() {
                        @Override
                        protected String doInBackground(Void... params) {
                            long t = System.currentTimeMillis();
                            long result = 0;
                            switch (type.getCheckedRadioButtonId()) {
                                case R.id.fib_ji:
                                    result = FibLib.FibJavaIterative(input);
                                    break;
                                case R.id.fib_jr:
                                    result = FibLib.FibJavaRecursive(input);
                                    break;
                                case R.id.fib_nji:
                                    result = FibLib.FibNativeIterative(input);
                                    break;
                                case R.id.fib_njr:
                                    result = FibLib.FibNativeRecursive(input);
                                    break;
                            }
                            t = System.currentTimeMillis() - t;
                            return String.format("result of fib(%d) = %d in %d", input, result, t);
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            activity.result.setText(s);
                            dialog.dismiss();
                        }
                    }.execute();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
